package com.example.rps.repository;

import com.example.rps.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByActiveTrue();

    void deleteByActiveFalse();
}
