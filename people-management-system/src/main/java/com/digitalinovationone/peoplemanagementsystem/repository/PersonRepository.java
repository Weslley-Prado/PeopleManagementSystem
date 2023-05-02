package com.digitalinovationone.peoplemanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalinovationone.peoplemanagementsystem.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	Optional<Person> findByCpf(String cpf);

}
