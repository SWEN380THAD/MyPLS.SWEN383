package com.springexample.spring;

public class QuizGrades {

    private int id;
    private String name;
    private String quiz_id;
    private String lesson_id;
    private String score;

    public int getIdDate() {
        return id;
    }

    public void setIdDate(int id) { this.id = id; }

    public String getName() { return name;}

    public void setName(String name){this.name = name;}

    public String getQuiz_id(){return quiz_id;}

    public void setQuiz_id(String course_id){this.quiz_id = course_id;}

    public String getLesson_id(){return lesson_id;}

    public void setLesson_id(String lesson_id){this.lesson_id = lesson_id;}

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
