import org.springframework.kafka.annotation.KafkaListener;

public class Listeners {

    @KafkaListener(topics = "msprocess")
    public void cashDescListener(TransactionData transactionData) {
       //todo connect with db in service and process message
    }
}
