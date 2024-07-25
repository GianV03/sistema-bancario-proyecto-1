package com.sistema_bancario.bootcoin_monedero_api.services;

import com.sistema_bancario.bootcoin_monedero_api.entities.Purse;
import com.sistema_bancario.bootcoin_monedero_api.repositories.PurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PurseService{

    @Autowired
    private PurseRepository purseRepository;

    public Mono<Purse> createPurse(Purse purse) {
        return purseRepository.save(purse);
    }

    public Mono<Purse> getPurseById(String id) {
        return purseRepository.findById(id);
    }

    public Flux<Purse> getAllPurses() {
        return purseRepository.findAll();
    }

    public Mono<Purse> updatePurse(String id, Purse purse) {
        return purseRepository.findById(id)
                .flatMap(existingPurse -> {
                    existingPurse.setDocumentType(purse.getDocumentType());
                    existingPurse.setDocumentNumber(purse.getDocumentNumber());
                    existingPurse.setPhoneNumber(purse.getPhoneNumber());
                    existingPurse.setPhoneIMEI(purse.getPhoneIMEI());
                    existingPurse.setEmail(purse.getEmail());
                    existingPurse.setCardNumber(purse.getCardNumber());
                    existingPurse.setBalance(purse.getBalance());
                    return purseRepository.save(existingPurse);
                });
    }

    public Mono<Void> deletePurse(String id) {
        return purseRepository.deleteById(id);
    }

    public Mono<Purse> charge(String id, double amount) {
        return purseRepository.findById(id)
                .flatMap(existingPurse -> {
                    if (existingPurse.getBalance() >= amount) {
                        existingPurse.setBalance(existingPurse.getBalance() - amount);
                        return purseRepository.save(existingPurse);
                    } else {
                        return Mono.error(new RuntimeException("Insufficient balance"));
                    }
                });
    }

    public Mono<Purse> pay(String id, double amount) {
        return purseRepository.findById(id)
                .flatMap(existingPurse -> {
                    existingPurse.setBalance(existingPurse.getBalance() + amount);
                    return purseRepository.save(existingPurse);
                });
    }

}
