package com.example.programmingexam2021.Candidate.Entity;

import com.example.programmingexam2021.Party.Entity.Party;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    private Party party;

    public Candidate(String name, Party party) {
        this.name = name;
        this.party = party;
    }
}
