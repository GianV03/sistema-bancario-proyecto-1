package com.yanki_api.yanki_api.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Purse {
    @Id
    private String id;
    private String documentType;
    private String documentNumber;
    private String phoneNumber;
    private String email;
    private String cardNumber;
    private double balance;

}
