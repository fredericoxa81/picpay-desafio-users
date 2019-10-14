package com.picpay.users.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFullDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonProperty("full_name")
	private String fullName;
	
	private String cpf;

	@JsonProperty("phone_number")
	private String phoneNumber;

	private String email;

	private String password;

	private String username;

	@JsonProperty("social_name")
	private String socialName;
	
	@JsonProperty("fantasy_name")
	private String fantasyName;

	private String cnpj;
	
	public UserFullDTO() {
		
	}

	public UserFullDTO(Integer id, String fullName, String cpf, String phoneNumber, String email, String password,
			String username, String socialName, String fantasyName, String cnpj) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.cpf = cpf;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.username = username;
		this.socialName = socialName;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSocialName() {
		return socialName;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFullDTO other = (UserFullDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
