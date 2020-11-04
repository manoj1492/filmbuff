package com.mycompany.filmbuff.controller;

import com.mycompany.filmbuff.model.QuestionModel;
import com.mycompany.filmbuff.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/question/")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<String> saveQuestion(@RequestBody QuestionModel questionModel){
        questionService.saveQuestion(questionModel);

        var headers = new HttpHeaders();
        headers.add("Responded", "QuestionController");

        return ResponseEntity.ok().headers(headers).body("Question created successfully");
    }

}
