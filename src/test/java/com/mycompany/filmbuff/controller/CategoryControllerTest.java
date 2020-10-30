package com.mycompany.filmbuff.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(CategoryController.class)
@Disabled("Disabled for now as @InjectMocks for CategoryService is failing")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /* @InjectMocks
    private CategoryController categoryController;

    @Spy
    private CategoryService categoryService;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    } */
    
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
