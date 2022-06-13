package org.quiz;

public class QuizManagementSystemDemo {
    public static void main(String[] args) throws Exception {

//        byte[] salt = SecureUtils.getSalt();
//        String username = "admin";
//        String password = "Admin_123";
//        String passSaltToString = SecureUtils.encodeSaltToString(salt);
//        String hashedPass = SecureUtils.getSecurePassword(password, salt);
//        Administrator admin = new Administrator(Guid.GenerateAdminGuid(), username, passSaltToString, hashedPass);
//        CSVFile.createRecord(User.path, admin.toString());

        Menu.MainMenu();
    }
}

