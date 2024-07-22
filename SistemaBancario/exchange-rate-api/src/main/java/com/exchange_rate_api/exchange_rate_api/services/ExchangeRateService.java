package com.exchange_rate_api.exchange_rate_api.services;

import com.exchange_rate_api.exchange_rate_api.entities.ExchangeRate;
import com.exchange_rate_api.exchange_rate_api.repositories.ExchangeRateRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public Mono<ExchangeRate> createExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public Mono<ExchangeRate> getExchangeRateById(String id) {
        return exchangeRateRepository.findById(id);
    }

    public Mono<Void> deleteExchangeRateById(String id) {
        return exchangeRateRepository.deleteById(id);
    }
}
