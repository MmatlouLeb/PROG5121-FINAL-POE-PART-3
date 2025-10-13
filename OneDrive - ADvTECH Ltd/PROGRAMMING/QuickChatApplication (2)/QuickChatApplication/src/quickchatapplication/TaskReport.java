package quickchatapplication;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * TaskReport class
 * Handles storing, searching, deleting, and reporting messages
 *
 * Changes applied:
 * - Removed duplicate methods and redundant code.
 * - loadTestData() resets the message counter for deterministic hashes in tests.
 * - loadTestData() no longer writes test data to JSON files (so real files aren't overwritten).
 * - Unified deleteByHash logic: a UI method prompts & calls the shared delete method.
 */
public class TaskReport {

    // Enable test mode to prevent file I/O and GUI popups during unit tests / CI
    public static boolean TEST_MODE = false;

    // Arrays for message storage
    static ArrayList<messages> sentMessages = new ArrayList<>();
    static ArrayList<messages> storedMessages = new ArrayList<>();
    static ArrayList<messages> disregardedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        // Default behavior for tests: load data but do not open GUI menu.
        if (TEST_MODE) {
            loadTestData();
        } else {
            // Normal startup reads persisted messages if present
            readSentMessages();
            readStoredMessages();
            readDisregardedMessages();
        }

        if (args != null && args.length > 0 && "run".equalsIgnoreCase(args[0])) {
            runReportMenu();
        }
    }

    public static void runReportMenu() {
        // ensure stored messages are loaded
        if (!TEST_MODE) {
            readSentMessages();
            readStoredMessages();
            readDisregardedMessages();
        } else {
            // in TEST_MODE keep sample test data deterministic
            loadTestData();
        }

        boolean running = true;

        while (running) {
            String choiceStr = JOptionPane.showInputDialog("""
                    TASK REPORT MENU 
                    1) Display senders and recipients
                    2) Display longest sent message
                    3) Search by message ID
                    4) Search all messages to a recipient
                    5) Delete message by hash
                    6) Display full report of SENT messages
                    0) Exit
                    """);

            if (choiceStr == null) return;
            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> displaySendersRecipients();
                case 2 -> displayLongestMessage();
                case 3 -> searchByID();
                case 4 -> searchByRecipient();
                case 5 -> deleteByHashUI();
                case 6 -> displayReport(); // now shows SENT messages per POE
                case 0 -> {
                    JOptionPane.showMessageDialog(null, "Exiting Task Report. Goodbye!");
                    running = false;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }

    //  LOAD TEST DATA (uses the assignment testcases exactly)
    static void loadTestData() {
        // Reset in-memory lists
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();

        // Reset message counter so generated hashes are deterministic
        messages.resetMessageCount();

        // Message 1 - Sent
        messages m1 = new messages();
        m1.setId("0000000001");
        m1.setRecipient("+27834557896");
        m1.setText("Did you get the cake?");
        m1.setSender("Developer");
        m1.setStatus("Sent");
        m1.setHash(m1.createMessageHash(m1.getId(), messages.incrementMessageCount(), m1.getText()));
        sentMessages.add(m1);
        messageHashes.add(m1.getHash());
        messageIDs.add(m1.getId());

        // Message 2 - Stored (long message)
        messages m2 = new messages();
        m2.setId("0000000002");
        m2.setRecipient("+27838884567");
        m2.setText("Where are you? You are late! I have asked you to be on time.");
        m2.setSender("Unknown");
        m2.setStatus("Stored");
        m2.setHash(m2.createMessageHash(m2.getId(), messages.incrementMessageCount(), m2.getText()));
        storedMessages.add(m2);
        messageHashes.add(m2.getHash());
        messageIDs.add(m2.getId());

        // Message 3 - Disregard
        messages m3 = new messages();
        m3.setId("0000000003");
        m3.setRecipient("+27834484567");
        m3.setText("Yohoooo, I am at your gate.");
        m3.setSender("Unknown");
        m3.setStatus("Disregard");
        m3.setHash(m3.createMessageHash(m3.getId(), messages.incrementMessageCount(), m3.getText()));
        disregardedMessages.add(m3);
        messageHashes.add(m3.getHash());
        messageIDs.add(m3.getId());

        // Message 4 - Sent (assignment uses Developer 0838884567)
        messages m4 = new messages();
        m4.setId("0838884567"); // keep test expectation intact
        m4.setRecipient("+27831234567"); // valid +27 recipient
        m4.setText("It is dinner time!");
        m4.setSender("Developer");
        m4.setStatus("Sent");
        m4.setHash(m4.createMessageHash(m4.getId(), messages.incrementMessageCount(), m4.getText()));
        sentMessages.add(m4);
        messageHashes.add(m4.getHash());
        messageIDs.add(m4.getId());

        // Message 5 - Stored
        messages m5 = new messages();
        m5.setId("0000000005");
        m5.setRecipient("+27838884567");
        m5.setText("Ok, I am leaving without you.");
        m5.setSender("Unknown");
        m5.setStatus("Stored");
        m5.setHash(m5.createMessageHash(m5.getId(), messages.incrementMessageCount(), m5.getText()));
        storedMessages.add(m5);
        messageHashes.add(m5.getHash());
        messageIDs.add(m5.getId());

        // IMPORTANT: Do not write test data to real JSON files automatically.
        // Writing persisted data should only happen through normal app flows (and not by loading test data).
    }

    // Add message and generate unique hash (used if adding dynamically)
    static void addMessage(String recipient, String text, String flag) {
        String id = messages.generate10DigitID(); // ensures 10 digits
        messages msg = new messages();
        msg.setId(id);
        msg.setRecipient(recipient);
        msg.setText(text);
        msg.setSender("Developer");
        msg.setStatus(flag);
        msg.setHash(msg.createMessageHash(id, messages.incrementMessageCount(), text));

        if (flag.equalsIgnoreCase("Sent")) sentMessages.add(msg);
        else if (flag.equalsIgnoreCase("Stored")) storedMessages.add(msg);
        else if (flag.equalsIgnoreCase("Disregard")) disregardedMessages.add(msg);

        messageHashes.add(msg.getHash());
        messageIDs.add(msg.getId());

        if (!TEST_MODE) {
            if (flag.equalsIgnoreCase("Stored")) saveStoredMessages();
            if (flag.equalsIgnoreCase("Sent")) saveSentMessages();
            if (flag.equalsIgnoreCase("Disregard")) saveDisregardedMessages();
        }
    }

    // READ STORED JSON (one-line arrays per append)
    static void readStoredMessages() {
        if (TEST_MODE) return;
        storedMessages.clear(); // refresh
        try (FileReader reader = new FileReader("stored_messages.json")) {
            JSONParser parser = new JSONParser();
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                Object parsed = parser.parse(line);
                if (!(parsed instanceof JSONArray)) continue;
                JSONArray arr = (JSONArray) parsed;
                for (Object obj : arr) {
                    JSONObject msgObject = (JSONObject) obj;

                    String id = (String) msgObject.getOrDefault("MessageID", "");
                    String recipient = (String) msgObject.getOrDefault("Recipient", "");
                    String message = (String) msgObject.getOrDefault("Message", "");
                    String hash = (String) msgObject.getOrDefault("MessageHash", "");
                    String status = (String) msgObject.getOrDefault("Status", "Stored");
                    String sender = (String) msgObject.getOrDefault("Sender", "Unknown");

                    messages msg = new messages();
                    msg.setId(id);
                    msg.setRecipient(recipient);
                    msg.setText(message);
                    msg.setHash(hash);
                    msg.setStatus(status);
                    msg.setSender(sender);

                    storedMessages.add(msg);
                    if (hash != null && !messageHashes.contains(hash)) messageHashes.add(hash);
                    if (id != null && !messageIDs.contains(id)) messageIDs.add(id);
                }
            }
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Stored messages loaded successfully from JSON.");
            }
        } catch (IOException e) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "No stored_messages.json file found yet.");
            }
        } catch (ParseException e) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Error reading stored messages file.");
            }
        }
    }

    // READ sent messages JSON (if persisted)
    static void readSentMessages() {
        if (TEST_MODE) return;
        sentMessages.clear();
        try (FileReader reader = new FileReader("sent_messages.json")) {
            JSONParser parser = new JSONParser();
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                Object parsed = parser.parse(line);
                if (!(parsed instanceof JSONArray)) continue;
                JSONArray arr = (JSONArray) parsed;
                for (Object obj : arr) {
                    JSONObject msgObject = (JSONObject) obj;

                    String id = (String) msgObject.getOrDefault("MessageID", "");
                    String recipient = (String) msgObject.getOrDefault("Recipient", "");
                    String message = (String) msgObject.getOrDefault("Message", "");
                    String hash = (String) msgObject.getOrDefault("MessageHash", "");
                    String status = (String) msgObject.getOrDefault("Status", "Sent");
                    String sender = (String) msgObject.getOrDefault("Sender", "Developer");

                    messages msg = new messages();
                    msg.setId(id);
                    msg.setRecipient(recipient);
                    msg.setText(message);
                    msg.setHash(hash);
                    msg.setStatus(status);
                    msg.setSender(sender);

                    sentMessages.add(msg);
                    if (hash != null && !messageHashes.contains(hash)) messageHashes.add(hash);
                    if (id != null && !messageIDs.contains(id)) messageIDs.add(id);
                }
            }
        } catch (IOException | ParseException ignored) {
            // ignore in tests; file may not exist yet
        }
    }

    // READ disregarded messages JSON
    static void readDisregardedMessages() {
        if (TEST_MODE) return;
        disregardedMessages.clear();
        try (FileReader reader = new FileReader("disregarded_messages.json")) {
            JSONParser parser = new JSONParser();
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                Object parsed = parser.parse(line);
                if (!(parsed instanceof JSONArray)) continue;
                JSONArray arr = (JSONArray) parsed;
                for (Object obj : arr) {
                    JSONObject msgObject = (JSONObject) obj;

                    String id = (String) msgObject.getOrDefault("MessageID", "");
                    String recipient = (String) msgObject.getOrDefault("Recipient", "");
                    String message = (String) msgObject.getOrDefault("Message", "");
                    String hash = (String) msgObject.getOrDefault("MessageHash", "");
                    String status = (String) msgObject.getOrDefault("Status", "Disregard");
                    String sender = (String) msgObject.getOrDefault("Sender", "Unknown");

                    messages msg = new messages();
                    msg.setId(id);
                    msg.setRecipient(recipient);
                    msg.setText(message);
                    msg.setHash(hash);
                    msg.setStatus(status);
                    msg.setSender(sender);

                    disregardedMessages.add(msg);
                    if (hash != null && !messageHashes.contains(hash)) messageHashes.add(hash);
                    if (id != null && !messageIDs.contains(id)) messageIDs.add(id);
                }
            }
        } catch (IOException | ParseException ignored) {
            // ignore in tests; file may not exist yet
        }
    }

    // Save storedMessages list to stored_messages.json (overwrites with current storedMessages)
    @SuppressWarnings("unchecked")
    static void saveStoredMessages() {
        if (TEST_MODE) return;
        JSONArray outArr = new JSONArray();
        for (messages m : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", m.getId());
            obj.put("Recipient", m.getRecipient());
            obj.put("Message", m.getText());
            obj.put("MessageHash", m.getHash());
            obj.put("Status", m.getStatus());
            obj.put("Sender", m.getSender());
            outArr.add(obj);
        }

        try (FileWriter fw = new FileWriter("stored_messages.json", false)) {
            fw.write(outArr.toJSONString());
            fw.write(System.lineSeparator());
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Stored messages saved to JSON.");
            }
        } catch (IOException e) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Error saving stored messages: " + e.getMessage());
            }
        }
    }

    // Save sentMessages
    @SuppressWarnings("unchecked")
    static void saveSentMessages() {
        if (TEST_MODE) return;
        JSONArray outArr = new JSONArray();
        for (messages m : sentMessages) {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", m.getId());
            obj.put("Recipient", m.getRecipient());
            obj.put("Message", m.getText());
            obj.put("MessageHash", m.getHash());
            obj.put("Status", m.getStatus());
            obj.put("Sender", m.getSender());
            outArr.add(obj);
        }

        try (FileWriter fw = new FileWriter("sent_messages.json", false)) {
            fw.write(outArr.toJSONString());
            fw.write(System.lineSeparator());
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Sent messages saved to JSON.");
            }
        } catch (IOException e) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Error saving sent messages: " + e.getMessage());
            }
        }
    }

    // Save disregardedMessages
    @SuppressWarnings("unchecked")
    static void saveDisregardedMessages() {
        if (TEST_MODE) return;
        JSONArray outArr = new JSONArray();
        for (messages m : disregardedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", m.getId());
            obj.put("Recipient", m.getRecipient());
            obj.put("Message", m.getText());
            obj.put("MessageHash", m.getHash());
            obj.put("Status", m.getStatus());
            obj.put("Sender", m.getSender());
            outArr.add(obj);
        }

        try (FileWriter fw = new FileWriter("disregarded_messages.json", false)) {
            fw.write(outArr.toJSONString());
            fw.write(System.lineSeparator());
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Disregarded messages saved to JSON.");
            }
        } catch (IOException e) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, "Error saving disregarded messages: " + e.getMessage());
            }
        }
    }

    //  REPORT MENU FUNCTIONS
    static void displaySendersRecipients() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages found.");
            return;
        }

        StringBuilder sb = new StringBuilder("Senders and Recipients (Sent Messages):\n\n");

        for (messages msg : sentMessages) {
            sb.append("Sender: ").append(msg.getSender() == null ? "Developer" : msg.getSender())
              .append("\nRecipient: ").append(msg.getRecipient())
              .append("\n----------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    static void displayLongestMessage() {
        // consider all lists
        ArrayList<messages> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        if (all.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages found.");
            return;
        }
        messages longest = all.get(0);
        for (messages msg : all) {
            if (msg.getText() != null && msg.getText().length() > (longest.getText() == null ? 0 : longest.getText().length())) {
                longest = msg;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.getText());
    }

    static void searchByID() {
        String id = JOptionPane.showInputDialog("Enter message ID:");
        if (id == null) return;

        ArrayList<messages> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        for (messages msg : all) {
            if (msg.getId() != null && msg.getId().equals(id)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Message ID: ").append(msg.getId()).append("\n")
                  .append("Sender: ").append(msg.getSender()).append("\n")
                  .append("Recipient: ").append(msg.getRecipient()).append("\n")
                  .append("Status: ").append(msg.getStatus()).append("\n")
                  .append("Message: ").append(msg.getText()).append("\n")
                  .append("Message Hash: ").append(msg.getHash());
                JOptionPane.showMessageDialog(null, sb.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }

    static void searchByRecipient() {
        String rec = JOptionPane.showInputDialog("Enter recipient number:");
        if (rec == null) return;

        StringBuilder sb = new StringBuilder("Messages for " + rec + ":\n");
        boolean found = false;
        ArrayList<messages> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);
        allMessages.addAll(disregardedMessages);

        for (messages msg : allMessages) {
            if (msg.getRecipient() != null && msg.getRecipient().equals(rec)) {
                sb.append("- ").append(msg.getText()).append("\n");
                found = true;
            }
        }
        if (found) JOptionPane.showMessageDialog(null, sb.toString());
        else JOptionPane.showMessageDialog(null, "No messages found for that recipient.");
    }

    // UI wrapper that prompts the user and calls the deletion routine
    static void deleteByHashUI() {
        String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
        if (hash == null) return;

        boolean deleted = deleteByHash(hash);

        if (deleted) {
            // Update messageHashes list
            messageHashes.removeIf(h -> h.equals(hash));
            // Save all lists in case we deleted persisted items
            if (!TEST_MODE) {
                saveStoredMessages();
                saveSentMessages();
                saveDisregardedMessages();
            }
            JOptionPane.showMessageDialog(null, "Message with hash " + hash + " successfully deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Message hash not found.");
        }
    }

    private static boolean removeByHashFromList(ArrayList<messages> list, String hash) {
        Iterator<messages> iterator = list.iterator();
        while (iterator.hasNext()) {
            messages msg = iterator.next();
            if (msg.getHash() != null && msg.getHash().equals(hash)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Core deletion routine used by UI and by tests/programmatic calls
    public static boolean deleteByHash(String hash) {
        boolean deleted = false;
        deleted |= removeByHashFromList(sentMessages, hash);
        deleted |= removeByHashFromList(storedMessages, hash);
        deleted |= removeByHashFromList(disregardedMessages, hash);
        return deleted;
    }

    // Display a report that lists the full details of all the SENT messages (per POE)
    static void displayReport() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages to display.");
            return;
        }

        StringBuilder sb = new StringBuilder(" Full Sent Messages Report \n");
        for (messages msg : sentMessages) {
            sb.append("Message ID: ").append(msg.getId()).append("\n")
              .append("Sender: ").append(msg.getSender()).append("\n")
              .append("Recipient: ").append(msg.getRecipient()).append("\n")
              .append("Message: ").append(msg.getText()).append("\n")
              .append("Message Hash: ").append(msg.getHash()).append("\n")
              .append("-----------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


    // ---------- Helper text-returning methods (useful for unit tests) ----------
    public static String getLongestMessageText() {
        ArrayList<messages> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        if (all.isEmpty()) return "";

        messages longest = all.get(0);
        for (messages msg : all) {
            if (msg.getText() != null &&
                msg.getText().length() > (longest.getText() == null ? 0 : longest.getText().length())) {
                longest = msg;
            }
        }
        return longest.getText();
    }

    public static String searchByIDText(String id) {
        ArrayList<messages> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        for (messages msg : all) {
            if (msg.getId() != null && msg.getId().equals(id)) {
                return "Message ID: " + msg.getId() + "\n" +
                       "Sender: " + msg.getSender() + "\n" +
                       "Recipient: " + msg.getRecipient() + "\n" +
                       "Status: " + msg.getStatus() + "\n" +
                       "Message: " + msg.getText() + "\n" +
                       "Message Hash: " + msg.getHash();
            }
        }
        return "Message ID not found.";
    }

    public static String searchByRecipientText(String rec) {
        StringBuilder sb = new StringBuilder();
        ArrayList<messages> all = new ArrayList<>();
        all.addAll(sentMessages);
        all.addAll(storedMessages);
        all.addAll(disregardedMessages);

        for (messages msg : all) {
            if (msg.getRecipient() != null && msg.getRecipient().equals(rec)) {
                sb.append(msg.getText()).append("\n");
            }
        }

        return sb.toString().trim();
    }

    public static String getFullSentReportText() {
        StringBuilder sb = new StringBuilder();

        for (messages msg : sentMessages) {
            sb.append("Message ID: ").append(msg.getId()).append("\n")
              .append("Sender: ").append(msg.getSender()).append("\n")
              .append("Recipient: ").append(msg.getRecipient()).append("\n")
              .append("Message: ").append(msg.getText()).append("\n")
              .append("Message Hash: ").append(msg.getHash()).append("\n")
              .append("-----------------------------\n");
        }

        return sb.toString();
    }
}
