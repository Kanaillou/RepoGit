package com.paymybuddy.Controller;

import java.math.BigDecimal;

public class Recharge {private Long creditCard;
    private BigDecimal amount;

    public Recharge() {
        //
    }

    public Long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Long creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

