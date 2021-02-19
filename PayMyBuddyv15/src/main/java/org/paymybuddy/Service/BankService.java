package org.paymybuddy.Service;

import org.paymybuddy.Model.CreditCard;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service

public class BankService {

    //methode externe par défaut vraie pour demander à la banque si la carte credit fonctionnel et le montant existe déjà
    public boolean requestMoney(CreditCard creditCard, BigDecimal amount) {
        return true;
    }
}
