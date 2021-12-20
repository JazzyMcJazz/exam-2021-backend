package com.example.programmingexam2021.Candidate.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {

    private Long id;
    private String name;
    private Character party_id;
}
