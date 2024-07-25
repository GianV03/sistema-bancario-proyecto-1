package com.sistema_bancario.bootcoin_monedero_api.controllers;

import com.sistema_bancario.bootcoin_monedero_api.entities.Purse;
import com.sistema_bancario.bootcoin_monedero_api.services.PurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/purses")
public class PurseController {

    @Autowired
    private PurseService purseService;

    @PostMapping
    public Mono<Purse> createPurse(@RequestBody Purse purse) {
        return purseService.createPurse(purse);
    }

    @GetMapping("/{id}")
    public Mono<Purse> getPurseById(@PathVariable String id) {
        return purseService.getPurseById(id);
    }

    @GetMapping
    public Flux<Purse> getAllPurses() {
        return purseService.getAllPurses();
    }

    @PutMapping("/{id}")
    public Mono<Purse> updatePurse(@PathVariable String id, @RequestBody Purse purse) {
        return purseService.updatePurse(id, purse);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePurse(@PathVariable String id) {
        return purseService.deletePurse(id);
    }

    @PostMapping("/{id}/charge")
    public Mono<Purse> charge(@PathVariable String id, @RequestParam double amount) {
        return purseService.charge(id, amount);
    }

    @PostMapping("/{id}/pay")
    public Mono<Purse> pay(@PathVariable String id, @RequestParam double amount) {
        return purseService.pay(id, amount);
    }

}
