package com.picpay.users.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.service.validation.ConsumerInsert;

@ConsumerInsert
public class ConsumerDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@JsonProperty("user_id")
	private Integer userId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=6, max=80, message="O tamanho deve ser entre 6 e 100 caracteres")
	private String username;
	
	public ConsumerDTO() {
		
	}

	public ConsumerDTO(Integer id, Integer userId, String username) {
		super();
		this.id = id;
		this.userId = userId;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
