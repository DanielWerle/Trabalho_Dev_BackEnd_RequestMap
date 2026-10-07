package org.example.trabalhodevback.repository;

import org.example.trabalhodevback.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
}