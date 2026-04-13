
package chatapp;

import java.util.Scanner;

public class Login {
    

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();
System.out.println("=====================================");
System.out.println("        💬 CHAT APPLICATION 💬        ");
System.out.println("=====================================");
System.out.println("       Welcome to Chat App!");
System.out.println("=====================================\n");
        
        
        System.out.println("=== REGISTRATION ===");

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
                System.out.print("Password is not correctly formatted, please ensure it meets all requirements.");
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
        if (login.registerUser(firstName, username, password, cell)) {
    System.out.println("\n🎉 Registration Successful!");
    System.out.println("Welcome, " + firstName + " 👋");
} else {
    System.out.println("\n⚠ Registration Failed. Please try again.");
}
    }
    private Object storedPass;
    private Object storedUser;
    private boolean status;

    boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

   boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        if (password.length() < 8) return false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27\\d{9}$"); // SA format: +27 followed by 9 digits
}

    boolean registerUser(String firstName, String username, String password, String cell) {
         return checkUsername(username) &&
           checkPasswordComplexity(password) &&
           checkCellPhoneNumber(cell);
}

    boolean login(String loginUser, String loginPass) {
          return loginUser.equals(storedUser) && loginPass.equals(storedPass);
}

    boolean returnLoginStatus(boolean b) {
        return status;
}
}


