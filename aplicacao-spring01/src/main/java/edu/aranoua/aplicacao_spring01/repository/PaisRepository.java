package edu.aranoua.aplicacao_spring01.repository;

import edu.aranoua.aplicacao_spring01.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

}
