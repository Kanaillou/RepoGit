package com.paymybuddy.Service;

import com.paymybuddy.Model.CreditCard;
import com.paymybuddy.Repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditCardService { @Autowired
CreditCardRepository creditCardRepository;

    //methode qui retourne une carte credit à partir de son id
    public CreditCard findById(Long creditCardId) {
        return creditCardRepository.getOne(creditCardId);
    }
}
