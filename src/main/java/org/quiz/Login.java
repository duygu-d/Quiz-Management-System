package org.quiz;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Login {
    private final static String path = "C:\\Users\\Laptop\\Desktop\\Talk to me Java\\QuizManagementSystem_\\Quiz-Management-System\\src\\main\\resources\\users.csv";

    private Login() {
    }

    public static User login() throws Exception {
        List<User> allUsers = getAllUsers();
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

    private static List<User> getAllUsers() throws Exception { //TODO: separate the logic for receiving user list to new a class/interface? or new method in class User
        List<String[]> records = CSVFile.readCSVfile(path);
        List<User> allUsers = new ArrayList<>();
        for (int i = 1; i < records.size();i++){
            String[] properties = records.get(i);
            if (properties[0].contains("admin")){
                User administrator = new Administrator(properties[0],properties[1],properties[2],properties[3]);
                allUsers.add(administrator);
            }
            else{
                User normalUser = new NormalUser(properties[0],properties[1],properties[2],properties[3]);
                allUsers.add(normalUser);
            }
        }

        return allUsers;
    }
}
