package com.lucasangelo.crudsimples.repositories;

import com.lucasangelo.crudsimples.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional(readOnly=true)
	Product findByName(String name);
}