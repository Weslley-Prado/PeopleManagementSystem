package com.digitalinovationone.peoplemanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) throws NotFoundException {
		try {
			PersonDTO personDTO = personService.listById(id);
			return ResponseEntity.ok(personDTO);
		} catch (InvalidDataException e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Pessoa não encontrada");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
	    try {
	        PersonDTO updatedPerson = personService.update(id, personDTO);
	        return ResponseEntity.ok(updatedPerson);
	    } catch (InvalidDataException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Pessoa não encontrada");
	        return ResponseEntity.badRequest().body(errorResponse);
	    }
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws InvalidDataException {
		try {
			personService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (InvalidDataException e) {
			return ResponseEntity.notFound().build();
		}
	}


}


