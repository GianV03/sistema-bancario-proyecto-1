package com.sistema_bancario.bootcoin_monedero_api.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BootcoinTransaction {
    @Id
    private String id;
    private String buyerId;
    private String sellerId;
    private double amount;
    private String paymentMethod; // Yanki or bank transfer
    private String transactionNumber;
    private LocalDateTime timestamp;
}
