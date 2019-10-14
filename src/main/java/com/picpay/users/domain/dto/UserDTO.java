package com.picpay.users.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.service.validation.UserInsert;

@UserInsert
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(max=255, message="O tamanho deve ser de 255 caracteres no máximo.")
	@JsonProperty("full_name")
	private String fullName;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=11, max=11, message="O tamanho deve ser de 11 caracteres.")
	@JsonProperty("phone_number")
	private String phoneNumber;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(max=100, message="O tamanho deve ser de 100 caracteres no máximo.")
	@Email(message="Email inválido")
	private String email;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(max=255, message="O tamanho deve ser de 255 caracteres no máximo.")
	private String password;
	
	public UserDTO() {
		
	}
	
	public UserDTO(Integer id, String fullName, String cpf, String phoneNumber, String email, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
