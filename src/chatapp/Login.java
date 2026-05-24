package chatapp;

import org.json.simple.JSONObject;

public class Login {

    private String storedUser;
    private String storedPass;
    private String storedFirstName;
    private String storedCell;

    // Username validation
    boolean checkUsername(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    // Password validation
    boolean checkPasswordComplexity(String password) {

        if (password == null) {
            return false;
        }

        // Minimum length
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {

                hasUpper = true;
            }
            else if (Character.isDigit(c)) {

                hasDigit = true;
            }
            else if (!Character.isLetterOrDigit(c)) {

                hasSpecial = true;
            }
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    // Cell phone validation
    boolean checkCellPhoneNumber(String cell) {

        return cell.matches("^\\+27\\d{9}$");
    }

    // Register user
    boolean registerUser(String firstName,
                         String username,
                         String password,
                         String cell) {

        if (checkUsername(username)
                && checkPasswordComplexity(password)
                && checkCellPhoneNumber(cell)) {

            storedFirstName = firstName;
            storedUser = username;
            storedPass = password;
            storedCell = cell;

            return true;
        }

        return false;
    }

    // Login method
    boolean login(String loginUser, String loginPass) {

        return loginUser.equals(storedUser)
                && loginPass.equals(storedPass);
    }

    // Login status message
    String returnLoginStatus(boolean success) {

        if (success) {

            return "Welcome "
                    + storedFirstName
                    + ", it is great to see you again.";
        }
        else {

            return "Username or password incorrect, please try again.";
        }
    }

    // JSON Method
    public JSONObject userToJSON() {

        JSONObject user = new JSONObject();

        user.put("First Name", storedFirstName);
        user.put("Username", storedUser);
        user.put("Password", storedPass);
        user.put("Cell Number", storedCell);

        return user;
    }
}

