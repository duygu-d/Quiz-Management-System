package org.quiz;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Login {

    private Login() {
    }

    public static User login() throws Exception {
        List<User> allUsers = QuizService.getAllUsers();
        User loggedUser = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        username = Validator.validateUsername(username);

        System.out.print("Enter your password: "); //TODO: find a way to mask the password input
        String password = scanner.nextLine();
        password = Validator.validatePassword(password);

        for (User user:allUsers) {
            if (user.getUsername().equals(username)){
                Validator.validUserPassword(password,user);
                loggedUser = user;
                break;
            }
        }

        if (loggedUser !=null){
            System.out.println("Hi, "+username+"! You have successfully logged in!");
            return loggedUser;
        }
        else{
            System.out.println("User "+username+" does not exist!");
            return null;
        }
    }
}
