package com.example.accounts_api.repositories;


import com.example.accounts_api.entities.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
    Flux<Account> findByClientId(String clientId);
}
