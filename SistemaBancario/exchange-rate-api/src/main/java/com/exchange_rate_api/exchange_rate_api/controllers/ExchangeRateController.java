package com.exchange_rate_api.exchange_rate_api.controllers;

import com.exchange_rate_api.exchange_rate_api.entities.ExchangeRate;
import com.exchange_rate_api.exchange_rate_api.services.ExchangeRateService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping
    public Single<ResponseEntity<ExchangeRate>> createExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        return Single.fromPublisher(exchangeRateService.createExchangeRate(exchangeRate))
                .map(createdExchangeRate -> ResponseEntity.status(HttpStatus.CREATED).body(createdExchangeRate));
    }

    @GetMapping("/{id}")
    public Single<ResponseEntity<ExchangeRate>> getExchangeRateById(@PathVariable String id) {
        return Single.fromPublisher(exchangeRateService.getExchangeRateById(id))
                .map(exchangeRate -> ResponseEntity.ok(exchangeRate))
                .onErrorReturn(e -> ResponseEntity.notFound().build());
    }
}
