package com.mycompany.filmbuff.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.entity.QuestionAnswer;
import com.mycompany.filmbuff.model.QuestionModel;
import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.service.QuizService;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/quiz/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("{categoryId}")
    public ResponseEntity<List<QuizModel>> getAllQuizzes(@PathVariable("categoryId") String categoryId) {

        List<QuizModel> listOfQuizzes = new ArrayList<QuizModel>();
        listOfQuizzes.addAll(quizService.getAllQuizzes(categoryId));

        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body(listOfQuizzes);
    }

    @PostMapping("")
    public ResponseEntity<String> saveQuiz(@RequestBody QuizModel quizModel) throws SchedulerException {
        quizService.saveQuiz(quizModel);

        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("register")
    public ResponseEntity<String> addParticipant(@RequestParam("quizId") String quizId){
        quizService.addParticipant(quizId);

        return ResponseEntity.ok().body("User Registered Successfully");
    }

    @GetMapping("enter")
    public ResponseEntity<String> enterQuiz(@RequestParam("quizId") String quizId){
        
        quizService.enterQuiz(quizId);
        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body("User entered in Quiz");

    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody List<QuestionModel> questions, @RequestParam("quizId") String quizId){
        quizService.addQuestions(questions, quizId);

        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body("Questions Added Successfully");

    }

    //TODO Remove this api as it will be called internally from scheduleQuiz.
    @GetMapping("publish/{quizId}")
    public ResponseEntity<String> publishQuestions(@PathVariable("quizId") String quizId){

        quizService.publishQuestions(quizId);
        
        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body("Published Successfully");

    }

    //TODO Remove this api as it will be called internally from saveQuiz.
    @PostMapping("schedule")
    public ResponseEntity<String> scheduleQuiz(@RequestBody QuizModel quizModel) throws SchedulerException {
        
        quizService.scheduleQuiz(quizModel);
        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body("Quiz scheduled Successfully");
    }

    @PostMapping("results")
    public ResponseEntity<String> showResults(@RequestBody List<QuestionAnswer> answeredQuestions){

        Integer score = quizService.showResults(answeredQuestions);
        var headers = new HttpHeaders();
        headers.add("Responded", "QuizController");

        return ResponseEntity.ok().headers(headers).body(score.toString());

    }


}
