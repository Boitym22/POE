/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;
import java.util.Scanner;
import java.util.Random;

// JSON IMPORTS
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;


// FILE IMPORTS
import java.io.FileWriter;
import java.io.IOException;

public class ChatApp {
    

    // =========================
    // GLOBAL VARIABLES
    // =========================

    static int numMessagesSent = 0;

   static JSONArray messageList = new JSONArray();

static String[] messageIDs = new String[100];
static String[] recipients = new String[100];
static String[] messages = new String[100];
static String[] messageHashes = new String[100];
static String[] statuses = new String[100];

static int messageCount = 0;

    

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // =========================
        // REGISTRATION
        // =========================

    System.out.print("Enter First Name: ");
    String firstName = input.nextLine();

    System.out.print("Enter Last Name: ");
    String lastName = input.nextLine();

        // USERNAME
    System.out.println("Create Username");
    System.out.println("Rules:");
    System.out.println("- Must contain _");
    System.out.println("- Must not exceed 5 characters");
    System.out.print("Username: ");

    String username = input.nextLine();

    while (!checkUsername(username)) {

    System.out.println("Username is not correctly formatted.");

    System.out.print("Enter Username Again: ");
    username = input.nextLine();
}

    System.out.println("Username successfully captured.");

        // PASSWORD
System.out.println("Create Password");
System.out.println("- At least 8 characters");
System.out.println("- Capital letter");
System.out.println("- Number");
System.out.println("- Special character");
System.out.print("Password: ");

String password = input.nextLine();

while (!checkPassword(password)) {

    System.out.println("Password is not correctly formatted.");

    System.out.print("Enter Password Again: ");
    password = input.nextLine();
}

System.out.println("Password successfully captured.");

        // CELLPHONE
    System.out.print("Enter cellphone number (+27831234567): ");
    String cellphone = input.nextLine();

while (!checkCellphone(cellphone)) {

    System.out.println("Cellphone number incorrectly formatted.");

    System.out.print("Enter cellphone again: ");
    cellphone = input.nextLine();
}

System.out.println("Cellphone successfully captured.");
System.out.println("Registration Successful!");

        // =========================
        // LOGIN
        // =========================

    System.out.print("Enter Username: ");
    String loginUsername = input.nextLine();

    System.out.print("Enter Password: ");
    String loginPassword = input.nextLine();

        if (loginUsername.equals(username)
                && loginPassword.equals(password)) {

        System.out.println(
        "Welcome "
        + firstName
        + " "
        + lastName
        + ", it is great to see you again.");

        } else {

        System.out.println("Username or password incorrect.");

            System.exit(0);
        }

        // =========================
        // CHAT MENU
        // =========================
        System.out.print("How many messages would you like to send? ");
        int maxMessages = Integer.parseInt(input.nextLine());
        boolean running = true;

        while (running) {

System.out.println("\n===== QUICKCHAT MENU =====");
System.out.println("1. Send Message");
System.out.println("2. Display Sent Messages");
System.out.println("3. Read Stored Messages");
System.out.println("4. Display Longest Message");
System.out.println("5. Search Message ID");
System.out.println("6. Search Recipient");
System.out.println("7. Delete Message By Hash");
System.out.println("8. Full Report");
System.out.println("9. Quit");
System.out.print("Choose option: ");

String menu = input.nextLine();

switch (menu) {

    case "1" -> {
        if(numMessagesSent >= maxMessages){

            System.out.println(
            "You have reached the maximum number of messages.");

            break;
        }

        System.out.print("Enter recipient number (+27xxxxxxxxx): ");
        String recipient = input.nextLine();

        if (!recipient.matches("^\\+27\\d{9}$")) {

            System.out.println("Cell number is incorrectly formatted.");
            break;
        }

        System.out.print("Enter your message: ");
        String message = input.nextLine();

        if(message.trim().isEmpty()){

            System.out.println("Message cannot be empty.");
            break;
        }

        System.out.println("1. Send Message");
        System.out.println("2. Store Message");
        System.out.println("3. Disregard Message");
        System.out.print("Choose option: ");

        String action = input.nextLine();

        String status;

        switch(action){
            case "1" -> status = "Sent";
            case "2" -> status = "Stored";
            case "3" -> status = "Disregarded";
            default -> status = "Unknown";
        }

        if(message.length() > 250){

            System.out.println(
            "Please enter a message of less than 250 characters.");
            break;
        }

        String messageID = generateMessageID();

        numMessagesSent++;

        String messageHash =
                createMessageHash(
                        messageID,
                        numMessagesSent,
                        message);

        JSONObject messageObject = new JSONObject();

        messageObject.put("MessageID", messageID);
        messageObject.put("Status", status);
        messageObject.put("Recipient", recipient);
        messageObject.put("Message", message);
        messageObject.put("MessageHash", messageHash);

        messageList.add(messageObject);

        messageIDs[messageCount] = messageID;
        recipients[messageCount] = recipient;
        messages[messageCount] = message;
        messageHashes[messageCount] = messageHash;
        statuses[messageCount] = status;

        messageCount++;

        try {

            FileWriter file =
                    new FileWriter("messages.json");

            file.write(messageList.toJSONString());

            file.flush();
            file.close();

        } catch (IOException e) {

            System.out.println("Error saving messages.");
        }

        System.out.println("Message sent successfully!");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Messages Sent: " + numMessagesSent);
    }

    case "2" -> {

        String report = "";

        for(int i = 0; i < messageCount; i++){

            if(statuses[i].equals("Sent")){

                report +=
                        "Recipient: "
                        + recipients[i]
                        + "\nMessage: "
                        + messages[i]
                        + "\n\n";
            }
        }

        if(report.equals("")){

            report = "No sent messages.";
        }

        System.out.println(report);
    }

    case "3" -> {
        readStoredMessages();
    }

    case "4" -> {
        System.out.println(getLongestMessage());
    }

    case "5" -> {

        System.out.print("Enter Message ID: ");
        String id = input.nextLine();

        System.out.println(searchMessageID(id));
    }

    case "6" -> {

        System.out.print("Enter Recipient: ");
        String recipientSearch = input.nextLine();

        System.out.println(searchRecipient(recipientSearch));
    }

    case "7" -> {

        System.out.print("Enter Hash: ");
        String hash = input.nextLine();

        deleteMessage(hash);
    }

    case "8" -> {
        System.out.println(generateReport());
    }

    case "9" -> {

        System.out.println("Goodbye!");
        running = false;
    }

    default -> System.out.println("Invalid option.");
}
            // =========================
            // SEND MESSAGE
            // =========================
            // =========================
            // COMING SOON
            // =========================
            // =========================
            // QUIT
            // =========================
            // =========================
            // INVALID OPTION
            // =========================
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

    public static String searchRecipient(String recipientSearch) {

    String result = "";

    for(int i = 0; i < messageCount; i++){

        if(recipients[i].equals(recipientSearch)){

            result +=
                    "Recipient: "
                    + recipients[i]
                    + "\nMessage: "
                    + messages[i]
                    + "\n\n";
        }
    }

    if(result.equals("")){

        return "Recipient not found.";
    }

    return result;
}
    public static String searchMessageID(String id) {

    for(int i = 0; i < messageCount; i++){

        if(messageIDs[i].equals(id)){

            return
                    "Message ID: "
                    + messageIDs[i]
                    + "\nRecipient: "
                    + recipients[i]
                    + "\nMessage: "
                    + messages[i]
                    + "\nHash: "
                    + messageHashes[i];
        }
    }

    return "Message ID not found.";
}

    private static void deleteMessage(String hash) {

    for(int i = 0; i < messageCount; i++){

        if(messageHashes[i].equals(hash)){

            for(int j = i; j < messageCount - 1; j++){

                messageIDs[j] = messageIDs[j + 1];
                recipients[j] = recipients[j + 1];
                messages[j] = messages[j + 1];
                messageHashes[j] = messageHashes[j + 1];
                statuses[j] = statuses[j + 1];
            }

            messageCount--;

            System.out.println(
            "Message deleted successfully.");

            return;
        }
    }

            System.out.println(
            "Hash not found.");
}

    private static String generateReport() {

    if(messageCount == 0){

        return "No messages available.";
    }

    String report = "";

    for(int i = 0; i < messageCount; i++){

        report +=
                "Message ID: " + messageIDs[i]
                + "\nRecipient: " + recipients[i]
                + "\nMessage: " + messages[i]
                + "\nHash: " + messageHashes[i]
                + "\nStatus: " + statuses[i]
                + "\n\n";
    }

    return report;
}
    public static void readStoredMessages() {

    try {

        JSONParser parser = new JSONParser();

        JSONArray storedMessages =
                (JSONArray) parser.parse(
                        new FileReader("messages.json"));

        if(storedMessages.isEmpty()) {

            System.out.println("No stored messages found.");
            return;
        }

        System.out.println("\n===== STORED MESSAGES =====");

        for(Object obj : storedMessages) {

            JSONObject msg = (JSONObject) obj;

            System.out.println(
                    "Message ID: "
                    + msg.get("MessageID"));

            System.out.println(
                    "Recipient: "
                    + msg.get("Recipient"));

            System.out.println(
                    "Message: "
                    + msg.get("Message"));

            System.out.println(
                    "Hash: "
                    + msg.get("MessageHash"));

            System.out.println(
                    "Status: "
                    + msg.get("Status"));

            System.out.println("--------------------------------");
        }

    } catch(Exception e) {

        System.out.println(
                "No stored messages file found.");
    }
}
    public static String getLongestMessage() {

    if(messageCount == 0){

        return "No messages available.";
    }

    String longest = messages[0];

    for(int i = 1; i < messageCount; i++){

        if(messages[i].length() > longest.length()){

            longest = messages[i];
        }
    }

    return longest;
}
}

