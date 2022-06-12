package org.quiz;

import java.util.InputMismatchException;
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
}