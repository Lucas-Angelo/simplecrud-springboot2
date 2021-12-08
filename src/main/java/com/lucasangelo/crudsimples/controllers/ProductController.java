package com.lucasangelo.crudsimples.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.lucasangelo.crudsimples.models.Product;
import com.lucasangelo.crudsimples.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

	@PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody Product obj) {
        obj = this.productService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(obj.getId()).toUri(); // Return new Product URL
        return ResponseEntity.created(uri).build();
    }

	@PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Product obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = this.productService.update(obj);
        return ResponseEntity.noContent().build();
    }

	@PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product obj = this.productService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = this.productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Product>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page, 
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, /* 24 is divisible per 1, 2, 3 and 4, best for layouts */
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
        ) {
        Page<Product> list = this.productService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}