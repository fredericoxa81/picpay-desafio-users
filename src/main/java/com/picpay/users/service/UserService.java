package com.picpay.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.UserDTO;
import com.picpay.users.domain.dto.UserFullDTO;
import com.picpay.users.repository.UserRepository;
import com.picpay.users.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public List<User> findByFullName(String fullName){
		return repository.findByFullNameStartingWithIgnoreCase(fullName);
	}
	
	public User find(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	
	public User insert(User obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public User fromUserDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getFullName(), userDTO.getCpf(), userDTO.getPhoneNumber(), 
				userDTO.getEmail(), userDTO.getPassword());
	}
	
	public UserDTO fromUser(User user) {
		return new UserDTO(user.getId(), user.getFullName(), user.getCpf(), user.getPhoneNumber(), 
				user.getEmail(), user.getPassword());
	}

	public UserFullDTO fromEntitie(Object obj) {
		UserFullDTO userFullDTO = null;
		if(obj != null && obj instanceof User) {
			User o = (User) obj;
			userFullDTO = new UserFullDTO(o.getId(), o.getFullName(), o.getCpf(), o.getPhoneNumber(), 
					o.getEmail(), o.getPassword(), null, null, null, null);
		}else if(obj != null && obj instanceof Consumer) {
			Consumer o = (Consumer) obj;
			userFullDTO = new UserFullDTO(o.getId(), o.getUser().getFullName(), o.getUser().getCpf(), o.getUser().getPhoneNumber(), 
					o.getUser().getEmail(), o.getUser().getPassword(), o.getUsername(), null, null, null);
		}else if(obj != null && obj instanceof Seller) {
			Seller o = (Seller) obj;
			userFullDTO = new UserFullDTO(o.getId(), o.getUser().getFullName(), o.getUser().getCpf(), o.getUser().getPhoneNumber(), 
					o.getUser().getEmail(), o.getUser().getPassword(), o.getUsername(), o.getSocialName(), o.getFantasyName(), o.getCnpj());
		}
		
		return userFullDTO;
	}

}
