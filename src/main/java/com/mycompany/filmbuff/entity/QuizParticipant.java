package com.mycompany.filmbuff.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.mycompany.filmbuff.entity.composite_keys.QuizParticipantsId;

@Entity
@IdClass(QuizParticipantsId.class)
@Table(name = "quiz_participants")
public class QuizParticipant{

    @Id
    private Integer userId;

    @Id
    private Integer quizId;

    private Integer correctCount;
    private Boolean isWinner;

    public QuizParticipant(){
        
    }
    public QuizParticipant(Quiz hostedQuiz, User participant) {
        this.userId = participant.getId();
        this.quizId = hostedQuiz.getId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }

}
