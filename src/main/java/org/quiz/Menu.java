package org.quiz;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.quiz.Login;
import org.quiz.Register;
import org.quiz.Validator;

public final class Menu {
    private final static int width = 100;
    private final static int height = 30;

    private Menu() {
    }

    public static void MainMenu() throws Exception {


        generateASCIIart("W e l c o m e!");
        printRowSeparator();
        System.out.println("LOGIN TO YOUR ACCOUNT AND DEEP DIVE INTO QUIZIELAND!");
        System.out.println("Press 1 to log in.");
        printRowSeparator();
        System.out.println("DON'T HAVE AN ACCOUNT? REGISTER NOW!");
        System.out.println("Press 2 to register.");
        printRowSeparator();
        System.out.println("Press 0 to exit.");
        printRowSeparator();
        System.out.print("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int choice = Validator.validateMainMenuChoice(input);
        switch(choice) {
            case 0:
                printRowSeparator();
                generateASCIIart("G o o d b y e!");
                printRowSeparator();
                break;
            case 1:
                printRowSeparator();
                User loggedUser = Login.login();
                System.out.println("Choose a quiz to add a question to: ");

//                Quiz.initializeCSVQuiz();
//                QnA.CSV_QnA();

                if (loggedUser instanceof Administrator) {
                    Administrator loggedAdmin = (Administrator) loggedUser;
                    String chosenQuizID = scanner.nextLine();
                    List<Quiz> allQuizzes = QuizService.getAllQuizzes();
                    if (!allQuizzes.isEmpty()){
                        Quiz currentQuiz = allQuizzes.get(Integer.parseInt(chosenQuizID) - 1);

                        System.out.println("How many questions do you wish to add?: ");
                        Scanner inputScanner = new Scanner(System.in);
                        String inputQnA = inputScanner.nextLine();
                        int questionCount = Validator.validateQuestionsCount(inputQnA, currentQuiz);
                        loggedAdmin.addQuestions(currentQuiz, questionCount);
                    }
                    else{
                        loggedAdmin.createQuiz();
                    }

                }
//                loggedAdmin.addQuestions(currentQuiz, 1);
//                Login.login();
                printRowSeparator();
                break;
            case 2:
                printRowSeparator();
                Register.createNewNormalUser();
                printRowSeparator();
                break;
        }

    }

    private static void generateASCIIart(String text) {
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", 0, 13));
        Graphics2D graphics = (Graphics2D)g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(text, 10, 20);

        for(int y = 0; y < height; ++y) {
            StringBuilder sb = new StringBuilder();

            for(int x = 0; x < width; ++x) {
                sb.append(image.getRGB(x, y) == -16777216 ? " " : "#");
            }

            if (!sb.toString().trim().isEmpty()) {
                System.out.println(sb);
            }
        }

    }

    private static void printRowSeparator() {
        System.out.println("\n" + StringUtils.repeat('_', width) + "\n");
    }
}
