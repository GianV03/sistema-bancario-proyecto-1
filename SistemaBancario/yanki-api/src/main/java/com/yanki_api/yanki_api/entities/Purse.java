package com.yanki_api.yanki_api.entities;

import com.yanki_api.yanki_api.utils.DocumentTypes;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Purse {
    @Id
    private String id;
    private DocumentTypes documentType;
    private String documentNumber;
    private String phoneNumber;
    private String phoneIMEI;
    private String email;
    private String cardNumber;
    private double balance;

}
