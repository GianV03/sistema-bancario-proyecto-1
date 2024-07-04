package com.example.credits_api.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "credits")
public class Credit {

    @Id
    private String id;
    private String clientId;
    private BigDecimal amount;
    private BigDecimal balance;
    private CreditType type;
    private String status;

}

enum CreditType {
    PERSONAL, BUSINESS, CREDIT_CARD
}
