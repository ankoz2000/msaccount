package com.example.demo;

import com.example.demo.entities.Account;
import com.example.demo.entities.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;

import java.util.Optional;

@Component
public class Listeners {
    private final Integer FIRST_USER_ID = 1;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Listeners(AccountRepository accountRepository, TransactionRepository transactionRepository,
                     KafkaTemplate<String, String> kafkaTemplate) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "msprocess", id = "1")
    public void cashDescListener(String transactionData) {
        Optional<Account> optionalAccount = accountRepository.findById(FIRST_USER_ID);
        if (optionalAccount.isPresent()) {
            Transactions transactions = new Transactions();
//            transactions.setAmount(transactionData.getAmount());
//            transactions.setAccountDebit(transactionData.getDebitCash());
//            transactions.setAccountCredit(transactionData.getCreditCash());
            transactionRepository.save(transactions);
        }
    }
}
