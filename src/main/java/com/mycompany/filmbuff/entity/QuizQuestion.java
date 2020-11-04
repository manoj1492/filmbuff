package com.mycompany.filmbuff.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.mycompany.filmbuff.entity.composite_keys.QuizQuestionId;

@Entity
@Table(name = "quiz_question")
@IdClass(QuizQuestionId.class)
public class QuizQuestion {

    @Id
    private Integer quizId;
    @Id
    private Integer questionId;

    public QuizQuestion(Integer quizId, Integer questionId) {
        this.quizId = quizId;
        this.questionId = questionId;
    }

    public QuizQuestion() {
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
