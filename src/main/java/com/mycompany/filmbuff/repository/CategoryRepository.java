package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}


