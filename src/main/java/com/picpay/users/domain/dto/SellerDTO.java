package com.picpay.users.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.service.validation.SellerInsert;

@SellerInsert
public class SellerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@JsonProperty("user_id")
	private Integer userId;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(max=255, message="O tamanho deve ser de 255 caracteres no máximo.")
	@JsonProperty("social_name")
	private String socialName;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(max=255, message="O tamanho deve ser de 255 caracteres no máximo.")
	@JsonProperty("fantasy_name")
	private String fantasyName;

	@NotEmpty(message="Preenchimento obrigatório")
	@CNPJ(message = "CPF inválido")
	private String cnpj;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=6, max=80, message="O tamanho deve ser entre 6 e 100 caracteres")
	private String username;
	
	public SellerDTO() {
		
	}

	public SellerDTO(Integer id, Integer userId, String socialName, String fantasyName, String cnpj, String username) {
		this.id = id;
		this.userId = userId;
		this.socialName = socialName;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUser(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
