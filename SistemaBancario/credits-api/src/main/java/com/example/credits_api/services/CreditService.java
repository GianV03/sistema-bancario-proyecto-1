package com.example.credits_api.services;

import com.example.credits_api.entities.Credit;
import com.example.credits_api.reposiroties.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }

    public Flux<Credit> getCreditsByClientId(String clientId) {
        return creditRepository.findByClientId(clientId);
    }

    public Mono<Credit> generateCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public Mono<Void> deleteCreditById(String id) {
        return creditRepository.deleteById(id);
    }
}
