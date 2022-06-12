package org.quiz;

public class Administrator extends User{
    public Administrator(String id, String username, String passwordSalt,String hashedPassword) {
        super(id, username, passwordSalt, hashedPassword);
    }

    public void createQuiz() {

    }

    public void addQuestions(Quiz quiz){

    }

    public void editQuestions(Quiz quiz){

    }

    public void assignQuizToUser(){

    }
}
