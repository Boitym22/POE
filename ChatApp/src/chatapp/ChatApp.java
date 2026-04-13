/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;

/**
 *
 * @author Student
 */
/*pubplic class ChatApp {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
*/
import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=====================================");
System.out.println("        💬 CHAT APPLICATION 💬       ");
System.out.println("=====================================");
System.out.println("       Welcome to Chat App!");
System.out.println("=====================================\n");

        System.out.println("======= REGISTRATION PROCESS =======");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
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
        System.out.println("\n=== LOGIN PROCESS ===");

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
}

