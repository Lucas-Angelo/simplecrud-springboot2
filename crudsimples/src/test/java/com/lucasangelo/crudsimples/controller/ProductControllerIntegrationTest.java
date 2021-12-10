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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lucasangelo.crudsimples.security.JWTUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTUtil jWTUtil;

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void createOneTest() throws Exception {
        String json = "{\"name\": \"teste\",\"price\": \"10\"}";
        this.mockMvc.perform(post("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8"))
            .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void updateOneTest() throws Exception {
        String json = "{\"name\": \"teste1\",\"price\": \"100\"}";
        this.mockMvc.perform(put("/product/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8"))
            .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void deleteOneTest() throws Exception {
        this.mockMvc.perform(delete("/product/3")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void findOneTest() throws Exception {
        this.mockMvc.perform(get("/product/1"))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void findAllTest() throws Exception {
        this.mockMvc.perform(get("/product"))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @WithMockUser(username = "lcs2001_@lucasangelo.com", password = "supersenha", roles = "ADMIN")
    public void buyProductTest() throws Exception {
        String token = jWTUtil.generateToken("lcs2001_@lucasangelo.com");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/product/buy/1")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andReturn();
    }

}