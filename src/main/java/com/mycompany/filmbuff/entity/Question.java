package com.mycompany.filmbuff.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mycompany.filmbuff.util.annotations.ValidEnumValue;
import com.mycompany.filmbuff.util.enums.QuestionTypeEnum;

@Entity
@Table(name = "question")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String statement;
    private Integer categoryId;
    
    @ValidEnumValue(enumClass = QuestionTypeEnum.class)
    private String type;

    @OneToMany( mappedBy = "questionId", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<QuestionAnswer> answers = new ArrayList<QuestionAnswer>();

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    
}
