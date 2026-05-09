package com.interview.Interview_Service.Kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InterviewProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InterviewProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(InterviewEvent event) {
        kafkaTemplate.send("interview-topic", event);
    }
}
