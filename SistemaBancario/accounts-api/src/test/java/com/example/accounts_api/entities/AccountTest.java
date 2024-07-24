package com.example.accounts_api.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void testAccountGettersAndSetters() {
        Account account = new Account();

        account.setId("123");
        account.setType("savings");
        account.setBalance(1000.0);
        account.setClientId("client1");

        assertThat(account.getId()).isEqualTo("123");
        assertThat(account.getType()).isEqualTo("savings");
        assertThat(account.getBalance()).isEqualTo(1000.0);
        assertThat(account.getClientId()).isEqualTo("client1");
    }

    @Test
    void testAccountConstructor() {
        Account account = new Account();
        account.setId("123");
        account.setType("savings");
        account.setBalance(1000.0);
        account.setClientId("client1");

        assertThat(account.getId()).isEqualTo("123");
        assertThat(account.getType()).isEqualTo("savings");
        assertThat(account.getBalance()).isEqualTo(1000.0);
        assertThat(account.getClientId()).isEqualTo("client1");
    }
}
