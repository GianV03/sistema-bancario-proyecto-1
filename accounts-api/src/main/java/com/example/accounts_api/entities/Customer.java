package com.example.accounts_api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Customer {
    @Id
    private String id;
    private String nombre;
    private String tipo; // "personal" o "empresarial"
}
