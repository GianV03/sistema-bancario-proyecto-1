package com.example.accounts_api.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "accounts")
public class Account {

    @Id
    private String id;
    private String type;
    private double balance;
    private String clientId; // Assuming client id for association

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
// Constructors, getters, setters, toString methods

    public Account(String id, String type, double balance, String clientId) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.clientId = clientId;
    }

    public Account() {
    }
}
