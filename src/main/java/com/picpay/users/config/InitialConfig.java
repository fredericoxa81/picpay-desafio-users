package com.picpay.users.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.picpay.users.domain.Consumer;
import com.picpay.users.domain.Seller;
import com.picpay.users.domain.User;
import com.picpay.users.repository.ConsumerRepository;
import com.picpay.users.repository.SellerRepository;
import com.picpay.users.repository.UserRepository;

@Configuration
public class InitialConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Frederico Araujo", "09606136760", "27997761665", "frederico.araujo@picpay.com", "123");
		User user2 = new User(null, "Tiago Spinasse", "09606136761", "27997761665", "tiago@picpay.com", "123");
		User user3 = new User(null, "Gabriela Pelisson", "09606136762", "27997761665", "gabriela@picpay.com", "123");
		User user4 = new User(null, "Magno Silva", "09606136763", "27997761665", "magno@picpay.com", "123");
		User user5 = new User(null, "Gustavo Magri", "09606136764", "27997761665", "gustavo@picpay.com", "123");
		User user6 = new User(null, "Constante", "09606136765", "27997761665", "davi@picpay.com", "123");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6));

		Consumer cons1 = new Consumer(null, user1, "consumer01");
		Consumer cons2 = new Consumer(null, user2, "consumer02");
		Consumer cons3 = new Consumer(null, user3, "consumer03");
		
		consumerRepository.saveAll(Arrays.asList(cons1, cons2, cons3));
		
		Seller seller1 = new Seller(null, user4, "Flamengo", "Mengão", "00000000000000", "seller01");
		Seller seller2 = new Seller(null, user5, "Flamengo2", "Mengão2", "00000000000001", "seller02");
		
		sellerRepository.saveAll(Arrays.asList(seller1, seller2));

	}


}
