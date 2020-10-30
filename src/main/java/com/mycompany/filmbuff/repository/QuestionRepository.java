package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Question;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer>{
    
}
    