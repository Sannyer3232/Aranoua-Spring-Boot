package edu.aranoua.aplicacao_spring01.repository;

import edu.aranoua.aplicacao_spring01.model.Cidade;
import edu.aranoua.aplicacao_spring01.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("select c from Cidade c where c.nome = :nome ")
    Cidade findByNome(@Param("nome") String nome);
}
