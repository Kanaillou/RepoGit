package org.paymybuddy.Model;

import java.util.ArrayList;

public class Topups {
    private int id;
    private double balance;
    private String email;
    private double sum;
    private int cardID;
    private int userID;
    private ArrayList<CreditCard> credit_card;
    private ArrayList<User> users;


    public Topups(int id, double balance, String email, double sum, int cardID, int userID, ArrayList<CreditCard> credit_card, ArrayList<User> users) {
        this.id = id;
        this.balance = balance;
        this.email = email;
        this.sum = sum;
        this.cardID = cardID;
        this.userID = userID;
        this.credit_card = credit_card;
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<CreditCard> getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(ArrayList<CreditCard> credit_card) {
        this.credit_card = credit_card;
    }


}
