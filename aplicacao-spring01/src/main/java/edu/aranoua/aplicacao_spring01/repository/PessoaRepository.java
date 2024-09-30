package edu.aranoua.aplicacao_spring01.repository;

import edu.aranoua.aplicacao_spring01.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
