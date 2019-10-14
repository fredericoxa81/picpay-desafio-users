package com.picpay.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.users.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	
	public User findByCpf(String cpf);
	
	public List<User> findByFullNameStartingWithIgnoreCase(String fullName);
	
}
