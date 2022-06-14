package org.quiz;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Register {
    private final static String path = "C:\\Users\\Laptop\\Desktop\\Нова папка (2)\\Quiz-Management-System\\target\\classes\\users.csv";

    private Register() {
    }

    public static void createNewNormalUser() throws Exception {
            List<User> allUsers =QuizService.getAllUsers();
            List<String> usernames = new ArrayList<>();
            boolean doesUserExist = true;
            System.out.print("Enter new username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            username = Validator.validateUsername(username);

            for (User user : allUsers) {
                usernames.add(user.getUsername());
            }

            while (doesUserExist) {
                if (!(usernames.contains(username))) {
                    doesUserExist = false;
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    password = Validator.validatePassword(password);
                    String passSalt = SecureUtils.encodeSaltToString(SecureUtils.getSalt());
                    String hashedPass = SecureUtils.getSecurePassword(password,SecureUtils.decodeSaltToByteArr(passSalt));
                    User newUser = new NormalUser(Guid.GenerateGuid(), username, passSalt, hashedPass);

                    CSVFile.createRecord(path, newUser.toString());

                    System.out.println("You have successfully created " + username + "!");
                }
                else {
                    System.out.println(username + " already exists! Please try another username!");
                    System.out.print("Enter new username: ");
                    username = scanner.nextLine();
                    username = Validator.validateUsername(username);
                }
            }
    }

    public static void createAdministrator(User loggedUser) throws Exception {
        if (loggedUser instanceof Administrator) {
            List<User> allUsers = QuizService.getAllUsers();
            List<String> usernames = new ArrayList<>();
            boolean doesUserExist = true;
            System.out.print("Enter new username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            username = Validator.validateUsername(username);

            for (User user : allUsers) {
                usernames.add(user.getUsername());
            }

            while (doesUserExist) {
                if (!(usernames.contains(username))) {
                    doesUserExist = false;
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    password = Validator.validatePassword(password);
                    String passSalt = SecureUtils.encodeSaltToString(SecureUtils.getSalt());
                    String hashedPass = SecureUtils.getSecurePassword(password,SecureUtils.decodeSaltToByteArr(passSalt));
                    User newAdmin = new Administrator(Guid.GenerateAdminGuid(), username, passSalt, hashedPass);

                    CSVFile.createRecord(path, newAdmin.toString());

                    System.out.println("You have successfully created " + username + "!");
                }
                else {
                    System.out.println(username + " already exists! Please try another username!");
                    System.out.print("Enter new username: ");
                    username = scanner.nextLine();
                    username = Validator.validateUsername(username);
                }
            }
        }
        else{
            System.out.println("You do not have rights to create new admins!");
        }
    }
}
