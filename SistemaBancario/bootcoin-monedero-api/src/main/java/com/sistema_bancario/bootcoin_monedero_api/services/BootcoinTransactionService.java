package com.sistema_bancario.bootcoin_monedero_api.services;

import com.sistema_bancario.bootcoin_monedero_api.entities.BootcoinTransaction;
import com.sistema_bancario.bootcoin_monedero_api.repositories.BootcoinTransactionRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BootcoinTransactionService {

    @Autowired
    private BootcoinTransactionRepository bootCoinTransactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Single<BootcoinTransaction> createTransaction(String buyerId, String sellerId, double amount, String paymentMethod) {
        BootcoinTransaction transaction = new BootcoinTransaction();
        transaction.setBuyerId(buyerId);
        transaction.setSellerId(sellerId);
        transaction.setAmount(amount);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setTransactionNumber(UUID.randomUUID().toString());
        transaction.setTimestamp(LocalDateTime.now());



        return Single.fromPublisher(bootCoinTransactionRepository.save(transaction))
                .doOnSuccess(savedTransaction -> kafkaTemplate.send("bootcoin-transactions", savedTransaction.getTransactionNumber(), savedTransaction.toString()));
    }

    public Single<BootcoinTransaction> validateTransaction(String transactionNumber) {
        return Single.fromPublisher(
                bootCoinTransactionRepository.findByTransactionNumber(transactionNumber)
                        .switchIfEmpty(Mono.error(new IllegalArgumentException("Transaction not found")))
        );
    }
}
