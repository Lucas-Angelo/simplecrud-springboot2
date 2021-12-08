package com.lucasangelo.crudsimples.models;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasangelo.crudsimples.models.enums.Profile;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(
      name = "user_product", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<Product>();
    
    @ElementCollection(fetch = FetchType.EAGER) // Force get profiles with user
    @CollectionTable(name = "profile")
    private Set<Integer> profiles = new HashSet<>();

    public User() {
      addProfile(Profile.USER);
    }

    public User(Integer id, String name, String email, String password) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.password = password;
      addProfile(Profile.USER);
    }
  
    public Set<Profile> getProfiles() {
      return this.profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }
    
    public void addProfile(Profile perfil) {
      this.profiles.add(perfil.getCode());
    }

}
