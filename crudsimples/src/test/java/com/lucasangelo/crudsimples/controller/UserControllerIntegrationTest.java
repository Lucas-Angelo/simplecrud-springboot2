package com.lucasangelo.crudsimples.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lucasangelo.crudsimples.security.JWTUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTUtil jWTUtil;

    @Test
    public void createUserTest() throws Exception {
        String json = "{\"name\": \"teste\",\"email\": \"testeMock@email.com\",\"password\": \"supersenha\"}";
        this.mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8"))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void deleteOneTest() throws Exception {
        String token = jWTUtil.generateToken("lcs2001_@lucasangelo.com");
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/user/3")
            .header("Authorization", "Bearer " + token))
            .andExpect(status().isNoContent())
            .andDo(print())
            .andReturn();
    }

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void findOneTest() throws Exception {
        String token = jWTUtil.generateToken("lcs2001_@lucasangelo.com");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
            .header("Authorization", "Bearer " + token))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void findAllTest() throws Exception {
        String token = jWTUtil.generateToken("lcs2001_@lucasangelo.com");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
            .header("Authorization", "Bearer " + token))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

}