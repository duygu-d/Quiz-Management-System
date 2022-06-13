package org.quiz;

import java.util.ArrayList;
import java.util.List;

public final class QuizService{
    private final static String path = "C:\\Users\\Laptop\\Desktop\\Talk to me Java\\QuizManagementSystem_\\Quiz-Management-System\\src\\main\\resources\\users.csv";

    private QuizService() {
    }

    public static List<User> getAllUsers() throws Exception {
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