package com.example.programmingexam2021.Candidate.DTO;

import com.example.programmingexam2021.Candidate.Entity.Candidate;
import com.example.programmingexam2021.Party.DTO.PartyDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateResponse {

    private Long id;
    private String name;
    private PartyDTO party;

    public static CandidateResponse mapCandidateToResponseDTO(Candidate candidate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(candidate, CandidateResponse.class);
    }

    public static List<CandidateResponse> mapCandidateToResponseDTO(List<Candidate> candidates) {
        ModelMapper modelMapper = new ModelMapper();
        return candidates
                .stream()
                .map(candidate -> modelMapper.map(candidate, CandidateResponse.class))
                .collect(Collectors.toList());
    }
}
