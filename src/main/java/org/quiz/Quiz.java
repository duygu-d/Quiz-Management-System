package org.quiz;

import com.opencsv.CSVWriter;
//import com.sun.tools.jdeprscan.CSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    public String quizID;
    public String genre;
    public static final String quizCSVpath = "C:\\Users\\Laptop\\Desktop\\Нова папка (2)\\Quiz-Management-System\\target\\classes\\quizzes.csv";

    public Quiz(String quizID, String genre) {
        this.quizID = quizID;
        this.genre = genre;
    }

    public static void initializeCSVQuiz() throws Exception {

        List<Quiz> quizList = QuizService.getAllQuizzes();

        if (quizList.isEmpty()) {
            File file = new File(quizCSVpath);

            try {
                FileWriter quizID_Writer = new FileWriter(file);

                CSVWriter writer = new CSVWriter(quizID_Writer, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);

//            String quizIDtoString = String.valueOf(quizID);

                String[] header = {"QuizID" , "Title "};
//            String[] properties = {quizIDtoString , "Title ", "QuestionListIDs" };
                writer.writeNext(header);
                String[] demoQuiz = {Guid.GenerateGuid(), "Demo"};
                writer.writeNext(demoQuiz);

//                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getQuizID() {
        return quizID;
    }

    @Override
    public String toString() {
        return quizID + ' ' + genre;
    }
}