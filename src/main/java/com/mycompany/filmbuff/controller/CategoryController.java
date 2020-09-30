package com.mycompany.filmbuff.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.model.CategoryModel;
import com.mycompany.filmbuff.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("")
    public ResponseEntity<List<CategoryModel>> getAllCategories(){

        List<CategoryModel> listOfCategory = new ArrayList<CategoryModel>();
        listOfCategory.addAll(categoryService.getAllCategories());

        var headers = new HttpHeaders();
        headers.add("Responded", "CategoryController");

        return ResponseEntity.ok().headers(headers).body(listOfCategory);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryModel> getCategory(@PathVariable("id") String id){
        var category = categoryService.getCategory(id);
        
        var headers = new HttpHeaders();
        headers.add("Responded", "CategoryController");

        return ResponseEntity.ok().headers(headers).body(category);
    }
}
