package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByName(String name);
}


