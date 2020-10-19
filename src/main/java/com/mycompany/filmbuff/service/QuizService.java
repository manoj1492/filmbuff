package com.mycompany.filmbuff.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.entity.Category;
import com.mycompany.filmbuff.entity.Quiz;
import com.mycompany.filmbuff.entity.User;
import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.repository.CategoryRepository;
import com.mycompany.filmbuff.repository.QuizRepository;
import com.mycompany.filmbuff.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void saveQuiz(QuizModel quizModel){
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(quizModel, quiz);
        Category category = categoryRepository.findByName(quizModel.getCategoryName());
        quiz.setCategoryId(category.getId());
        quizRepository.save(quiz);
    }

    public void addParticipant(String quizId){
        //Fetch User Details
        User user = userRepository.findById(1).get();

        //Fetch Quiz Details
        Quiz quiz = quizRepository.getOne(Integer.parseInt(quizId));

        //Add Participant
        quiz.addParticipant(user);

        // Save Quiz
        quizRepository.save(quiz);
    }
}
