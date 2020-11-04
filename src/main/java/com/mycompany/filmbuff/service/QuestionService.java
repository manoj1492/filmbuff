package com.mycompany.filmbuff.service;

import java.util.List;

import com.mycompany.filmbuff.entity.Category;
import com.mycompany.filmbuff.entity.Question;
import com.mycompany.filmbuff.entity.QuestionAnswer;
import com.mycompany.filmbuff.model.AnswerModel;
import com.mycompany.filmbuff.model.QuestionModel;
import com.mycompany.filmbuff.repository.CategoryRepository;
import com.mycompany.filmbuff.repository.QuestionRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveQuestion(QuestionModel questionModel){
        //Add Question
        Question question = new Question();
        BeanUtils.copyProperties(questionModel, question);
        Category category = categoryRepository.findByName(questionModel.getCategoryName());
        question.setCategoryId(category.getId());
        Question savedQuestion = questionRepository.save(question);

        // Add Question Anwer Mapping
        addAnswers(savedQuestion ,questionModel.getAnswerList());
        questionRepository.save(savedQuestion);
    }

    private void addAnswers(Question question, List<AnswerModel> selectedChoices){
        selectedChoices.forEach(e -> {
            QuestionAnswer questionAnswer = new QuestionAnswer(question.getId(), e.getId(), e.getIsCorrect());
            question.getAnswers().add(questionAnswer);
        });
    }
    
}
