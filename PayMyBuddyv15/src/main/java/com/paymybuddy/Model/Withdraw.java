package com.paymybuddy.Model;

import java.util.ArrayList;
import java.util.Date;

public class Withdraw {
    private int bankID;
    private int userID;
    private double sum;
    private Date today;
    private ArrayList<CreditCard> credit_card;



    public Withdraw(int bankID, int userID, double sum, Date today,ArrayList<CreditCard> credit_card) {
        this.bankID = bankID;
        this.userID = userID;
        this.sum = sum;
        this.today = today;
        this.credit_card = credit_card;


    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
    public ArrayList<CreditCard> getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(ArrayList<CreditCard> credit_card) {
        this.credit_card = credit_card;
    }
}
