package com.example.programmingexam2021.Party.Controller;

import com.example.programmingexam2021.Party.Service.PartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/parties")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping
    private ResponseEntity<?> getParties() {
        return new ResponseEntity<>(
                partyService.getAllParties(),
                HttpStatus.OK
        );
    }
}
