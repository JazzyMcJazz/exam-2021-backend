package com.example.programmingexam2021.Party.DTO;

import com.example.programmingexam2021.Party.Entity.Party;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyDTO {

    private Character id;
    private String name;
//    private Set<CandidateDTO> candidates = new HashSet<>();

    public static PartyDTO mapPartyToDTO(Party party) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(party, PartyDTO.class);
    }

    public static List<PartyDTO> mapPartyToDTO(List<Party> parties) {
        ModelMapper modelMapper = new ModelMapper();
        return parties
                .stream()
                .map(party -> modelMapper.map(party, PartyDTO.class))
                .collect(Collectors.toList());
    }
}
