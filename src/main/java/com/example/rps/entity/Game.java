package com.example.rps.entity;

import com.example.rps.listener.GameListener;
import com.example.rps.model.Symbol;
import com.example.rps.model.Winner;
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
@EntityListeners(GameListener.class)
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private boolean active = true;

    @Column(name = "symbol_a")
    private Symbol symbolA;

    @Column(name = "symbol_b")
    private Symbol symbolB;

    private Winner winner;
}
