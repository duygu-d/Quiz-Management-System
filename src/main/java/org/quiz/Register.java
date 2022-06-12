package org.quiz;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Register {
    private final static String path = "C:\\Users\\Laptop\\Desktop\\Talk to me Java\\QuizManagementSystem_\\Quiz-Management-System\\src\\main\\resources\\users.csv";

    private Register() {
    }

    public static void createNewNormalUser() throws Exception {
            List<User> allUsers = getAllUsers();
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
            List<User> allUsers = getAllUsers();
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
