package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Answer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnswerRepository extends PagingAndSortingRepository<Answer, Integer>{
    
}
