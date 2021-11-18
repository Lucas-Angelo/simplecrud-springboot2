package com.lucasangelo.crudsimples.services;

import java.util.List;
import java.util.Optional;

import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.repositories.UserRepository;
import com.lucasangelo.crudsimples.services.exceptions.DataIntegrityException;
import com.lucasangelo.crudsimples.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository repository;
    
    public User create(User obj) {
        obj.setId(null); // To protect if Id isn't null...
        obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        return this.repository.save(obj);
    }

    public User update(User obj) {
        User newObj = find(obj.getId());
		updateData(newObj, obj);
		return this.repository.save(newObj);
	}

    public void delete(Integer id){
        find(id);
        try {
            this.repository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este usuário.");
        }
    }

    public User find(Integer id) {
        Optional<User> obj = this.repository.findById(id); 
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName())
        ); 
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
    }
}
