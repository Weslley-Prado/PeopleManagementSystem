package com.digitalinovationone.peoplemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.digitalinovationone.peoplemanagementsystem.entity.Person;
import com.digitalinovationone.peoplemanagementsystem.exceptions.InvalidDataException;
import com.digitalinovationone.peoplemanagementsystem.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	@SuppressWarnings("deprecation")
	public Person createPerson(Person person) throws InvalidDataException {
		if (StringUtils.isEmpty(person.getFirstName()) || StringUtils.isEmpty(person.getLastName())) {
			throw new InvalidDataException("O primeiro e último nome são obrigatórios.");
		}
		
		if (StringUtils.isEmpty(person.getCpf()) || !person.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			throw new InvalidDataException("O CPF deve estar no formato 999.999.999-99.");
		}
		
		if (personRepository.findByCpf(person.getCpf()).isPresent()) {
			throw new InvalidDataException("Já existe uma pessoa cadastrada com esse CPF.");
		}
		
		return personRepository.save(person);
	}
}
