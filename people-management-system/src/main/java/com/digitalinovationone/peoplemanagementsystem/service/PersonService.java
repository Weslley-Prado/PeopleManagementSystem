package com.digitalinovationone.peoplemanagementsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.digitalinovationone.peoplemanagementsystem.dto.request.PersonDTO;
import com.digitalinovationone.peoplemanagementsystem.entity.Person;
import com.digitalinovationone.peoplemanagementsystem.exceptions.InvalidDataException;
import com.digitalinovationone.peoplemanagementsystem.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@SuppressWarnings("deprecation")
	public Person createPerson(PersonDTO personDTO) throws InvalidDataException {
		if (StringUtils.isEmpty(personDTO.getFirstName()) || StringUtils.isEmpty(personDTO.getLastName())) {
			throw new InvalidDataException("O primeiro e último nome são obrigatórios.");
		}
		
		if (StringUtils.isEmpty(personDTO.getCpf()) || !personDTO.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			throw new InvalidDataException("O CPF deve estar no formato 999.999.999-99.");
		}
		
		if (personRepository.findByCpf(personDTO.getCpf()).isPresent()) {
			throw new InvalidDataException("Já existe uma pessoa cadastrada com esse CPF.");
		}
		
		Person person = modelMapper.map(personDTO, Person.class);
		System.out.println(person.getBirthDate());
		return personRepository.save(person);
	}
}
