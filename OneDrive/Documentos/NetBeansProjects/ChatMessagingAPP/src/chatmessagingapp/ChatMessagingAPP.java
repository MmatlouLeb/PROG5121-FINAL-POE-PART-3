/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatmessagingapp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
/**
 *
 * @author mmatl
 */
public class ChatMessagingAPP {

    /** This is for registering new users. */
    private final Registration REGISTRATION;

    /** This is for checking user login info. */
    private final Login LOGIN;

    /** This is the screen where users type their info to register. */
    private final RegistrationForm REGISTRATION_FORM;

    /** This is the screen where users type their info to login. */
    private final LoginForm LOGIN_FORM;
/** This is the screen where users type and send messages. */
private final MessagesForm MESSAGES_FORM = null;

    /**
     * Makes a new chatMessagingAPP. 
     * Sets up registration, login, and their screens so the app can work.
     */
    public ChatMessagingAPP() {
        REGISTRATION = new Registration(); // Make the registration stuff
        LOGIN = new Login(REGISTRATION); // Give registration to login
        REGISTRATION_FORM = new RegistrationForm(REGISTRATION, this);
        LOGIN_FORM = new LoginForm(LOGIN, this);
        MessagesForm MESSAGES_FORM = new MessagesForm(this);

    }
 
    /**
     * Starts the app by showing the registration screen.
     * Makes it appear in the middle of the screen and closes when you click X.
     */
    public void start() {
        REGISTRATION_FORM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        REGISTRATION_FORM.setLocationRelativeTo(null); // Put it in the middle
        REGISTRATION_FORM.setVisible(true); // Show it
    }

    /**
     * Shows the login screen after someone registers.
     * Puts it in the middle and closes app when click X.
     */
    public void showLoginForm() {
        LOGIN_FORM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LOGIN_FORM.setLocationRelativeTo(null); // Middle of screen
        LOGIN_FORM.setVisible(true);                   
    }

    /**
     * Shows the messaging feature.
     */
    public void showMessageFeature() {
        String currentUser = LOGIN.getUsername();
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat, " + currentUser + "!");

        // Step 3️⃣ — Menu loop
        int choice = 0;
        do {
            String menu = """
            Please choose an option:
            1) Send Message
            2) Show Recently Sent Messages
            3) Quit
            """;

            try {
                choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // Ask how many messages AFTER user selects Option 1
                    int totalMessages = 0;
                    boolean valid = false;
                    while (!valid) {
                        try {
                            totalMessages = Integer.parseInt(JOptionPane.showInputDialog(
                                    "How many messages would you like to send?"));
                            if (totalMessages > 0) valid = true;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                        }
                    }

                    sendMessages(totalMessages); // Call sendMessages with the chosen number
                }
                case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon");
                case 3 -> {
    JOptionPane.showMessageDialog(null, "Goodbye!");
    System.exit(0);  // Quit after saying goodbye
}

                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }

        } while (choice != 3);
    }

    // Helper method to handle sending messages
    private void sendMessages(int totalMessages) {
        for (int i = 0; i < totalMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient cell number (e.g., +27123456789):");
            String messageContent = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            Messages msg = new Messages(recipient, messageContent);

            String[] options = {"Send Message", "Store Message", "Disregard Message"};
            int action = JOptionPane.showOptionDialog(
                    null,
                    "Choose an action for this message:",
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (action == 0) { // Send Message
                String result = msg.sentMessage();
                if (result.equals("Message successfully sent.")) {
                    msg.storeMessage(); // Save JSON file
                    JOptionPane.showMessageDialog(null,
                            "Message Sent Successfully!\n\n" +
                                    "Message ID: " + msg.getMessageID() + "\n" +
                                    "Message Hash: " + msg.getMessageHash() + "\n" +
                                    "Recipient: " + msg.getMessageRecipient() + "\n" +
                                    "Message: " + msg.getMessagePayload());
                } else {
                    JOptionPane.showMessageDialog(null, result);
                }

            } else if (action == 1) { // Store Message for later
                String storeStatus = msg.storeMessage();
                JOptionPane.showMessageDialog(null, storeStatus);

            } else { // Disregard
                JOptionPane.showMessageDialog(null, "Message disregarded.");
            }
        }

        JOptionPane.showMessageDialog(null, 
                "You have sent " + Messages.returnTotalMessages() + " message(s) in total.");
    }

    /**
     * Main method: starts the app
     */
    public static void main(String[] args) {
        ChatMessagingAPP chatMessagingAPP = new ChatMessagingAPP();
        chatMessagingAPP.start();
    }
}
