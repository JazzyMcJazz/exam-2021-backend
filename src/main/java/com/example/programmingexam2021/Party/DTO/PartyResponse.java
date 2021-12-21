package com.example.programmingexam2021.Party.DTO;

import com.example.programmingexam2021.Party.Entity.Party;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PartyResponse {

    private Character id;
    private String name;
    private Set<PartyCandidateDTO> candidates;

    public static List<PartyResponse> mapPartyToDto(List<Party> parties) {
        ModelMapper modelMapper = new ModelMapper();
        return parties
                .stream()
                .map(party -> modelMapper.map(party, PartyResponse.class))
                .collect(Collectors.toList());

    }

}
