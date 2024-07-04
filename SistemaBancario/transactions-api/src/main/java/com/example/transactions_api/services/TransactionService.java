package com.example.transactions_api.services;

import com.example.transactions_api.entities.Transaction;
import com.example.transactions_api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Mono<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Flux<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Flux<Transaction> getTransactionsByClientId(String clientId) {
        return transactionRepository.findByClientId(clientId);
    }

    public Mono<Transaction> generateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Mono<Void> deleteTransactionById(String id) {
        return transactionRepository.deleteById(id);
    }
}
