package org.quiz;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Administrator extends User{
    private static final String QnACSVpath = "C:\\Users\\Laptop\\Desktop\\Нова папка (2)\\Quiz-Management-System\\target\\classes\\QnA.csv";

    public Administrator(String id, String username, String passwordSalt,String hashedPassword) {
        super(id, username, passwordSalt, hashedPassword);
    }

    public void createQuiz() throws Exception{
        System.out.println("What will be the title of the quiz: ");
        String newQuizTitle = scanner.nextLine();
        Quiz newQuiz = new Quiz(Guid.GenerateGuid(), newQuizTitle);

        CSVFile.createRecord(Quiz.quizCSVpath, newQuiz.toString());

        System.out.println("How many questions do you wish to add?: ");
        int questionsAddAmount = scanner.nextInt();

        addQuestions(newQuiz, questionsAddAmount);
    }

    public void addQuestions(Quiz quiz, int questionAmount) throws Exception { //woodchopper logic
//        System.out.println("To which quiz do you want to add questions?: ");
//        String quizIDtoAdd = scanner.nextLine();
//        Quiz quiz = new Quiz(quizIDtoAdd);
//        System.out.println("How many questions do you want to add?: ");
//        questionAmount = scanner.nextInt();   IN THE MENU

       List<QnA> allQnAs = QuizService.getAllQuestionsOfQuiz(quiz);

        for (int i =0; i<questionAmount;i++){
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter question text: ");
            String text = scanner.nextLine();

            System.out.print("Enter correct answer: ");
            String answer = scanner.nextLine();

            System.out.print("Enter wrong answer: ");
            String wrongAnswer1 = scanner.nextLine();

            System.out.print("Enter wrong answer: ");
            String wrongAnswer2 = scanner.nextLine();

            QnA question = new QnA(Guid.GenerateGuid(),quiz.getQuizID(),text,answer,wrongAnswer1,wrongAnswer2);
            allQnAs.add(question);
        }

        for (QnA question:allQnAs) {

            CSVFile.createRecord(QnACSVpath,question.toString());

        }
    }

    public void editQuestions(Quiz quiz) throws Exception {

        List<Quiz> allQuizzes = QuizService.getAllQuizzes();
        String[] allQuizzesID = new String[allQuizzes.size()];
        System.out.println("Choose a quiz to edit: ");
        String quizIDtoChangeQuestion = scanner.nextLine();
        System.out.println("Which question do you want to change?: ");
        String questionIDtoChange = scanner.nextLine();



        for (int i = 1; i < allQuizzesID.length; i++) { //needs testing, new method in CSVFile for update!
            if (quizIDtoChangeQuestion.equals(allQuizzesID[i])) {
                List<QnA> allQuestionsOfChosenQuiz = QuizService.getAllQuestionsOfQuiz(quiz);

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


        List<Quiz> allQuizzes = QuizService.getAllQuizzes();
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

                    FileWriter NormalUser_writer = new FileWriter("C:\\Users\\Laptop\\Desktop\\Нова папка (2)\\Quiz-Management-System\\target\\classes\\users.csv");

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
