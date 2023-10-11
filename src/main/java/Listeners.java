import entities.Account;
import entities.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import repositories.AccountRepository;
import repositories.TransactionRepository;

import java.util.Optional;

public class Listeners {
    private final Integer FIRST_USER_ID = 1;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public Listeners(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @KafkaListener(topics = "msprocess")
    public void cashDescListener(TransactionData transactionData) {
        Optional<Account> optionalAccount = accountRepository.findById(FIRST_USER_ID);
        if (optionalAccount.isPresent()) {
            Transactions transactions = new Transactions();
            transactions.setAmount(transactionData.getAmount());
            transactions.setAccountDebit(transactionData.getDebitCash());
            transactions.setAccountCredit(transactionData.getCreditCash());
            transactionRepository.save(transactions);
        }
    }
}
