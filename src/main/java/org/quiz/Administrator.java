package org.quiz;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Administrator extends User{
    public Administrator(String id, String username, String passwordSalt,String hashedPassword) {
        super(id, username, passwordSalt, hashedPassword);
    }

    public void createQuiz() throws Exception { //
        File fileQnA = new File(QnA.QnACSVpath);
        File fileQuiz = new File(Quiz.quizCSVpath);

        try {
            FileWriter QnA_Writer = new FileWriter(fileQnA);
            FileWriter quiz_Writer = new FileWriter(fileQnA);

            CSVWriter writerQuiz = new CSVWriter(quiz_Writer, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            CSVWriter writerQnA = new CSVWriter(QnA_Writer, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String userAnswer = "";
            int questionCounter = 0;

            String[] QuizInput = {Guid.GenerateGuid()};

            while(questionCounter < 20 || !userAnswer.equals("no")) {


                String[] QnA_Input = {Guid.GenerateGuid(), QuizInput[0],
                        scanner.nextLine(), scanner.nextLine(), scanner.nextLine()};
                //ID, QuizID, CorrectAnswer, wrong1, wrong2
                writerQnA.writeNext(QnA_Input);

                System.out.println("Add another question?");
                userAnswer = scanner.nextLine();
                questionCounter++;
            }

            writerQuiz.close();
            writerQnA.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addQuestions(Quiz quiz, int questionAmount) throws Exception { //woodchopper logic
//        System.out.println("To which quiz do you want to add questions?: ");
//        String quizIDtoAdd = scanner.nextLine();
//        Quiz quiz = new Quiz(quizIDtoAdd);
//        System.out.println("How many questions do you want to add?: ");
//        questionAmount = scanner.nextInt();   IN THE MENU

        CSVFile.readCSVfile(Quiz.quizCSVpath);
        String currentQuizID = quiz.getQuizID();

        File fileQnA = new File(QnA.QnACSVpath);
        File fileQuiz = new File(Quiz.quizCSVpath);

        if (CSVFile.readCSVfile(Quiz.quizCSVpath).size() + questionAmount <= 20 || questionAmount == 0) {
            try {
                FileWriter QnA_Writer = new FileWriter(fileQnA);

                CSVWriter writerQnA = new CSVWriter(QnA_Writer, ',',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);

                String[] QnA_Input = {Guid.GenerateGuid(), currentQuizID,
                        scanner.nextLine(), scanner.nextLine(), scanner.nextLine()};
                //ID, QuizID, CorrectAnswer, wrong1, wrong2

                writerQnA.writeNext(QnA_Input);
                writerQnA.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("This quiz already has 20 questions! You cannot add more.");
        }
    }

    public void editQuestions(Quiz quiz) throws Exception {

        List<Quiz> allQuizzes = Quiz.getAllQuizzes();
        String[] allQuizzesID = new String[allQuizzes.size()];
        System.out.println("Choose a quiz to edit: ");
        String quizIDtoChangeQuestion = scanner.nextLine();
        System.out.println("Which question do you want to change?: ");
        String questionIDtoChange = scanner.nextLine();



        for (int i = 1; i < allQuizzesID.length; i++) { //needs testing, new method in CSVFile for update!
            if (quizIDtoChangeQuestion.equals(allQuizzesID[i])) {
                List<QnA> allQuestionsOfChosenQuiz = QnA.getAllQuestionsOfQuiz(quiz);

                for (int currentQnA_ID = 1; currentQnA_ID < allQuestionsOfChosenQuiz.size(); currentQnA_ID++) {
                    if (questionIDtoChange.equals(allQuestionsOfChosenQuiz.get(i).questionID)) {
                        QnA currentQuestionEdit = allQuestionsOfChosenQuiz.get(i);
                        QnA.printQnA(allQuestionsOfChosenQuiz.get(i));

                        System.out.println("Change the question: ");
                        currentQuestionEdit.setQuestion(scanner.nextLine());
                        CSVFile.updateCSV(QnA.QnACSVpath, currentQuestionEdit.question,
                                          Integer.parseInt(currentQuestionEdit.getQuestionID() + 1), 3);

                        System.out.println("Change the correct answer: ");
                        currentQuestionEdit.setCorrectAnswer(scanner.nextLine());
                        CSVFile.updateCSV(QnA.QnACSVpath, currentQuestionEdit.getCorrectAnswer(),
                                Integer.parseInt(currentQuestionEdit.getQuestionID() + 1), 4);

                        System.out.println("Change the wrong answer 1: ");
                        currentQuestionEdit.setWrongAnswer1(scanner.nextLine());
                        CSVFile.updateCSV(QnA.QnACSVpath, currentQuestionEdit.getWrongAnswer1(),
                                Integer.parseInt(currentQuestionEdit.getQuestionID() + 1), 5);

                        System.out.println("Change the correct answer 2: ");
                        currentQuestionEdit.setWrongAnswer2(scanner.nextLine());
                        CSVFile.updateCSV(QnA.QnACSVpath, currentQuestionEdit.getWrongAnswer2(),
                                Integer.parseInt(currentQuestionEdit.getQuestionID() + 1), 6);
                    }
                }
            }
        }
    }

    public void assignQuizToUser(Quiz quiz) throws Exception { // writing in CSV not finished, needs testing
        System.out.println("To which user do you want to assign the quiz?: ");
        String userIDToAssign = scanner.nextLine();

        System.out.println("Which quiz do you want to assign?: ");
        String quizIDToAssign = scanner.nextLine();


        List<Quiz> allQuizzes = Quiz.getAllQuizzes();
        String[] allQuizzesID = new String[allQuizzes.size()];
        Quiz assignedQuiz = allQuizzes.get(0);
        for (int i = 1; i < allQuizzes.size(); i++) {
            allQuizzesID[i] = allQuizzes.get(i).getQuizID();
            if (quizIDToAssign.equals(allQuizzesID[i])) {
                assignedQuiz = allQuizzes.get(i);
                break;
            }
        }
        try {
            List<NormalUser> allNormalUsers = QuizService.getAllNormalUsers();
            String[] allNUsersID = new String[allNormalUsers.size()];
            for (int i = 1; i < allNormalUsers.size(); i++) {
                allNUsersID[i] = allNormalUsers.get(i).getId();
                if (userIDToAssign.equals(allNUsersID[i])){
                    NormalUser userToAssignObject = allNormalUsers.get(i);
                    userToAssignObject.assignedQuizzes.add(assignedQuiz);

                    FileWriter NormalUser_writer = new FileWriter("E:\\Experian\\Project\\Quiz-Management-System\\target\\classes\\users.csv");

                    CSVWriter writerNormalUser = new CSVWriter(NormalUser_writer, ',',
                            CSVWriter.NO_QUOTE_CHARACTER,
                            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                            CSVWriter.DEFAULT_LINE_END);

//                    ????????
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
