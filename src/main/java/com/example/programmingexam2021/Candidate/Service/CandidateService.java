package com.example.programmingexam2021.Candidate.Service;

import com.example.programmingexam2021.Candidate.DTO.CandidateRequest;
import com.example.programmingexam2021.Candidate.DTO.CandidateResponse;

import java.util.List;

public interface CandidateService {

    List<CandidateResponse> findAllByPartyId(char id);
    List<CandidateResponse> findAll();
    CandidateResponse addCandidate(CandidateRequest request);
    CandidateResponse editCandidate(CandidateRequest request);
    void addVote(Long id);
    void deleteCandidate(Long id);

}
