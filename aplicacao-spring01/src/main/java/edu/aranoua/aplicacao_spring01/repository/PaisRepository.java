package edu.aranoua.aplicacao_spring01.repository;

import edu.aranoua.aplicacao_spring01.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("select p from pais p where p.nome = :nome")
    Pais findByNome(@Param("nome") String nome);
}
