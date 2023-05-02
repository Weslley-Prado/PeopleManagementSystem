package com.digitalinovationone.peoplemanagementsystem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column
	private LocalDate birthDate;
	
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
					})
	@Column(nullable = false)
	private List<Phone> phones;

}
