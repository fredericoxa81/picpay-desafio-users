package com.picpay.users.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.SellerDTO;
import com.picpay.users.repository.ConsumerRepository;
import com.picpay.users.repository.SellerRepository;
import com.picpay.users.resource.exception.FieldMessageError;

public class SellerInsertValidator implements ConstraintValidator<SellerInsert, SellerDTO> {

	@Autowired
	private SellerRepository repository;
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public void initialize(SellerInsert ann) {
	}

	@Override
	public boolean isValid(SellerDTO sellerDTO, ConstraintValidatorContext context) {
		
		List<FieldMessageError> list = new ArrayList<>();
		
		Consumer consumer = null;
		Seller seller = repository.findByUsername(sellerDTO.getUsername());
		if(seller != null) {
			list.add(new FieldMessageError("username", "Username já cadastrado"));
		}else {
			consumer = consumerRepository.findByUsername(sellerDTO.getUsername());
			if(consumer != null) {
				list.add(new FieldMessageError("username", "Username já cadastrado"));
			}
		}

		if(sellerDTO.getUserId() == null) {
			list.add(new FieldMessageError("userId", "preenchimento obrigatório"));
		}else {
			User user = new User();
			user.setId(sellerDTO.getUserId());
			
			seller = repository.findByUser(user);
			if(seller != null) {
				list.add(new FieldMessageError("userId", "user já possui perfil definido como seller"));
			}else {
				consumer = consumerRepository.findByUser(user);
				if(consumer != null) {
					list.add(new FieldMessageError("userId", "user já possui perfil definido como consumer"));
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

