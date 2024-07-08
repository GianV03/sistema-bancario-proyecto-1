package com.example.accounts_api.services;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setup() {
        account = new Account();
        account.setId("1");
        account.setClientId("client1");
        account.setBalance(100.0);
    }

    @Test
    void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(account)));

        StepVerifier.create(accountService.getAllAccounts())
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void testGetAccountById() {
        when(accountRepository.findById(anyString())).thenReturn(Mono.just(account));

        StepVerifier.create(accountService.getAccountById("1"))
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void testGetAccountsByClientId() {
        when(accountRepository.findByClientId(anyString())).thenReturn(Flux.fromIterable(Arrays.asList(account)));

        StepVerifier.create(accountService.getAccountsByClientId("client1"))
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(Mono.just(account));

        StepVerifier.create(accountService.createAccount(account))
                .expectNext(account)
                .verifyComplete();
    }

    @Test
    void testDeleteAccountById() {
        when(accountRepository.deleteById(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(accountService.deleteAccountById("1"))
                .verifyComplete();

        verify(accountRepository, times(1)).deleteById("1");
    }

    @Test
    void testUpdateAccountBalance() {
        when(accountRepository.findById(anyString())).thenReturn(Mono.just(account));
        when(accountRepository.save(any(Account.class))).thenReturn(Mono.just(account));

        accountService.listenTransactionEvents("{\"accountId\": \"1\", \"amount\": 50.0}");

        verify(accountRepository, times(1)).findById("1");
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}
