package com.example.programmingexam2021.Party.Service;

import com.example.programmingexam2021.Party.DTO.PartyResponse;
import com.example.programmingexam2021.Party.Repository.PartyRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepo partyRepo;

    public PartyServiceImpl(PartyRepo partyRepo) {
        this.partyRepo = partyRepo;
    }

    @Override
    public List<PartyResponse> getAllParties() {
        return PartyResponse.mapPartyToDto(partyRepo.findAll());
    }
}
