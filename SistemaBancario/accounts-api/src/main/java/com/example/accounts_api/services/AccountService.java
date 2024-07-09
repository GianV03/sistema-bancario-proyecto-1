package com.example.accounts_api.services;

import com.example.accounts_api.entities.Account;
import com.example.accounts_api.enums.AccountType;
import com.example.accounts_api.enums.TransactionType;
import com.example.accounts_api.repositories.AccountRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    public Flux<Account> getAccountsByClientId(String clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public Mono<Account> createAccount(Account account) {
        if (account.getType() == AccountType.SAVINGS && account.getBalance() < AccountType.SAVINGS.getMinimumOpeningBalance()) {
            return Mono.error(new IllegalArgumentException("El monto inicial es inferior al mínimo requerido para abrir una cuenta de ahorro."));
        } else if (account.getType() == AccountType.CHECKING && account.getBalance() < AccountType.CHECKING.getMinimumOpeningBalance()) {
            return Mono.error(new IllegalArgumentException("El monto inicial es inferior al mínimo requerido para abrir una cuenta corriente."));
        } else if (account.getType() == AccountType.FIXED_DEPOSIT && account.getBalance() < AccountType.FIXED_DEPOSIT.getMinimumOpeningBalance()) {
            return Mono.error(new IllegalArgumentException("El monto inicial es inferior al mínimo requerido para abrir un plazo fijo."));
        }

        return accountRepository.save(account);

    }

    public Mono<Void> deleteAccountById(String id) {
        return accountRepository.deleteById(id);
    }

    @KafkaListener(topics = "transaction-topic", groupId = "group-id-1")
    public void listenTransactionEvents(String message) {

        System.out.println("Received message: " + message);

        JSONObject json = new JSONObject(message);
        String accountId = json.getString("accountId");
        String transactionTypeStr = json.getString("type");
        TransactionType transactionType = TransactionType.valueOf(transactionTypeStr.toUpperCase());
        System.out.println(transactionType);
        Double amount = json.getDouble("amount");

        // Actualizar el saldo de la cuenta
        updateAccountBalance(accountId, transactionType, amount);
    }

    private void updateAccountBalance(String accountId, TransactionType transactionType, Double amount) {
        accountRepository.findById(accountId).subscribe(account -> {
            double currentBalance = account.getBalance();
            double newBalance;
            if(transactionType == TransactionType.DEPOSIT){
                System.out.println("depósito");
                newBalance = currentBalance + amount;
            }
            else if(transactionType == TransactionType.WITHDRAWAL){
                System.out.println("retiro");
                newBalance = currentBalance - amount;
            }
            else{
                throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
            }

            account.setBalance(newBalance);
            accountRepository.save(account).subscribe();
        });
    }

}
