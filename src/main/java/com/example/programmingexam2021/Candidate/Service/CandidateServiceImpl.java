package com.example.programmingexam2021.Candidate.Service;

import com.example.programmingexam2021.Candidate.DTO.CandidateRequest;
import com.example.programmingexam2021.Candidate.DTO.CandidateResponse;
import com.example.programmingexam2021.Candidate.Entity.Candidate;
import com.example.programmingexam2021.Candidate.Repository.CandidateRepo;
import com.example.programmingexam2021.Party.Entity.Party;
import com.example.programmingexam2021.Party.Repository.PartyRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepo candidateRepo;
    private final PartyRepo partyRepo;

    private final ModelMapper modelMapper = new ModelMapper();

    public CandidateServiceImpl(CandidateRepo candidateRepo, PartyRepo partyRepo) {
        this.candidateRepo = candidateRepo;
        this.partyRepo = partyRepo;
    }

    @Override
    public List<CandidateResponse> findAllByPartyId(char id) {
        List<Candidate> candidates = candidateRepo.findAllByParty_Id(id);

        return CandidateResponse.mapCandidateToResponseDTO(candidates);
    }

    @Override
    public List<CandidateResponse> findAll() {
        List<Candidate> candidates = candidateRepo.findAll();
        return CandidateResponse.mapCandidateToResponseDTO(candidates);
    }

    @Override
    public CandidateResponse addCandidate(CandidateRequest request) {
        Party party = partyRepo.findById(request.getParty_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PARTY NOT FOUND"));

        Candidate candidate = new Candidate(request.getName(), party);

        return CandidateResponse.mapCandidateToResponseDTO(candidateRepo.save(candidate));
    }

    @Override
    public CandidateResponse editCandidate(CandidateRequest request) {
        Party party = partyRepo.findById(request.getParty_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PARTY NOT FOUND"));

        Candidate candidate = modelMapper.map(request, Candidate.class);
        candidate.setParty(party);

        return CandidateResponse.mapCandidateToResponseDTO(candidateRepo.save(candidate));
    }

    @Override
    public void deleteCandidate(Long id) {
        if (!candidateRepo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CANDIDATE NOT FOUND");

        candidateRepo.deleteById(id);
    }
}
