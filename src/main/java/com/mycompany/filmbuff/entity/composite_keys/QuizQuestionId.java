package com.mycompany.filmbuff.entity.composite_keys;

import java.io.Serializable;

public class QuizQuestionId implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer quizId;
    private Integer questionId;

    public QuizQuestionId(Integer quizId, Integer questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
    }

    public QuizQuestionId() {
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    
}
