package com.mycompany.filmbuff.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.repository.QuizRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;

    public List<QuizModel> getAllQuizzes(String categoryId){
        List<QuizModel> quizModels = new ArrayList<QuizModel>();
        var it = quizRepository.findByCategoryId(Integer.parseInt(categoryId));
        it.forEach(e -> {
            QuizModel quizModel = new QuizModel();
            BeanUtils.copyProperties(e, quizModel);
            quizModels.add(quizModel);
        });

        return quizModels;
    }
}
