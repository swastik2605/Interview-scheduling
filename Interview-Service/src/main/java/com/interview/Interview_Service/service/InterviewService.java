package com.interview.Interview_Service.service;

import com.interview.Interview_Service.Kafka.InterviewEvent;
import com.interview.Interview_Service.Kafka.InterviewProducer;
import com.interview.Interview_Service.Repository.InterviewRepo;
import com.interview.Interview_Service.dto.InterviewDTO;
import com.interview.Interview_Service.model.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepo interviewRepo;
    private final InterviewProducer producer;
    public InterviewService(InterviewRepo repo, InterviewProducer producer) {
        this.interviewRepo = repo;
        this.producer = producer;
    }


    @CachePut(value = "interviews", key = "#result.id")
    public Interview BookInterview(InterviewDTO dto) {

        // 1. Convert DTO → Entity
        Interview interview = new Interview();
        interview.setCandidateId(dto.getCandidateId());
        interview.setInterviewerId(dto.getInterviewerId());
        interview.setTime(dto.getTime());
        interview.setStatus("SCHEDULED");

        // 2. Save to DB
        Interview saved = interviewRepo.save(interview);

        // 3. Send Kafka Event (optional but recommended)
        InterviewEvent event = new InterviewEvent();
        event.setInterviewId(saved.getId());
        event.setMessage("Interview scheduled");

        producer.sendEvent(event);

        // 4. Return saved entity (also updates cache)
        return saved;
    }

    public InterviewDTO convertToDTO(Interview interview){
        InterviewDTO interviewDTO = new InterviewDTO();
        interviewDTO.setInterviewerId(interview.getInterviewerId());
        interviewDTO.setCandidateId(interview.getCandidateId());
        interviewDTO.setTime(interview.getTime());
        return interviewDTO;
    }
    @Cacheable(value = "interviews", key = "#id")
    public InterviewDTO getInterview(Long id) {
        Interview interview = interviewRepo.findById(id).orElse(null);
        return convertToDTO(interview);
    }
    @Cacheable("allinterviews")
    public List<InterviewDTO> getallinterviews() {
        List<Interview> interviews = interviewRepo.findAll();
        List<InterviewDTO> dtoList = new ArrayList<>();
        for (Interview interview : interviews) {
            dtoList.add(convertToDTO(interview));
        }
        return dtoList;
    }
}
