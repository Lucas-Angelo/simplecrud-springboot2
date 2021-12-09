package com.lucasangelo.crudsimples.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.models.enums.Profile;
import com.lucasangelo.crudsimples.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        this.user = new User(1, "Test", "test@email.com", this.bCryptPasswordEncoder.encode("supersenha"), 100000d);
        this.user.addProfile(Profile.USER);
    }

    @Test
    public void postTest() {
        User createdUser = this.userService.create(this.user);
        assertEquals(0d, createdUser.getBalance());
    }
    
}
