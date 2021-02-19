package org.paymybuddy.Controller;

import java.math.BigDecimal;

public class SendTransaction { private Long receiverId;
    private BigDecimal amountSended;

    public SendTransaction() {
        //
    }

    public SendTransaction(Long receiverId, BigDecimal amountSended) {
        this.receiverId = receiverId;
        this.amountSended = amountSended;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmountSended() {
        return amountSended;
    }

    public void setAmountSended(BigDecimal amountSended) {
        this.amountSended = amountSended;
    }
}

