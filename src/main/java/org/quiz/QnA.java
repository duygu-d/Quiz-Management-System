package org.quiz;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QnA {

    public static final String QnACSVpath = "C:\\Users\\Laptop\\Desktop\\Нова папка (2)\\Quiz-Management-System\\target\\classes\\QnA.csv";
    public String questionID;
    public String quizID;
    public String question;
    public String correctAnswer;
    public String WrongAnswer1;
    public String WrongAnswer2;

    public QnA(String questionID, String quizID, String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2) {
        this.questionID = questionID;
        this.quizID = quizID;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.WrongAnswer1 = wrongAnswer1;
        this.WrongAnswer2 = wrongAnswer2;
    }

    public static void CSV_QnA () {
        File file = new File(QnACSVpath);

        try {
            FileWriter QnA_Writer = new FileWriter(file);

            CSVWriter writer = new CSVWriter(QnA_Writer, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"QuestionID" , "QuizID ", "Question", "WrongAnswer1", "WrongAnswer2"};
            writer.writeNext(header);
            String[] demoQnA = {"1", "1", "How many troubles", "Too many", "One", "Two"};
            writer.writeNext(demoQnA);
//            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printQnA(QnA questions) { // FIND HOW TO SHUFFLE CORRECTS
        System.out.println(questions.getQuestionID() + ". " + questions.getQuestion());
        System.out.println("a) " + questions.correctAnswer +
                           "\nb) " + questions.getWrongAnswer1() +
                           "\nc) " + questions.getWrongAnswer2());
    }



    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        quizID = quizID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return WrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        WrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return WrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        WrongAnswer2 = wrongAnswer2;
    }

    @Override
    public String toString() {
        return questionID + ';' +
                quizID + ';' + question + ';' + correctAnswer + ';' + WrongAnswer1 + ';' + WrongAnswer2;
    }
}