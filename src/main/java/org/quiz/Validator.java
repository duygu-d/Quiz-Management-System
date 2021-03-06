package org.quiz;

import org.apache.commons.collections.functors.IfClosure;

import java.util.Base64;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    private Validator() {
    }

    public static String validateUsername(String username){
        boolean isValid = false;
        while (!isValid){
            String regex = "^([a-z][a-z][a-z]*)(=?.*[._]|\\d*)?.{2,15}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(username);
            if (matcher.matches()){
                isValid = true;
            }
            else{
                System.out.println("The username " + username+" is not valid! Please try again!");
                System.out.print("Enter new password: ");
                Scanner scanner = new Scanner(System.in);
                String newUsername = scanner.nextLine();
                username = newUsername;
            }
        }

        return username;
    }

    public static String validatePassword(String password){
        boolean isValidPass = false;
        while (!isValidPass) {
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*-_!]).{8,20}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);

            if (matcher.matches()){
                isValidPass = true;
            }
            else{
                System.out.println("The password you entered is invalid! Please try again!\n");
                System.out.println("The password length should be between 8 and 20. \nIt must have at least one numeric character,\nat least one lowercase and one uppercase character \nand one special symbol among {@, #, $, %, *, -, _, %, !}\n");
                System.out.print("Enter new password: ");
                Scanner scanner = new Scanner(System.in);
                String newPass = scanner.nextLine();
                password = newPass;
            }
        }

        return password;
    }

    public static int validateMainMenuChoice(String input){
        boolean isValidInput = false;
        while (!isValidInput){
            String regex = "[0-9]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()){
                isValidInput = true;
            }
            else{
                System.out.println("Please enter valid menu choice!");
                System.out.print("Enter menu choice: ");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
            }
        }
        return Integer.parseInt(input);
    }

    public static int validateHeadersCount(String input){
        boolean isValidInput = false;
        while (!isValidInput){
            String regex = "^[1]?[0-9]|20$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()){
                isValidInput = true;
            }
            else{
                System.out.println("Please enter valid headers' count!");
                System.out.print("Enter count: ");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
            }
        }
        return Integer.parseInt(input);
    }

    public static int validateQuestionsCount(String input,Quiz quiz) throws Exception {
        int currQuizQnACount = QuizService.getAllQuestionsOfQuiz(quiz).size();
        Scanner scanner = new Scanner(System.in);

        boolean isValidInput = false;
        while (!isValidInput){
            String regex = "^[1-1][0-9]|20$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()){
                int num = Integer.parseInt(input);
                if (currQuizQnACount != 0 && currQuizQnACount<20){
                    while (num>(20-currQuizQnACount)){
                        System.out.print("You have "+currQuizQnACount+" question(s) in the current quiz.\nYou cannot add more than "+(20-currQuizQnACount)+" question(s).\nEnter questions' count you'd like to add: ");
                        input = scanner.nextLine();
                        num = Integer.parseInt(input);
                    }
                }
                else{
                    System.out.println("You already have 20 questions in the quiz. You cannot add more!\n");
                    break;
                }
                isValidInput = true;
            }
            else{
                if (currQuizQnACount !=0){
                    System.out.print("Invalid input! Questions' count should be a number between 1 and "+(20-currQuizQnACount)+"\n");
                }
                else{
                    System.out.println("Invalid input! Questions' count should be a number between 10 and 20");
                }
                System.out.print("Enter count: ");
                input = scanner.nextLine();
            }
        }
        return Integer.parseInt(input);
    }

    public static void validUserPassword(String password, User user){
       String userPass = user.getHashedPassword();
       byte[] salt = SecureUtils.decodeSaltToByteArr(user.getPasswordSalt());
       String hashedPass = SecureUtils.getSecurePassword(password,salt);

        while(!(userPass.equals(hashedPass))){
            System.out.println("The password you entered is incorrect!");
            System.out.print("Please try again: ");
            Scanner scanner = new Scanner(System.in);
            password = validatePassword(scanner.nextLine());
            hashedPass = SecureUtils.getSecurePassword(password,salt);
        }
    }
}