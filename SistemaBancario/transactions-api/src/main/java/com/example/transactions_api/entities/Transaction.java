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
    private double amount;
    private double commission;
    private TransactionType transactionType; // "DEPOSIT", "WITHDRAWAL"
    private LocalDateTime date;
}

 enum TransactionType {
    DEPOSIT(0.01), // Comisión del 1%
    WITHDRAWAL(0.02); // Comisión del 2%

    private final double commission;

    TransactionType(double commission) {
        this.commission = commission;
    }

    public double getCommission() {
        return commission;
    }
}
