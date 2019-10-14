package com.picpay.users.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.ConsumerDTO;
import com.picpay.users.repository.ConsumerRepository;
import com.picpay.users.repository.SellerRepository;
import com.picpay.users.resource.exception.FieldMessageError;

public class ConsumerInsertValidator implements ConstraintValidator<ConsumerInsert, ConsumerDTO> {

	@Autowired
	private ConsumerRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public void initialize(ConsumerInsert ann) {
	}

	@Override
	public boolean isValid(ConsumerDTO consumerDTO, ConstraintValidatorContext context) {
		
		List<FieldMessageError> list = new ArrayList<>();
		
		Seller seller = null;
		Consumer consumer = repository.findByUsername(consumerDTO.getUsername());
		if(consumer != null) {
			list.add(new FieldMessageError("username", "Username já cadastrado"));
		}else {
			seller = sellerRepository.findByUsername(consumerDTO.getUsername());
			if(seller != null) {
				list.add(new FieldMessageError("username", "Username já cadastrado"));
			}
		}
		
		if(consumerDTO.getUserId() == null) {
			list.add(new FieldMessageError("userId", "Preenchimento obrigatório"));
		}else {
			User user = new User();
			user.setId(consumerDTO.getUserId());
			
			consumer = repository.findByUser(user);
			if(consumer != null) {
				list.add(new FieldMessageError("userId", "user já possui perfil definido como consumer"));
			}else {
				seller = sellerRepository.findByUser(user);
				if(seller != null) {
					list.add(new FieldMessageError("userId", "user já possui perfil definido como seller"));
				}
			}
		}

		for (FieldMessageError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

