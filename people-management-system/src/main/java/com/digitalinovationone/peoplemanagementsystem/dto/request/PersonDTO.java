package com.digitalinovationone.peoplemanagementsystem.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;


public class PersonDTO {

	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	private String firstName;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	private String lastName;
	
	@NotEmpty
	@CPF
	private String cpf;
	
	@Valid
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate birthDate;

    @Valid
    @NotEmpty
	private List<PhoneDTO> phones;
    
	public PersonDTO() {}

	public PersonDTO(Long id, String firstName, String lastName, String cpf, LocalDate birthDate,List<PhoneDTO> phones) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.phones = phones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	
	

}
