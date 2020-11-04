package com.mycompany.filmbuff.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.entity.Answer;
import com.mycompany.filmbuff.entity.Category;
import com.mycompany.filmbuff.entity.Question;
import com.mycompany.filmbuff.entity.Quiz;
import com.mycompany.filmbuff.entity.QuizParticipant;
import com.mycompany.filmbuff.entity.QuizQuestion;
import com.mycompany.filmbuff.entity.User;
import com.mycompany.filmbuff.model.AnswerModel;
import com.mycompany.filmbuff.model.QuestionModel;
import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.repository.AnswerRepository;
import com.mycompany.filmbuff.repository.CategoryRepository;
import com.mycompany.filmbuff.repository.QuestionRepository;
import com.mycompany.filmbuff.repository.QuizRepository;
import com.mycompany.filmbuff.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    public List<QuizModel> getAllQuizzes(String categoryId) {
        List<QuizModel> quizModels = new ArrayList<QuizModel>();
        var it = quizRepository.findByCategoryId(Integer.parseInt(categoryId));
        it.forEach(e -> {
            QuizModel quizModel = new QuizModel();
            BeanUtils.copyProperties(e, quizModel);
            quizModels.add(quizModel);
        });

        return quizModels;
    }

    public void saveQuiz(QuizModel quizModel) throws FileNotFoundException, IOException{
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(quizModel, quiz);
        Category category = categoryRepository.findByName(quizModel.getCategoryName());
        quiz.setCategoryId(category.getId());
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

    public void enterQuiz(String quizId){
        //Fetch User Details
        User user = userRepository.findById(1).get();

        //Fetch Quiz Details
        Quiz quiz = quizRepository.getOne(Integer.parseInt(quizId));

        //User enters in Quiz
        List<QuizParticipant> participants = quiz.getParticipants();
        participants.forEach(participant -> {
            if(participant.getUserId().equals(user.getId())){
                participant.setIsEntered(true);
            }
        });
        quiz.setParticipants(participants);

        //Save Quiz
        quizRepository.save(quiz);
    }

    public void addQuestions(List<QuestionModel> questions, String quizId){
        //Find Quiz
        Integer savedQuizId = Integer.parseInt(quizId);

        //Set Questions
        Quiz quiz = quizRepository.getOne(savedQuizId);
        List<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
        questions.forEach(question -> {
            quizQuestions.add(new QuizQuestion(savedQuizId, question.getId()));
        });
        quiz.setQuestions(quizQuestions);

        //Save Quiz
        quizRepository.save(quiz);
        
    }

    public List<QuestionModel> publishQuestions(String quizId){
        //Fetch a set of questions per quiz to send to a websocket
        Integer savedQuizId = Integer.parseInt(quizId);
        Quiz quiz = quizRepository.getOne(savedQuizId);
        List<QuestionModel> questions = new ArrayList<QuestionModel>();
        quiz.getQuestions().forEach(quizQuestion -> {
            QuestionModel questionModel = new QuestionModel();
            Question question = questionRepository.findById(quizQuestion.getQuestionId()).get();
            List<AnswerModel> answers = new ArrayList<AnswerModel>();
            question.getAnswers().forEach(questionAnswer -> {
                AnswerModel answerModel = new AnswerModel();
                Answer answer = answerRepository.findById(questionAnswer.getAnswerId()).get();
                BeanUtils.copyProperties(answer, answerModel);
                answers.add(answerModel);
            });
            BeanUtils.copyProperties(question, questionModel);
            questionModel.setAnswerList(answers);
            questions.add(questionModel);
        });

        // Publish messages to Client UI through Websocket
        simpMessagingTemplate.convertAndSend("/topic/quiz", questions);
        
        return questions;
    }
}
