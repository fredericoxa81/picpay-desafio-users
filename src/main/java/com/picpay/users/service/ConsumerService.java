package com.picpay.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.ConsumerDTO;
import com.picpay.users.repository.ConsumerRepository;
import com.picpay.users.service.exception.ObjectNotFoundException;

@Service
public class ConsumerService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsumerRepository repository;
	
	public List<Consumer> findAll(){
		return repository.findAll();
	}
	
	public List<Consumer> findByUsername(String username){
		return repository.findByUsernameStartingWithIgnoreCase(username);
	}
	
	public Consumer find(Integer id) {
		Optional<Consumer> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Consummer não encontrado! Id: " + id + ", Tipo: " + Consumer.class.getName()));
	}
	
	public Consumer insert(Consumer consumer) {
		userService.find(consumer.getUser().getId());
		consumer.setId(null);
		return repository.save(consumer);
	}
	
	public Consumer fromConsumerDTO(ConsumerDTO consumerDTO) {
		User user = new User();
		user.setId(consumerDTO.getUserId());
		return new Consumer(consumerDTO.getId(), user, consumerDTO.getUsername());
	}
	
	public ConsumerDTO fromConsumer(Consumer consumer) {
		return new ConsumerDTO(consumer.getId(), consumer.getUser().getId(), consumer.getUsername());
	}
}
