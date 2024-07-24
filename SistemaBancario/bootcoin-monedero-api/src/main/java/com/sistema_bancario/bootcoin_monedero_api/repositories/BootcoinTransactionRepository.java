package com.sistema_bancario.bootcoin_monedero_api.repositories;

import com.sistema_bancario.bootcoin_monedero_api.entities.BootcoinTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BootcoinTransactionRepository extends ReactiveMongoRepository<BootcoinTransaction, String> {
    Mono<BootcoinTransaction> findByTransactionNumber(String transactionNumber);
}
