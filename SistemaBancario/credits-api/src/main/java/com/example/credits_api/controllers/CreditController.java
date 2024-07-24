package com.example.credits_api.controllers;

import com.example.credits_api.entities.Credit;
import com.example.credits_api.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping
    public Flux<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Credit>> getCreditById(@PathVariable String id) {
        return creditService.getCreditById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public Flux<Credit> getCreditsByClientId(@PathVariable String clientId) {
        return creditService.getCreditsByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Credit> createCredit(@RequestBody Credit credit) {
        return creditService.generateCredit(credit);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCreditById(@PathVariable String id) {
        return creditService.deleteCreditById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
