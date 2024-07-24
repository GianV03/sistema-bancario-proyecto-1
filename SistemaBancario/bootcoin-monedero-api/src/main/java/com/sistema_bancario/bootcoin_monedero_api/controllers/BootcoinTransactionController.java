package com.sistema_bancario.bootcoin_monedero_api.controllers;

import com.sistema_bancario.bootcoin_monedero_api.entities.BootcoinTransaction;
import com.sistema_bancario.bootcoin_monedero_api.services.BootcoinTransactionService;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bootcoin-transactions")
public class BootcoinTransactionController {

    @Autowired
    private BootcoinTransactionService bootcoinTransactionService;

    @PostMapping
    public Single<ResponseEntity<BootcoinTransaction>> createTransaction(@RequestParam String buyerId,
                                                                         @RequestParam String sellerId,
                                                                         @RequestParam double amount,
                                                                         @RequestParam String paymentMethod) {
        return bootcoinTransactionService.createTransaction(buyerId, sellerId, amount, paymentMethod)
                .map(savedTransaction -> ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction));
    }

    @GetMapping("/{transactionNumber}")
    public Single<ResponseEntity<BootcoinTransaction>> validateTransaction(@PathVariable String transactionNumber) {
        return bootcoinTransactionService.validateTransaction(transactionNumber)
                .map(ResponseEntity::ok);
    }
}
