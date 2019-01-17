package com.sainath.examen.data.model.fresher;

public class FresherQuiz {
    private String question;
    private String anwser;

    public FresherQuiz() {
    }

    public FresherQuiz(String question, String anwser) {
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
