package com.mycompany.filmbuff.entity.composite_keys;

import java.io.Serializable;

public class QuestionAnswerId implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer questionId;
    private Integer answerId;

    public QuestionAnswerId(Integer questionId, Integer answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public QuestionAnswerId() {
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

}
