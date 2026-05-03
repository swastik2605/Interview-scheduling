package com.interview.Interview_Service.Kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterviewEvent implements Serializable {


    private Long interviewId;
    private String message;

    
}
