package org.quiz;

import java.util.HashMap;
import java.util.List;

public class NormalUser extends User{
    private List<Quiz> allQuizzes;
    protected List<Quiz> assignedQuizzes;
    private List<String> currentResult;
    private HashMap<Quiz, List<String>> last5QuizzesResult;

    public NormalUser(String id, String username, String passwordSalt,String hashedPassword) {
        super(id, username, passwordSalt, hashedPassword);
    }

    public void takeAQuiz(List<Quiz> allQuizzes,List<Quiz> assignedQuizzes) {

    }

}
