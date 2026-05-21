package com.interview.notification_service.kafka;

import com.interview.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final EmailService emailService;

    public NotificationConsumer(
            EmailService emailService
    ) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "interview-topic",
            groupId = "notification-group"
    )
    public void consume(String message) {

        System.out.println("📩 MESSAGE RECEIVED");

        emailService.sendEmail(
                "swastikthakur2604@gmail.com",
                "Interview Scheduled",
                message
        );
    }
}