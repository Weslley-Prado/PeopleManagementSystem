package com.digitalinovationone.peoplemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalinovationone.peoplemanagementsystem.entity.Person;
import com.digitalinovationone.peoplemanagementsystem.exceptions.InvalidDataException;
import com.digitalinovationone.peoplemanagementsystem.service.PersonService;



@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	
	@PostMapping
	public ResponseEntity<String> createPerson(@RequestBody Person person) {
	    try {
	        personService.createPerson(person);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa criada com sucesso!");
	    } catch (InvalidDataException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}
