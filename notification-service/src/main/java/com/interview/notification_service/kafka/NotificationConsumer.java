package com.interview.notification_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(
            topics = "interview-topic",
            groupId = "notification-group"
    )
    public void consume(String message) {

        System.out.println("📩 Notification Received");
        System.out.println(message);
//        System.out.println(event.getMessage());
//        System.out.println(event.getInterviewId());
    }
}
