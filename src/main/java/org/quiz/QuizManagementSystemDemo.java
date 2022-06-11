package org.quiz;

import java.io.IOException;
import java.util.List;

public class QuizManagementSystemDemo {
    public static void main(String[] args) throws Exception {
    String adminId = Guid.GenerateAdminGuid();
    String pass = "superAdmin123";

   User admin = new Administrator("superAdmin",pass,adminId);
   String path = "C:\\Users\\Laptop\\Desktop\\Talk to me Java\\Quiz\\src\\users.csv";
   String[] csvDataAdmin = {admin.getId(),admin.getUsername(),admin.getPassword()};

   String userId = Guid.GenerateGuid();
   String password = "user123";
   User user = new NormalUser(userId,"user",password);
   String[] csvDataUser = {user.getId(),user.getUsername(),user.getPassword()};
   CSVFile.createOneLineRecord(path,csvDataUser);

    }
}

