package com.example.accounts_api.services;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.repositories.AccountRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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

    @KafkaListener(topics = "transaction-topic", groupId = "group-id-1")
    public void listenTransactionEvents(String message) {

        System.out.println("Received message: " + message);

        JSONObject json = new JSONObject(message);
        String accountId = json.getString("accountId");
        Double amount = json.getDouble("amount");

        // Actualizar el saldo de la cuenta
        updateAccountBalance(accountId, amount);
    }

    private void updateAccountBalance(String accountId, Double amount) {
        accountRepository.findById(accountId).map(account -> {
            account.setBalance(account.getBalance()+amount);
            return accountRepository.save(account);
        }).subscribe();
    }

}
