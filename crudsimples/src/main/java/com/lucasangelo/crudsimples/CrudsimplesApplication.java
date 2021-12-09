package com.lucasangelo.crudsimples;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class CrudsimplesApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CrudsimplesApplication.class, args);
	}

	@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://docker-mysql:3306/simplecrud?autoReconnect=true&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

	@Override
	public void run(String... args) throws Exception {
	}

}