package com.example.transactions_api.services;

import com.example.transactions_api.entities.Transaction;
import com.example.transactions_api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "transaction-topic";

    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Mono<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Flux<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Mono<Transaction> generateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction)
                .doOnSuccess(savedTransaction -> {
                    String message = createKafkaMessage(savedTransaction);
                    kafkaTemplate.send(TOPIC, message);
                });
    }

    public Mono<Void> deleteTransactionById(String id) {
        return transactionRepository.deleteById(id);
    }

    private String createKafkaMessage(Transaction transaction) {

        return String.format("{\"accountId\":\"%s\", \"amount\":%s}", transaction.getAccountId(), transaction.getAmount());
    }

}
