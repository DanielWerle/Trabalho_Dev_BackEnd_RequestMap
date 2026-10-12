package org.example.trabalhodevback.repository;

import org.example.trabalhodevback.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    @Query("SELECT j FROM Jogo j WHERE " +
            "(COALESCE(:nome, '') = '' OR LOWER(j.nome) LIKE LOWER(CONCAT(COALESCE(:nome, ''), '%'))) AND " +
            "(:preco IS NULL OR j.preco <= :preco) AND " +
            "(COALESCE(:genero, '') = '' OR LOWER(j.genero) LIKE LOWER(:genero))")
    List<Jogo> buscarComFiltro(
            @Param("nome") String nome,
            @Param("preco") Double preco,
            @Param("genero") String genero
    );
}