package com.lucasangelo.crudsimples.services.database;

import java.util.Arrays;

import com.lucasangelo.crudsimples.models.Product;
import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.models.enums.Profile;
import com.lucasangelo.crudsimples.repositories.ProductRepository;
import com.lucasangelo.crudsimples.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
    
    public void instantiateTestDatabase() {

		Product product1 = new Product(null, "Office365", 500.00d, null);
		Product product2 = new Product(null, "Windows 10 Pro", 1000.00d, null);
		Product product3 = new Product(null, "Xbox GamePass", 100.00d, null);
		this.productRepository.saveAll(Arrays.asList(product1, product2, product3));

		User user1 = new User(null, "Lucas", "lcs2001_@lucasangelo.com", this.bCryptPasswordEncoder.encode("supersenha"), 100000d);
		User user2 = new User(null, "Ana", "ana@email.com", this.bCryptPasswordEncoder.encode("supersenha"), 2000d);
		User user3 = new User(null, "TesteDelete", "teste@email.com", this.bCryptPasswordEncoder.encode("supersenha"), 0d);
		user1.setProducts(Arrays.asList(product1, product2));
		user1.addProfile(Profile.ADMIN);
		user2.setProducts(Arrays.asList(product2));
		this.userRepository.saveAll(Arrays.asList(user1, user2, user3));



    }
}
