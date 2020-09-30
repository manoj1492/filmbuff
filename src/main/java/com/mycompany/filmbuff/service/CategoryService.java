package com.mycompany.filmbuff.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.model.CategoryModel;
import com.mycompany.filmbuff.repository.CategoryRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories(){
        var it = categoryRepository.findAll();
        var categories = new ArrayList<CategoryModel>();
        it.forEach(e -> {
            CategoryModel category = new CategoryModel(); 
            BeanUtils.copyProperties(e, category);
            categories.add(category);
        });

        return categories;
    }

    public CategoryModel getCategory(String id){
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryRepository.findById(Integer.parseInt(id)).get(), categoryModel);
        return categoryModel;
    }
    
}
