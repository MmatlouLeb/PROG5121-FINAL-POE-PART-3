/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;
 
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author mmatl
 */
public class Messages {
    

    //  Variables 
    private final String MESSAGE_ID;          // Random unique ID (10 digits)
    private final String MESSAGE_RECIPIENT;   // Cell number
    private final String MESSAGE_PAYLOAD;     // Message text
    private int MESSAGE_INDEX;                // Number of message sent
    private String MESSAGE_HASH;              // Auto-generated hash

    private static int messageCounter = 0;    // Tracks number of messages sent
    private static String lastSentMessage = ""; // Placeholder for last message
    private static final int MAX_PAYLOAD_LENGTH = 250; // Max characters allowed

    //  Constructor 
    public Messages(final String recipient, final String payload) {
        this.MESSAGE_ID = String.format("%010d", (long)(Math.random() * 10000000000L));
        this.MESSAGE_RECIPIENT = recipient;
        this.MESSAGE_PAYLOAD = payload;
        this.MESSAGE_INDEX = 0;
        this.MESSAGE_HASH = "";
    }

    //  Getters 
    public String getMessageID() { return MESSAGE_ID; }
    public String getMessageRecipient() { return MESSAGE_RECIPIENT; }
    public String getMessagePayload() { return MESSAGE_PAYLOAD; }
    public int getMessageIndex() { return MESSAGE_INDEX; }
    public String getMessageHash() { return MESSAGE_HASH; }

    // 
    // 1️⃣ Boolean: checkMessageID()
    // 
    public boolean checkMessageID() {
        return MESSAGE_ID != null && MESSAGE_ID.matches("\\d{10}");
    }

    // 
    // 2️⃣ Int: checkRecipientCell()
    // Ensures number starts with +27 and is followed by 9 digits.
    // 
    public int checkRecipientCell() {
        String regex = "^\\+27\\d{9}$";
        if (Pattern.matches(regex, MESSAGE_RECIPIENT)) {
            return 1; // success
        }
        return 0; // fail
    }

    //
    // 3️⃣ String: validatePayloadLength()
    // Ensures message ≤ 250 chars. Returns success or error message.
    // 
    public String validatePayloadLength() {
        if (MESSAGE_PAYLOAD == null) {
            return "Message exceeds 250 characters by " + (0 - MAX_PAYLOAD_LENGTH) + ", please reduce size.";
        }
        if (MESSAGE_PAYLOAD.length() <= MAX_PAYLOAD_LENGTH) {
            return "Message ready to send.";
        } else {
            int excess = MESSAGE_PAYLOAD.length() - MAX_PAYLOAD_LENGTH;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    // 
    // 4️⃣ String: validateRecipientNumber()
    // Returns messages as per test cases.
    // 
    public String validateRecipientNumber() {
        String regex = "^\\+27\\d{9}$";
        if (Pattern.matches(regex, MESSAGE_RECIPIENT)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 
    // 5️⃣ String: createMessageHash()
    // Format: FirstTwoDigitsOfID:Index:FirstWordLastWord (ALL CAPS)
    // Example: 00:1:HITHANKS
    // 
    public String createMessageHash() {
        if (MESSAGE_ID == null || MESSAGE_PAYLOAD == null || MESSAGE_PAYLOAD.isBlank()) {
            return "";
        }

        String firstTwo = MESSAGE_ID.substring(0, 2);
        String[] words = MESSAGE_PAYLOAD.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        MESSAGE_HASH = (firstTwo + ":" + MESSAGE_INDEX + ":" + firstWord + lastWord).toUpperCase();
        return MESSAGE_HASH;
    }

    // 
    // 6️⃣ String: sentMessage()
    // Allows user to send message after validations.
    // Returns "Message successfully sent." or error message.
    // 
    public String sentMessage() {
        // Validate message length
        String payloadCheck = validatePayloadLength();
        if (!payloadCheck.equals("Message ready to send.")) {
            return payloadCheck;
        }

        // Validate recipient format
        if (checkRecipientCell() == 0) {
            return "Failed to send message: Invalid recipient number.";
        }

        // Validate message ID
        if (!checkMessageID()) {
            return "Failed to send message: Invalid Message ID.";
        }

        // Validate message content
        if (MESSAGE_PAYLOAD == null || MESSAGE_PAYLOAD.trim().isEmpty()) {
            return "Failed to send message: Message content cannot be empty.";
        }

        // All validations passed
        messageCounter++;
        MESSAGE_INDEX = messageCounter;
        MESSAGE_HASH = createMessageHash();

        lastSentMessage = "Message ID: " + MESSAGE_ID +
                "\nRecipient: " + MESSAGE_RECIPIENT +
                "\nMessage: " + MESSAGE_PAYLOAD +
                "\nMessage Hash: " + MESSAGE_HASH +
                "\nMessage Index: " + MESSAGE_INDEX;

        return "Message successfully sent.";
    }

    // 
    // 7️⃣ String: storeMessage()
    // Stores message details in JSON file (message_INDEX.json)
    // 
    public String storeMessage() {
        JSONObject json = new JSONObject();
        json.put("MESSAGE_ID", MESSAGE_ID);
        json.put("MESSAGE_RECIPIENT", MESSAGE_RECIPIENT);
        json.put("MESSAGE_PAYLOAD", MESSAGE_PAYLOAD);
        json.put("MESSAGE_INDEX", MESSAGE_INDEX);
        json.put("MESSAGE_HASH", MESSAGE_HASH);

        String fileName = "message_" + MESSAGE_INDEX + ".json";
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            return "Message successfully stored.";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
        } catch (IOException e) {
            return "Failed to store message: " + e.getMessage();
        }
    }

    // 
    // 8️⃣ String: printMessages()
    // Displays the last message (will be extended in Part 3)
    // 
    public static String printMessages() {
        if (lastSentMessage.isEmpty()) {
            return "No messages sent yet.";
        }
        return lastSentMessage;
    }

    // 
    // 9️⃣ Int: returnTotalMessages()
    // Returns total sent messages.
    // 
    public static int returnTotalMessages() {
        return messageCounter;
    }

    // 
    // Helper: getGeneratedIdNotification()
    // Used in unit tests.
    // 
    public String getGeneratedIdNotification() {
        return "Message ID generated: " + MESSAGE_ID;
    }

    // 
    //  Helper: resetMessageCounterForTesting()
    // Used to reset counter between test cases.
    // 
    public static void resetMessageCounterForTesting() {
        messageCounter = 0;
        lastSentMessage = "";
    }
}
 