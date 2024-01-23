package br.com.trosoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trosoftware.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{}
