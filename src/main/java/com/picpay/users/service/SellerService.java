package com.picpay.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.domain.dto.SellerDTO;
import com.picpay.users.repository.SellerRepository;
import com.picpay.users.service.exception.ObjectNotFoundException;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository repository;
	
	@Autowired
	private UserService userService;
	
	public List<Seller> findAll(){
		return repository.findAll();
	}
	
	public List<Seller> findByUsername(String username){
		return repository.findByUsernameStartingWithIgnoreCase(username);
	}
	
	public Seller find(Integer id) {
		Optional<Seller> seller = repository.findById(id);
		return seller.orElseThrow(() -> new ObjectNotFoundException(
				"Seller não encontrado! Id: " + id + ", Tipo: " + Seller.class.getName()));
	}
	
	public Seller insert(Seller seller) {
		userService.find(seller.getUser().getId());
		seller.setId(null);
		return repository.save(seller);
	}
	
	public Seller fromSellerDTO(SellerDTO sellerDTO) {
		User user = new User();
		user.setId(sellerDTO.getUserId());
		return new Seller(sellerDTO.getId(), user, sellerDTO.getSocialName(), sellerDTO.getFantasyName(), 
				sellerDTO.getCnpj(), sellerDTO.getUsername());
	}
	
	public SellerDTO fromSeller(Seller seller) {
		return new SellerDTO(seller.getId(), seller.getUser().getId(), seller.getSocialName(), seller.getFantasyName(), 
				seller.getCnpj(), seller.getUsername());
	}
}
