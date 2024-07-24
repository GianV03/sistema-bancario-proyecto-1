package com.exchange_rate_api.exchange_rate_api.repositories;

import com.exchange_rate_api.exchange_rate_api.entities.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRate, String> {
}
