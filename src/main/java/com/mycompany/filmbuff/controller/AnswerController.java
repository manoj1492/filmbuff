package com.mycompany.filmbuff.controller;

import com.mycompany.filmbuff.model.AnswerModel;
import com.mycompany.filmbuff.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/answer/")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<String> saveAnswer(@RequestBody AnswerModel answerModel){
        answerService.saveAnswer(answerModel);

        var headers = new HttpHeaders();
        headers.add("Responded", "AnswerController");

        return ResponseEntity.ok().headers(headers).build();
    }
    
}
