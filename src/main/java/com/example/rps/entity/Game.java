package com.example.rps.entity;

import com.example.rps.listener.GameListener;
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
@EntityListeners(GameListener.class)
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private boolean active = true;

    @Column(name = "a_symbol")
    private Symbol playerASymbol;

    @Column(name = "b_symbol")
    private Symbol playerBSymbol;

    private String winner;
}
