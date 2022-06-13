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
    public static final String quizCSVpath = "E:\\Valio\\LatestIteration\\Quiz-Management-System\\target\\classes\\quizzes.csv";

    public Quiz(String quizID, String genre) {
        this.quizID = quizID;
        this.genre = genre;
    }

    public static void initializeCSVQuiz() throws Exception {

        List<Quiz> quizList = getAllQuizzes();

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

    public static List<Quiz> getAllQuizzes() throws Exception {
        List<String[]> records = CSVFile.readCSVfile(quizCSVpath);
        List<Quiz> allQuizzes = new ArrayList<>();
        for (int i = 1; i < records.size(); i++){
            String[] properties = records.get(i);
            Quiz currentQuiz = new Quiz(properties[0],properties[1]);
            allQuizzes.add(currentQuiz);
        }
        return allQuizzes;
    }

    public String getQuizID() {
        return quizID;
    }
}