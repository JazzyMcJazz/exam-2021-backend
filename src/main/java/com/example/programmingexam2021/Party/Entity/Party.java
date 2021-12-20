package com.example.programmingexam2021.Party.Entity;

import com.example.programmingexam2021.Candidate.Entity.Candidate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Party {

    @Id
    @Column(nullable = false)
    private Character id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE)
    private Set<Candidate> candidates = new HashSet<>();
}
