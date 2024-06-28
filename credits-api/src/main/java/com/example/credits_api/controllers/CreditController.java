package com.example.credits_api.controllers;

import com.example.credits_api.entities.Credit;
import com.example.credits_api.services.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credits")
@Tag(name = "Credits", description = "Créditos")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping
    @Operation(summary = "Obtener todos los créditos")
    public Flux<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener créditos por Id")
    public Mono<ResponseEntity<Credit>> getCreditById(@PathVariable String id) {
        return creditService.getCreditById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Obtener créditos por Id cliente")
    public Flux<Credit> getCreditsByClientId(@PathVariable String clientId) {
        return creditService.getCreditsByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generar un nuevo crédito")
    public Mono<Credit> createCredit(@RequestBody Credit credit) {
        return creditService.generateCredit(credit);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un crédito por Id")
    public Mono<ResponseEntity<Void>> deleteCreditById(@PathVariable String id) {
        return creditService.deleteCreditById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
