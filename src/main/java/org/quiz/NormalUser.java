package org.quiz;

import java.util.HashMap;
import java.util.List;

public class NormalUser extends User{
    private List<Quiz> allQuizzes;
    private List<Quiz> assignedQuizzes;
    private List<String> currentResult;
    private HashMap<Quiz, List<String>> last5QuizzesResult;

    public NormalUser(String id, String username, String password) {
        super(id, username, password);
    }

    public void takeAQuiz(List<Quiz> allQuizzes,List<Quiz> assignedQuizzes){

    }

}
