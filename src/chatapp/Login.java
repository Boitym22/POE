
package chatapp;

import java.util.Scanner;

public class Login {
    

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = input.nextLine();

            if (login.checkUsername(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure it contains '_' and is no more than 5 characters.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted, please ensure it meets all requirements.");
            }
        }

        String cell;
        while (true) {
            System.out.print("Enter SA cell number (+27...): ");
            cell = input.nextLine();

            if (login.checkCellPhoneNumber(cell)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell number incorrectly formatted or does not contain international code.");
            }
        }

        System.out.println(login.registerUser(firstName, username, password, cell));

        // LOGIN
        System.out.println("\n=== LOGIN ===");

        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter password: ");
            String loginPass = input.nextLine();

            boolean success = login.login(loginUser, loginPass);

            if (success) {
                System.out.println(login.returnLoginStatus(true));
                break;
            } else {
                attempts--;
                System.out.println("Username or password incorrect. Attempts left: " + attempts);
            }
        }

        if (attempts == 0) {
            System.out.println("Too many failed attempts. Access denied.");
        }
    }

    boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    boolean checkPasswordComplexity(String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean checkCellPhoneNumber(String cell) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean registerUser(String firstName, String username, String password, String cell) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean login(String loginUser, String loginPass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean returnLoginStatus(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


