package org.quiz;

import java.util.ArrayList;
import java.util.List;

public final class QuizService{
    private final static String path = "E:\\Valio\\LatestIteration\\Quiz-Management-System\\target\\classes\\users.csv";

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

    public static List<NormalUser> getAllNormalUsers() throws Exception {
        List<String[]> records = CSVFile.readCSVfile(path);
        List<NormalUser> allNormalUsers = new ArrayList<>();
        for (int i = 1; i < records.size(); i++) {
            String[] properties = records.get(i);
            if (properties[0].contains("admin")) {
                continue;
            }
            else{
                NormalUser normalUser = new NormalUser(properties[0], properties[1], properties[2], properties[3]);
                allNormalUsers.add(normalUser);
            }
        }

        return allNormalUsers;
    }

    public static List<Administrator> getAllAdminUsers() throws Exception {
        List<String[]> records = CSVFile.readCSVfile(path);
        List<Administrator> allAdminUsers = new ArrayList<>();
        for (int i = 1; i < records.size(); i++) {
            String[] properties = records.get(i);
            if (properties[0].contains("admin")) {
                Administrator administrator = new Administrator(properties[0], properties[1], properties[2], properties[3]);
                allAdminUsers.add(administrator);
            }
        }

        return allAdminUsers;
    }

}