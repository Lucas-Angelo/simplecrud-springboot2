package com.lucasangelo.crudsimples.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import com.lucasangelo.crudsimples.models.enums.Profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserUnitTest {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @BeforeEach
    public void setUp() {
        this.user = new User(1, "Test", "test@email.com", this.bCryptPasswordEncoder.encode("supersenha"), 100000d);
        this.user.addProfile(Profile.USER);
    }

    @Test
    public void checkUserData() {
        assertEquals(1, this.user.getId());
        assertEquals("Test", this.user.getName());
        assertEquals("test@email.com", this.user.getEmail());
        assertNotEquals("supersenha", this.user.getPassword());
        assertEquals(100000d, this.user.getBalance());
        assertEquals(0, this.user.getProducts().size());
        Set<Profile> profile = new HashSet<Profile>(){{
            add(Profile.toEnum(2));
        }};
        assertEquals(profile, this.user.getProfiles());
    }
    
}
