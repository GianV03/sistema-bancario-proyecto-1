package com.example.accounts_api.controllers;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountService accountService;

    @Test
    void testGetAccountById() {

        Account mockAccount = new Account("1", "Savings", 100.0, "client1");
        when(accountService.getAccountById("1")).thenReturn(Mono.just(mockAccount));

        webTestClient.get().uri("/api/accounts/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
                .value(account -> {

                    assertThat(account.getId()).isEqualTo(mockAccount.getId());
                    assertThat(account.getType()).isEqualTo(mockAccount.getType());
                    assertThat(account.getBalance()).isEqualTo(mockAccount.getBalance());
                    assertThat(account.getClientId()).isEqualTo(mockAccount.getClientId());
                });
    }

    @Test
    void testGetAllAccounts() {

        Account account1 = new Account("1", "Savings", 100.0, "client1");
        Account account2 = new Account("2", "Checking", 200.0, "client2");
        when(accountService.getAllAccounts()).thenReturn(Flux.just(account1, account2));

        webTestClient.get().uri("/api/accounts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Account.class)
                .value(accounts -> {
                    assertThat(accounts).hasSize(2);
                    assertThat(accounts).extracting(Account::getId)
                            .contains(account1.getId(), account2.getId());
                });
    }

}
