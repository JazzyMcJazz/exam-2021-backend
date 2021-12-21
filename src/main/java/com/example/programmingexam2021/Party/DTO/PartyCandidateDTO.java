package com.example.programmingexam2021.Party.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartyCandidateDTO {

    private Long id;
    private String name;
    private Integer votes;
}
