package com.example.credits_api.reposiroties;

import com.example.credits_api.entities.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
    Flux<Credit> findByClientId(String clientId);
}
