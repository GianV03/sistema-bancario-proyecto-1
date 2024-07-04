package com.example.accounts_api.services;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    public Flux<Account> getAccountsByClientId(String clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Void> deleteAccountById(String id) {
        return accountRepository.deleteById(id);
    }
}
