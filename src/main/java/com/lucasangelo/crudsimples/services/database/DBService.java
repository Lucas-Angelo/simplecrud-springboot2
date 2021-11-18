package com.lucasangelo.crudsimples.services.database;

import java.util.Arrays;

import com.lucasangelo.crudsimples.models.User;
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
    
    public void instantiateTestDatabase() {
		User user0 = new User(null, "Lucas", "lcs2001_@lucasangelo.com", this.bCryptPasswordEncoder.encode("supersenha"));
		User user1 = new User(null, "Ana", "ana@email.com", this.bCryptPasswordEncoder.encode("supersenha"));
		this.userRepository.saveAll(Arrays.asList(user0, user1));
    }
}
