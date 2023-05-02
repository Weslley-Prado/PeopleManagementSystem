package com.digitalinovationone.peoplemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.digitalinovationone.peoplemanagementsystem.dto.request.PersonDTO;
import com.digitalinovationone.peoplemanagementsystem.entity.Person;
import com.digitalinovationone.peoplemanagementsystem.entity.Phone;
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

	public List<PersonDTO> listAll() {
	    List<Person> allPeople = personRepository.findAll();
	    List<PersonDTO> allPeopleDTO = allPeople.stream()
	            .map(person -> modelMapper.map(person, PersonDTO.class))
	            .collect(Collectors.toList());
	    return allPeopleDTO;
	}
	
	public PersonDTO listById(Long id) throws NotFoundException, InvalidDataException {
	    Optional<Person> optionalPerson = personRepository.findById(id);
	    if (optionalPerson.isEmpty()) {
	        throw new InvalidDataException("Pessoa não encontrada");
	    }
	    Person person = optionalPerson.get();
	    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
	    return personDTO;
	}
	
	public PersonDTO update(Long id, PersonDTO personDTO) throws InvalidDataException {
	    Optional<Person> optionalPerson = personRepository.findById(id);
	    if (optionalPerson.isEmpty()) {
	        throw new InvalidDataException("Pessoa não encontrada");
	    }
	    Person person = optionalPerson.get();
	    person.setFirstName(personDTO.getFirstName());
	    person.setLastName(personDTO.getLastName());
	    person.setCpf(personDTO.getCpf());
	    person.setBirthDate(personDTO.getBirthDate());

	    List<Phone> phones = personDTO.getPhones().stream()
	            .map(phoneDTO -> modelMapper.map(phoneDTO, Phone.class))
	            .collect(Collectors.toList());
	    person.setPhones(phones);

	    Person updatedPerson = personRepository.save(person);
	    return modelMapper.map(updatedPerson, PersonDTO.class);
	}

	public void delete(Long id) throws InvalidDataException {
	    Optional<Person> optionalPerson = personRepository.findById(id);
	    if (optionalPerson.isEmpty()) {
	        throw new InvalidDataException("Pessoa não encontrada");
	    }
	    personRepository.deleteById(id);
	}


}
