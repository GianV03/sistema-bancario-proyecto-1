package com.example.accounts_api.entities;

import com.example.accounts_api.enums.AccountType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private AccountType type; // Saving, Checking, Fixed Term
    private String customerId;
    private double balance;
}