package com.sainath.examen.data.model.fresher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fresher {

    private String question;
    private String anwser;

    public Fresher() {
    }

    public Fresher(String question, String anwser) {
        this.question = question;
        this.anwser = anwser;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }
}
