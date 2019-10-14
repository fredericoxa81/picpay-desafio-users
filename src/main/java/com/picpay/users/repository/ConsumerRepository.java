package com.picpay.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.User;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer>{
	
	public Consumer findByUsername(String username);
	
	public Consumer findByUser(User user);
	
	public List<Consumer> findByUsernameStartingWithIgnoreCase(String username);

}
