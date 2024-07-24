package com.example.accounts_api.enums;

public enum AccountType {
    SAVINGS(100.0),           // Mínimo de apertura de cuenta de ahorro: $100
    CHECKING(200.0),          // Mínimo de apertura de cuenta corriente: $200
    FIXED_DEPOSIT(500.0);     // Mínimo de apertura de plazo fijo: $500

    private final double minimumOpeningBalance;

    AccountType(double minimumOpeningBalance) {
        this.minimumOpeningBalance = minimumOpeningBalance;
    }

    public double getMinimumOpeningBalance() {
        return minimumOpeningBalance;
    }
}

