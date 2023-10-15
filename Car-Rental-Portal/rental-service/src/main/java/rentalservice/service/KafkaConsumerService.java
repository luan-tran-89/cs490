package rentalservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "carStatusTopic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
        // Add logic to process the message if necessary
    }
}
