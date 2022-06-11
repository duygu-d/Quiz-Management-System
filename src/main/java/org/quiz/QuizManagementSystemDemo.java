package org.quiz;

import java.io.IOException;
import java.util.List;

public class QuizManagementSystemDemo {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Laptop\\Desktop\\Talk to me Java\\QuizManagementSystem_\\Quiz-Management-System\\src\\main\\resources\\users.csv";

        String adminId = Guid.GenerateAdminGuid();
        String pass = "superAdmin123";

        User admin = new Administrator(adminId,"superAdmin",pass);

        //CSVFile.createRecord(path,admin.toString());

        String userId = Guid.GenerateGuid();
        String password = "user123";
        User user = new NormalUser(userId,"user",password);
        CSVFile.createRecord(path,user.toString());

    }
}

