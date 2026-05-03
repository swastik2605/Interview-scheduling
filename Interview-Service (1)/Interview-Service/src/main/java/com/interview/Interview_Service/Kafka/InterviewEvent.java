package com.interview.Interview_Service.Kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterviewEvent implements Serializable {


    private Long interviewId;
    private String message;

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getMessage() {   // ✅ THIS IS MISSING IN YOUR CASE
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
