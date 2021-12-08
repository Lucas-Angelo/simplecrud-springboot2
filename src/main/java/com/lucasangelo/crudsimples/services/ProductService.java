package com.lucasangelo.crudsimples.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.lucasangelo.crudsimples.models.Product;
import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.repositories.ProductRepository;
import com.lucasangelo.crudsimples.security.UserSS;
import com.lucasangelo.crudsimples.services.exceptions.AuthorizationException;
import com.lucasangelo.crudsimples.services.exceptions.DataIntegrityException;
import com.lucasangelo.crudsimples.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Product create(Product obj) {
        obj.setId(null); // To protect if Id isn't null...
        return this.productRepository.save(obj);
    }

    public Product update(Product obj) {
        Product newObj = find(obj.getId());
		updateData(newObj, obj);
		return this.productRepository.save(newObj);
	}

    public void delete(Integer id){
        find(id);
        try {
            this.productRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este usuário.");
        }
    }

    public Product find(Integer id) {
        Optional<Product> obj = this.productRepository.findById(id); 
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName())
        ); 
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

	public Page<Product> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.productRepository.
        findAll(pageRequest);
	}

    private void updateData(Product newObj, Product obj) {
        newObj.setName(obj.getName());
    }

    public void buy(Product product) {
        UserSS userSS = UserService.authenticated();
		if (userSS==null)
			throw new AuthorizationException("Acesso negado");

        User user = this.userService.find(userSS.getId());
        if(user.getBalance()<product.getPrice())
            throw new DataIntegrityException("Saldo insuficiente!");
        
        user.addProducts(Arrays.asList(product));
        user.setBalance(user.getBalance()-product.getPrice());
        this.userService.update(user);
    }
}
