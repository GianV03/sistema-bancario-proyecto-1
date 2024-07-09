package com.example.accounts_api.enums;
public enum TransactionType {
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
