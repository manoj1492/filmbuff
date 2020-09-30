package com.mycompany.filmbuff.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @Test
    public void testGetAllCategories() throws Exception {
        this.mockMvc.perform(get("/api/category/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testGetCategory() throws Exception {
        this.mockMvc.perform(get("/api/category/4"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
    }

}
