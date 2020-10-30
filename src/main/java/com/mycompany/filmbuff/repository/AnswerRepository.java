package com.mycompany.filmbuff.repository;

import com.mycompany.filmbuff.entity.Answer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Integer>{
    
}
