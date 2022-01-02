package com.mycompany.filmbuff.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.mycompany.filmbuff.entity.Answer;
import com.mycompany.filmbuff.entity.Category;
import com.mycompany.filmbuff.entity.Question;
import com.mycompany.filmbuff.entity.QuestionAnswer;
import com.mycompany.filmbuff.entity.Quiz;
import com.mycompany.filmbuff.entity.QuizParticipant;
import com.mycompany.filmbuff.entity.QuizQuestion;
import com.mycompany.filmbuff.entity.Users;
import com.mycompany.filmbuff.model.AnswerModel;
import com.mycompany.filmbuff.model.QuestionModel;
import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.repository.AnswerRepository;
import com.mycompany.filmbuff.repository.CategoryRepository;
import com.mycompany.filmbuff.repository.QuestionRepository;
import com.mycompany.filmbuff.repository.QuizRepository;
import com.mycompany.filmbuff.repository.UserRepository;
import com.mycompany.filmbuff.util.Constants;
import com.mycompany.filmbuff.util.enums.QuizStates;
import com.mycompany.filmbuff.util.helper.QuizScheduler;

import org.quartz.SchedulerException;
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

    @Autowired
    private QuizScheduler scheduler;

    private static Logger logger = LoggerFactory.getLogger(QuizService.class);

    //TODO: User id is 1 but need to change it when fetching user info from spring security context
    public List<QuizModel> getAllQuizzes(String categoryId) {
        List<QuizModel> quizModels = new ArrayList<QuizModel>();
        var it = quizRepository.findByCategoryId(Integer.parseInt(categoryId));
        it.forEach(e -> {
            QuizModel quizModel = new QuizModel();
            BeanUtils.copyProperties(e, quizModel);
            e.getParticipants().forEach(participant -> {
                if(participant.getUserId().equals(1))
                    quizModel.setIsRegistered(true);
            });
            quizModels.add(quizModel);
        });

        return quizModels;
    }

    public void saveQuiz(QuizModel quizModel) throws SchedulerException {
        Quiz quiz = new Quiz();
        BeanUtils.copyProperties(quizModel, quiz);
        Category category = categoryRepository.findByName(quizModel.getCategoryName());
        quiz.setCategoryId(category.getId());
        Quiz savedQuiz = quizRepository.save(quiz);

        // Schedule a job to publish questions for this quiz
        scheduler.scheduleQuiz(savedQuiz);
    }

    public void addParticipant(String quizId){
        //Fetch User Details
        Users user = userRepository.findById(1).get();

        //Fetch Quiz Details
        Quiz quiz = quizRepository.getOne(Integer.parseInt(quizId));

        //Add Participant
        quiz.addParticipant(user);

        // Save Quiz
        quizRepository.save(quiz);
    }

    public void enterQuiz(String quizId){
        //Fetch User Details
        Users user = userRepository.findById(1).get();

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

    public void publishQuestions(String quizId){
        //Fetch a set of questions per quiz to send to a websocket
        Integer savedQuizId = Integer.parseInt(quizId);
        Quiz quiz = quizRepository.findById(savedQuizId).get();
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

        // Publish questions to Client UI through Websocket
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(quiz.getStartTime(), currentTime);
        int questionNumber = (int) (duration.toSeconds() / Constants.TIME_INTERVAL);

        if(duration.toSeconds() >= quiz.getTimeLimit() * 60){
            simpMessagingTemplate.convertAndSend("/topic/quiz", QuizStates.COMPLETED);
        }

        logger.info("Duration:" + duration + ", QuestionNumber: "+ questionNumber);
        simpMessagingTemplate.convertAndSend("/topic/quiz", questions.get(questionNumber));
        
    }

    public void scheduleQuiz(QuizModel quizModel) throws SchedulerException {

        Quiz quiz = quizRepository.getOne(quizModel.getId());
        BeanUtils.copyProperties(quizModel, quiz);
        quizRepository.save(quiz);
        scheduler.scheduleQuiz(quiz);

    }

    public Integer showResults(List<QuestionAnswer> answeredQuestions){
        
        AtomicInteger correctCount = new AtomicInteger();
        answeredQuestions.forEach(question -> {
            Question savedQuestion = questionRepository.findById(question.getQuestionId()).get();
            savedQuestion.getAnswers().forEach(answer -> {
                if(question.getAnswerId() != null && answer.getAnswerId().equals(question.getAnswerId()) && answer.getIsCorrect()){
                            correctCount.incrementAndGet();
                }
            });
        });

        return correctCount.get();
    }
}
