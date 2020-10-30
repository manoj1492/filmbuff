package com.mycompany.filmbuff.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer categoryId;
    private Integer timeLimit;
    private LocalDate date;
    private LocalDateTime startTime;
    private Integer createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", insertable=false, updatable=false)
    private User owner;

    @OneToMany( mappedBy = "quizId", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<QuizParticipant> participants = new ArrayList<QuizParticipant>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    
    public void addParticipant(User participant){
        QuizParticipant quizParticipant = new QuizParticipant(this, participant);
        participants.add(quizParticipant);
        //participant.getParticipants().add(quizParticipant);

    }
    public void removeParticipant(User participant){
        this.participants.removeIf(e -> {
            return e.getUserId().equals(participant.getId());
        });

        participant.getParticipants().removeIf(e -> {
            return e.getUserId().equals(participant.getId());
        });
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((timeLimit == null) ? 0 : timeLimit.hashCode());
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
        Quiz other = (Quiz) obj;
        if (categoryId == null) {
            if (other.categoryId != null)
                return false;
        } else if (!categoryId.equals(other.categoryId))
            return false;
        if (createdBy == null) {
            if (other.createdBy != null)
                return false;
        } else if (!createdBy.equals(other.createdBy))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (participants == null) {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (timeLimit == null) {
            if (other.timeLimit != null)
                return false;
        } else if (!timeLimit.equals(other.timeLimit))
            return false;
        return true;
    }

}
