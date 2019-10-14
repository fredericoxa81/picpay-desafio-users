package com.picpay.users.resource;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.ConsumerDTO;
import com.picpay.users.domain.dto.SellerDTO;
import com.picpay.users.domain.dto.UserDTO;
import com.picpay.users.domain.dto.UserFullDTO;
import com.picpay.users.resource.util.URL;
import com.picpay.users.service.ConsumerService;
import com.picpay.users.service.SellerService;
import com.picpay.users.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private SellerService sellerService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Set<UserFullDTO>> findByName(@RequestParam(value="q") String name) {
		
		name = URL.decodeParam(name);
		
		Set<UserFullDTO> set = new HashSet<>();
		List<User> listUser = null;
		List<Consumer> listConsumer = null;
		List<Seller> listSeller = null;
		
		if(name == null || name.isBlank()) {
			listUser = service.findAll();
			listConsumer = consumerService.findAll();
			listSeller = sellerService.findAll();
		}else {
			listUser = service.findByFullName(name);
			listConsumer = consumerService.findByUsername(name);
			listSeller = sellerService.findByUsername(name);
		}

		for (User u : listUser) {
			set.add(service.fromEntitie(u));
		}

		for (Consumer c : listConsumer) {
			set.add(service.fromEntitie(c));
		}
		
		for (Seller s : listSeller) {
			set.add(service.fromEntitie(s));
		}
		
		return ResponseEntity.ok().body(set);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findUser(@PathVariable Integer id) {
		User user = service.find(id);
		UserDTO userDTO = service.fromUser(user);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> insertUser(@Valid @RequestBody UserDTO userDTO) {
		User user = service.fromUserDTO(userDTO);
		user = service.insert(user);
		userDTO = service.fromUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	@RequestMapping(value="/consumers/{id}", method=RequestMethod.GET)
	public ResponseEntity<ConsumerDTO> findConsumer(@PathVariable Integer id) {
		Consumer consumer = consumerService.find(id);
		ConsumerDTO consumerDTO = consumerService.fromConsumer(consumer);
		return ResponseEntity.ok().body(consumerDTO);
	}
	
	@RequestMapping(value="/consumers", method=RequestMethod.POST)
	public ResponseEntity<ConsumerDTO> insertConsumer(@Valid @RequestBody ConsumerDTO consumerDTO) {
		Consumer consumer = consumerService.fromConsumerDTO(consumerDTO);
		consumer = consumerService.insert(consumer);
		consumerDTO = consumerService.fromConsumer(consumer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(consumer.getId()).toUri();
		return ResponseEntity.created(uri).body(consumerDTO);
	}
	
	@RequestMapping(value="/sellers/{id}", method=RequestMethod.GET)
	public ResponseEntity<SellerDTO> findSeller(@PathVariable Integer id) {
		Seller seller = sellerService.find(id);
		SellerDTO sellerDTO = sellerService.fromSeller(seller);
		return ResponseEntity.ok().body(sellerDTO);
	}
	
	@RequestMapping(value="/sellers", method=RequestMethod.POST)
	public ResponseEntity<SellerDTO> insertSeller(@Valid @RequestBody SellerDTO sellerDTO) {
		Seller seller = sellerService.fromSellerDTO(sellerDTO);
		seller = sellerService.insert(seller);
		sellerDTO = sellerService.fromSeller(seller);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(seller.getId()).toUri();
		return ResponseEntity.created(uri).body(sellerDTO);
	}
	
}
