package com.example.accounts_api.services;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.repositories.AccountRepository;
import com.example.accounts_api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

    public Mono<Account> createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Mono<Account> updateAccount(String id, Account account) {
        return accountRepository.findById(id)
                .flatMap(existingAccount -> {
                    existingAccount.setType(account.getType());
                    existingAccount.setBalance(account.getBalance());
                    return accountRepository.save(existingAccount);
                });
    }

    public Mono<Void> deleteAccount(String id) {
        return accountRepository.deleteById(id);
    }
}