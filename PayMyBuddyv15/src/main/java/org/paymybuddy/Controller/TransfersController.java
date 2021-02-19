package org.paymybuddy.Controller;

import org.paymybuddy.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.security.Principal;

public class TransfersController {@Autowired
TransferService transferService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/send")
    public boolean send(@RequestBody SendTransaction sendTransaction, Principal principal) {
        return transferService.send(principal, sendTransaction.getReceiverId(), sendTransaction.getAmountSended());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/recharge")
    public boolean rechargeCompte(@RequestBody Recharge recharge, Principal user) {
        return transferService.rechargeCompte(recharge.getCreditCard(), recharge.getAmount(), user);
    }
}

