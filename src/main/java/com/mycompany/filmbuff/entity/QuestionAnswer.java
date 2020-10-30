package com.mycompany.filmbuff.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.mycompany.filmbuff.entity.composite_keys.QuestionAnswerId;

@Entity
@Table(name = "question_answer")
@IdClass(QuestionAnswerId.class)
public class QuestionAnswer {

    @Id
    private Integer questionId;
    @Id
    private Integer answerId;
    private Boolean isCorrect;

    public QuestionAnswer(Integer questionId, Integer answerId, Boolean isCorrect) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public QuestionAnswer() {
    }


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

}
