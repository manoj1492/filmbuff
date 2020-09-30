package com.mycompany.filmbuff.controller;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.filmbuff.model.QuizModel;
import com.mycompany.filmbuff.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/quiz/")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("{categoryId}")
    public ResponseEntity<List<QuizModel>> getAllQuizzes(@PathVariable("categoryId") String categoryId){

        List<QuizModel> listOfQuizzes = new ArrayList<QuizModel>();
        listOfQuizzes.addAll(quizService.getAllQuizzes(categoryId));

        var headers = new HttpHeaders();
        headers.add("Responded", "CategoryController");

        return ResponseEntity.ok().headers(headers).body(listOfQuizzes);
    }
    
}
