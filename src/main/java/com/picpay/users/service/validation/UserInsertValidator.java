package com.picpay.users.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.UserDTO;
import com.picpay.users.repository.UserRepository;
import com.picpay.users.resource.exception.FieldMessageError;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserDTO> {

	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
		
		List<FieldMessageError> list = new ArrayList<>();
		
		User user = null;
		
		user = repository.findByCpf(userDTO.getCpf());
		if(user != null) {
			list.add(new FieldMessageError("cpf", "CPF já cadastrado"));
		}

		user = repository.findByEmail(userDTO.getEmail());
		if(user != null) {
			list.add(new FieldMessageError("email", "Email já cadastrado"));
		}

		for (FieldMessageError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

