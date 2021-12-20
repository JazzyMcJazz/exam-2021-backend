package com.example.programmingexam2021.Candidate.Controller;

import com.example.programmingexam2021.Candidate.DTO.CandidateRequest;
import com.example.programmingexam2021.Candidate.Service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    private ResponseEntity<?> getAllCandidates() {
        return new ResponseEntity<>(
                candidateService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<?> getAllCandidateByPartyId(@PathVariable char id) {

        return new ResponseEntity<>(
                candidateService.findAllByPartyId(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    private ResponseEntity<?> createCandidate(@RequestBody CandidateRequest request) {
        return new ResponseEntity<>(
                candidateService.addCandidate(request),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    private ResponseEntity<?> editCandidate(@RequestBody CandidateRequest request) {
        if (request.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidate ID cannot be null");

        return new ResponseEntity<>(
                candidateService.editCandidate(request),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    private ResponseEntity<?> deleteCandidate(@RequestBody Long id) {
        candidateService.deleteCandidate(id);

        return new ResponseEntity<>(
                null,
                HttpStatus.NO_CONTENT
        );
    }
}
