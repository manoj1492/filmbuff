package com.mycompany.filmbuff.entity.composite_keys;

import java.io.Serializable;

public class QuizParticipantsId implements Serializable{

    private static final long serialVersionUID = 7423253002071976668L;
    private Integer userId;
    private Integer quizId;

    public QuizParticipantsId(Integer userId, Integer quizId) {
        this.userId = userId;
        this.quizId = quizId;
    }

    public QuizParticipantsId(){}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuizParticipantsId other = (QuizParticipantsId) obj;
        if (quizId == null) {
            if (other.quizId != null)
                return false;
        } else if (!quizId.equals(other.quizId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
 
    
}
