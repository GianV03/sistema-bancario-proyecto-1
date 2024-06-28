package com.example.transactions_api.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String accountId;
    private String clientId;
    private double amount;
    private TransactionType transactionType; // e.g., "DEPOSIT", "WITHDRAWAL"
    private LocalDateTime date;
}

enum TransactionType{
    DEPOSIT, WITHDRAWAL
}
