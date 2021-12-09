package com.lucasangelo.crudsimples.dto;

import java.io.Serializable;
import java.util.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(access = Access.READ_ONLY)
    private Integer id;

    @Size(max = 255, min = 1, message = "O tamanho deve ser entre 1 e 255 caracteres")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @Size(max = 255, min = 1, message = "O tamanho deve ser entre 1 e 255 caracteres")
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Deve ser um endereço de e-mail bem formado")
    private String email;

    @Size(max = 255, min = 1, message = "O tamanho deve ser entre 1 e 255 caracteres")
    @NotEmpty(message = "Preenchimento obrigatório")
    @JsonProperty(access = Access.WRITE_ONLY) // Not to show this field in response
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório")
    private double balance;

    private List<Product> products = new ArrayList<Product>();

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.products = obj.getProducts();
    }

}
