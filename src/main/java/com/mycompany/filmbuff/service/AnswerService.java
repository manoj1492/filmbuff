package com.mycompany.filmbuff.service;

import com.mycompany.filmbuff.entity.Answer;
import com.mycompany.filmbuff.model.AnswerModel;
import com.mycompany.filmbuff.repository.AnswerRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService{

    @Autowired
    private AnswerRepository answerRepository;
    
    public void saveAnswer(AnswerModel answerModel){
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerModel, answer);
        answerRepository.save(answer);
    }
}
