package org.quiz;

import java.io.Console;

public class Login {
    public static void login(){
        Console console = System.console();
        if (console != null) {
            String username = console.readLine("Enter your username: ");
            char[] password = console.readPassword("Enter your password: ");
        }
        else {
            System.out.println("Console was not found!");
        }

    }
}
