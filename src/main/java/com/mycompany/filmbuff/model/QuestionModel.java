package com.mycompany.filmbuff.model;

import java.util.List;

public class QuestionModel {
    
    private Integer id;
    private String statement;
    private String categoryName;
    private String type;

    private List<AnswerModel> answerList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnswerModel> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerModel> answerList) {
        this.answerList = answerList;
    }

}
