//package com.interview.Interview_Service.Kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InterviewConsumer {
//    @KafkaListener(topics = "interview-topic", groupId = "interview-group")
//    public void consume(InterviewEvent event) {
//        System.out.println("Received event: " + event.getMessage());
//    }
//}
