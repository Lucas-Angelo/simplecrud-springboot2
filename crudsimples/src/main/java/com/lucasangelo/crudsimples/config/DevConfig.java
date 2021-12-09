package com.lucasangelo.crudsimples.config;

import com.lucasangelo.crudsimples.services.database.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() {

        // Insert data only test strategy
        if(!this.strategy.equals("create") && !this.strategy.equals("create-drop") /*&& !this.strategy.equals("validate")*/ )
            return false;

        this.dbService.instantiateTestDatabase();
        return true;
    }

}
