package com.digitalinovationone.peoplemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalinovationone.peoplemanagementsystem.dto.request.PersonDTO;
import com.digitalinovationone.peoplemanagementsystem.exceptions.InvalidDataException;
import com.digitalinovationone.peoplemanagementsystem.service.PersonService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	
	@PostMapping
	public ResponseEntity<String> createPerson(@RequestBody @Valid PersonDTO personDTO) {
	    try {
	        personService.createPerson(personDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa criada com sucesso!");
	    } catch (InvalidDataException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@GetMapping
	public List<PersonDTO> listAll() {
		return personService.listAll();
	}
}
