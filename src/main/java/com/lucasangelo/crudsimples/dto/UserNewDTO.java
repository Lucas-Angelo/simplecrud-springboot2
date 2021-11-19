package com.lucasangelo.crudsimples.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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

}
