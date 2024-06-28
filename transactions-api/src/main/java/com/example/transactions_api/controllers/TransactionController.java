package com.example.transactions_api.controllers;

import com.example.transactions_api.entities.Transaction;
import com.example.transactions_api.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "Gestión de Transacciones")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Obtener todas las transacciones")
    public Flux<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por Id")
    public Mono<ResponseEntity<Transaction>> getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Obtener transacciones por Id de cuenta")
    public Flux<Transaction> getTransactionsByAccountId(@PathVariable String accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Obtener transacciones por Id de cliente")
    public Flux<Transaction> getTransactionsByClientId(@PathVariable String clientId) {
        return transactionService.getTransactionsByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una nueva transacción")
    public Mono<Transaction> generateTransaction(@RequestBody Transaction transaction) {
        return transactionService.generateTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una transacción por Id")
    public Mono<ResponseEntity<Void>> deleteTransactionById(@PathVariable String id) {
        return transactionService.deleteTransactionById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
