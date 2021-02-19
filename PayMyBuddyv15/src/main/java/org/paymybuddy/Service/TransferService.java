package org.paymybuddy.Service;

import org.paymybuddy.Model.CreditCard;
import org.paymybuddy.Model.User;
import org.paymybuddy.Model.Transfers;
import org.paymybuddy.Model.Account;
import org.paymybuddy.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;

@Service
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    BankService bankService;

    //testmethodSend
    public TransferService(TransferRepository transactionRepository, AccountService accountService, CreditCardService creditCardService, BankService bankService, UserService userService) {
        this.transferRepository = transactionRepository;
        this.accountService = accountService;
        this.creditCardService = creditCardService;
        this.bankService = bankService;
        this.userService = userService;
    }
    public BigDecimal calculateAmountWithTax(BigDecimal amountSended) {
        return amountSended.multiply(BigDecimal.valueOf(1.005)) ;
    }

    public boolean rechargeCompte(Long creditCardId, BigDecimal amount, Principal user) {

        //creditcard à partir de son id
        CreditCard creditCard = this.creditCardService.findById(creditCardId);
        //piratage
        if (!creditCard.getAccount().getOwner().getEmail().equals(user.getName())) {
            throw new SecurityException("attention tentative de piratage!!");
        }

        boolean request = bankService.requestMoney(creditCard, amount);

        if (request == true) {

            accountService.ajouterArgent(creditCard.getAccount(), amount);
            return true;
        } else {
            throw new IllegalStateException("Operation de recharge non reussie");
        }
    }
/* methode de transfert d'argent de user vers son compte bancaire
    on fait appel à la methode retirerArgent*/

    public boolean tranfertduComptAppliAuCompteBancaire
            (CreditCard creditCard, BigDecimal amount) {

        boolean request = bankService.requestMoney(creditCard, amount);
        if (request == true) {
            accountService.retirerArgent(creditCard.getAccount(), amount);
            return true;
        } else {
            throw new IllegalStateException("Operation de recharge non reussie");
        }

    }
    /*methode send appelé  dans le restcontroller simplification des paramétres par rapport
       à la 2 eme methode send en tenant compte de securité
       le principal c'est lui qui connecte a travers son email dans l authentification
        */
    public boolean send(Principal currentUser, Long receiverId, BigDecimal amountSended) {
        // Principal.getName retourne un email - car on a defini email comme
        // dans la configuration de l'authentication
        String senderEmail = currentUser.getName();

        User sender = userService.findByEmail(senderEmail);

        User receiver = userService.findById(receiverId);

        return send( sender,  receiver, amountSended);//fait appel à la 2eme methode send
    }

    public boolean send(User sender, User receiver, BigDecimal amountSended) {

        Account accountSender =
                accountService.findAccountByEmail(sender.getEmail());

        if (accountSender == null) {
            throw new IllegalStateException("Compte Sender introuvable");
        }

        BigDecimal totalAmount = calculateAmountWithTax(amountSended);

        if (accountSender.getAmount().compareTo(totalAmount) > 0) {

            Account accountReceiver =
                    accountService.findAccountByEmail(receiver.getEmail());

            if (accountReceiver == null) {
                throw new IllegalStateException("Compte Receiver introuvable");
            }

            accountService.retirerArgent(accountSender, totalAmount);
            accountService.ajouterArgent(accountReceiver, amountSended);

            // transaction avec les  nouvelles valeurs puis sera enregistré dans le repository
            Transfers transaction = new Transfers(
                    amountSended,
                    totalAmount.subtract(amountSended),
                    accountSender,
                    accountReceiver
            );
            transferRepository.save(transaction);

            return true;
        }
        throw new IllegalStateException("Solde insuffisant");
    }

}
