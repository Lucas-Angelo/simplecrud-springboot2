package com.lucasangelo.crudsimples.services;

import java.util.List;
import java.util.Optional;

import com.lucasangelo.crudsimples.dto.UserDTO;
import com.lucasangelo.crudsimples.dto.UserNewDTO;
import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.repositories.UserRepository;
import com.lucasangelo.crudsimples.services.exceptions.DataIntegrityException;
import com.lucasangelo.crudsimples.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User create(User obj) {
        obj.setId(null); // To protect if Id isn't null...
        obj.setPassword(obj.getPassword());
        return this.userRepository.save(obj);
    }

    public User update(User obj) {
        obj.setPassword(obj.getPassword());
        User newObj = find(obj.getId());
		updateData(newObj, obj);
		return this.userRepository.save(newObj);
	}

    public void delete(Integer id){
        find(id);
        try {
            this.userRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possível excluir este usuário.");
        }
    }

    public User find(Integer id) {
        Optional<User> obj = this.userRepository.findById(id); 
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName())
        ); 
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), null);
    }

    public User fromDTO(UserNewDTO objDto) {
        return new User(null, objDto.getName(), objDto.getEmail(), bCryptPasswordEncoder.encode(objDto.getPassword()));
    }

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.userRepository.
        findAll(pageRequest);
	}

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
    }
}
