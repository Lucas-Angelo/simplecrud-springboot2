package com.lucasangelo.crudsimples.services.database;

import java.util.Arrays;

import com.lucasangelo.crudsimples.models.Product;
import com.lucasangelo.crudsimples.models.User;
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
		this.productRepository.saveAll(Arrays.asList(product1, product2));

		User user1 = new User(null, "Lucas", "lcs2001_@lucasangelo.com", this.bCryptPasswordEncoder.encode("supersenha"), null);
		User user2 = new User(null, "Ana", "ana@email.com", this.bCryptPasswordEncoder.encode("supersenha"), null);
		user1.setProducts(Arrays.asList(product1, product2));
		user2.setProducts(Arrays.asList(product2));
		this.userRepository.saveAll(Arrays.asList(user1, user2));



    }
}
