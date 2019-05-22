package com.sainath.examen.data.room_database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "interview_quiz")
public class AddQuiz {
  @PrimaryKey(autoGenerate = true)
   private int id;
   private String quiz;
   private String answer;
   private int priority;
    public AddQuiz( int priority,String quiz, String answer) {
        this.priority =priority;
        this.quiz = quiz;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
