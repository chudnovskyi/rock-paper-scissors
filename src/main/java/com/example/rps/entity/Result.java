package com.example.rps.entity;

import com.example.rps.model.Symbol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_a_symbol")
    private Symbol playerASymbol;

    @Column(name = "player_b_symbol")
    private Symbol playerBSymbol;

    private String winnerId;
}
