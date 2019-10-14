package com.picpay.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
	public Seller findByUsername(String username);
	
	public Seller findByUser(User user);
	
	public List<Seller> findByUsernameStartingWithIgnoreCase(String username);

}
