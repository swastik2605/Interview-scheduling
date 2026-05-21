package com.interview.notification_service.kafka;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.io.Serializable;
@Data
public class InterviewEvent implements Serializable {

    private Long interviewId;
    private String message;
}
