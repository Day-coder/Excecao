package com.projeto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.entities.Contato;
@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long> {

}
