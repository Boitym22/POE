/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;

import javax.swing.JOptionPane;
import java.util.Random;

// JSON IMPORTS
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// FILE IMPORTS
import java.io.FileWriter;
import java.io.IOException;

public class ChatApp {

    // =========================
    // GLOBAL VARIABLES
    // =========================

    static int numMessagesSent = 0;

    static JSONArray messageList = new JSONArray();

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        // =========================
        // REGISTRATION
        // =========================

        String firstName = JOptionPane.showInputDialog(
                "Enter First Name:");

        String lastName = JOptionPane.showInputDialog(
                "Enter Last Name:");

        // USERNAME
        String username = JOptionPane.showInputDialog(
                """
                Create Username

                Rules:
                - Must contain _
                - Must not exceed 5 characters
                """);

        while (!checkUsername(username)) {

            JOptionPane.showMessageDialog(null,
                    "Username is not correctly formatted.");

            username = JOptionPane.showInputDialog(
                    "Enter Username Again:");
        }

        JOptionPane.showMessageDialog(null,
                "Username successfully captured.");

        // PASSWORD
        String password = JOptionPane.showInputDialog(
                """
                Create Password

                Password must contain:
                - At least 8 characters
                - Capital letter
                - Number
                - Special character
                """);

        while (!checkPassword(password)) {

            JOptionPane.showMessageDialog(null,
                    "Password is not correctly formatted.");

            password = JOptionPane.showInputDialog(
                    "Enter Password Again:");
        }

        JOptionPane.showMessageDialog(null,
                "Password successfully captured.");

        // CELLPHONE
        String cellphone = JOptionPane.showInputDialog(
                """
                Enter cellphone number

                Example:
                +27831234567
                """);

        while (!checkCellphone(cellphone)) {

            JOptionPane.showMessageDialog(null,
                    "Cellphone number incorrectly formatted.");

            cellphone = JOptionPane.showInputDialog(
                    "Enter cellphone again:");
        }

        JOptionPane.showMessageDialog(null,
                "Cellphone successfully captured.");

        JOptionPane.showMessageDialog(null,
                "Registration Successful!");

        // =========================
        // LOGIN
        // =========================

        String loginUsername = JOptionPane.showInputDialog(
                "Enter Username:");

        String loginPassword = JOptionPane.showInputDialog(
                "Enter Password:");

        if (loginUsername.equals(username)
                && loginPassword.equals(password)) {

            JOptionPane.showMessageDialog(null,
                    "Welcome "
                            + firstName
                            + " "
                            + lastName
                            + ", it is great to see you again.");

        } else {

            JOptionPane.showMessageDialog(null,
                    "Username or password incorrect.");

            System.exit(0);
        }

        // =========================
        // CHAT MENU
        // =========================

        boolean running = true;

        while (running) {

            String menu = JOptionPane.showInputDialog(
                    """
                    Choose an option:

                    1. Send Message
                    2. Show recently sent messages
                    3. Quit
                    """);

            switch (menu) {

                // =========================
                // SEND MESSAGE
                // =========================

                case "1":

                    String recipient = JOptionPane.showInputDialog(
                            """
                            Enter recipient number

                            Example:
                            +27685139480
                            """);

                    // VALIDATE RECIPIENT
                    if (!recipient.matches("^\\+27\\d{9}$")) {

                        JOptionPane.showMessageDialog(null,
                                "Cell number is incorrectly formatted.");

                        break;
                    }

                    // ENTER MESSAGE
                    String message = JOptionPane.showInputDialog(
                            "Enter your message:");

                    // MESSAGE VALIDATION
                    if (message.length() > 250) {

                        JOptionPane.showMessageDialog(null,
                                "Please enter a message of less than 250 characters.");

                        break;
                    }

                    // GENERATE MESSAGE ID
                    String messageID = generateMessageID();

                    // COUNT MESSAGE
                    numMessagesSent++;

                    // GENERATE HASH
                    String messageHash =
                            createMessageHash(
                                    messageID,
                                    numMessagesSent,
                                    message);

                    // =========================
                    // JSON STORAGE
                    // =========================

                    JSONObject messageObject =
                            new JSONObject();

                    messageObject.put(
                            "MessageID",
                            messageID);

                    messageObject.put(
                            "Recipient",
                            recipient);

                    messageObject.put(
                            "Message",
                            message);

                    messageObject.put(
                            "MessageHash",
                            messageHash);

                    // ADD TO ARRAY
                    messageList.add(messageObject);

                    // SAVE JSON FILE
                    try {

                        FileWriter file =
                                new FileWriter("messages.json");

                        file.write(
                                messageList.toJSONString());

                        file.flush();

                        file.close();

                    } catch (IOException e) {

                        JOptionPane.showMessageDialog(null,
                                "Error saving messages.");
                    }

                    // SUCCESS MESSAGE
                    JOptionPane.showMessageDialog(null,
                            "Message sent successfully!"
                                    + "\n\nMessage ID: "
                                    + messageID
                                    + "\nMessage Hash: "
                                    + messageHash
                                    + "\nMessages Sent: "
                                    + numMessagesSent);

                    break;

                // =========================
                // COMING SOON
                // =========================

                case "2":

                    JOptionPane.showMessageDialog(null,
                            "Coming Soon.");

                    break;

                // =========================
                // QUIT
                // =========================

                case "3":

                    JOptionPane.showMessageDialog(null,
                            "Goodbye!");

                    running = false;

                    break;

                // =========================
                // INVALID OPTION
                // =========================

                default:

                    JOptionPane.showMessageDialog(null,
                            "Invalid option.");
            }
        }
    }

    // =========================
    // USERNAME VALIDATION
    // =========================

    public static boolean checkUsername(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    // =========================
    // PASSWORD VALIDATION
    // =========================

    public static boolean checkPassword(String password) {

        return password.matches(
                "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=<>?/]).{8,}$");
    }

    // =========================
    // CELLPHONE VALIDATION
    // =========================

    public static boolean checkCellphone(String cellphone) {

        return cellphone.matches("^\\+27\\d{9}$");
    }

    // =========================
    // GENERATE MESSAGE ID
    // =========================

    public static String generateMessageID() {

        Random random = new Random();

        long number =
                1000000000L
                        + (long)
                        (random.nextDouble()
                                * 9000000000L);

        return String.valueOf(number);
    }

    // =========================
    // CREATE MESSAGE HASH
    // =========================

    public static String createMessageHash(
            String messageID,
            int messageNumber,
            String message) {

        String[] words =
                message.trim().split("\\s+");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1]
                        .toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }
}

