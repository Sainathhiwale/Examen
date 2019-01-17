package com.sainath.examen.data.model.expreience;

public class ExperienceQuiz {
    private String question;
    private String anwser;

    public ExperienceQuiz(String question, String anwser) {
        this.question = question;
        this.anwser = anwser;
    }

    public ExperienceQuiz() {
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
