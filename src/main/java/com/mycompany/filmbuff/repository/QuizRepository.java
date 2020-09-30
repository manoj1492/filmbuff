package com.mycompany.filmbuff.repository;

import java.util.List;

import com.mycompany.filmbuff.entity.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    public List<Quiz> findByCategoryId(Integer categoryId);
    
}
