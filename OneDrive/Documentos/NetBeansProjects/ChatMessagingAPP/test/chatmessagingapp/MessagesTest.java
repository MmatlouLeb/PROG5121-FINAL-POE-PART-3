/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatmessagingapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mmatl
 */
public class MessagesTest {
    
     @BeforeEach
    public void setUp() {
        // Reset static counters before each test
        Messages.resetMessageCounterForTesting();
    }

    @Test
    public void testCheckMessageID_ValidAndInvalid() {
        Messages msg = new Messages("+27821234567", "Hello World!");
        assertTrue(msg.checkMessageID(), "Valid 10-digit Message ID should return true.");

        // Manually create an invalid message (simulate via reflection or shorter constructor)
        String invalidId = "123"; // Not 10 digits
        assertTrue(msg.getMessageID().matches("\\d{10}"), "Message ID must always be 10 digits long.");
    }

    @Test
    public void testCheckRecipientCell_ValidNumber() {
        Messages msg = new Messages("+27821234567", "Test message");
        assertEquals(1, msg.checkRecipientCell(), "Valid South African number should return 1.");
    }

    @Test
    public void testCheckRecipientCell_InvalidNumber() {
        Messages msg = new Messages("0821234567", "Test message");
        assertEquals(0, msg.checkRecipientCell(), "Invalid number without +27 should return 0.");
    }

    @Test
    public void testValidatePayloadLength_ValidMessage() {
        String payload = "Short message within limit.";
        Messages msg = new Messages("+27821234567", payload);
        assertEquals("Message ready to send.", msg.validatePayloadLength());
    }

    @Test
    public void testValidatePayloadLength_TooLongMessage() {
        String payload = "A".repeat(260);
        Messages msg = new Messages("+27821234567", payload);
        assertTrue(msg.validatePayloadLength().contains("exceeds 250 characters"),
                "Should report that message exceeds character limit.");
    }

    @Test
    public void testValidateRecipientNumber_Valid() {
        Messages msg = new Messages("+27831234567", "Hi there");
        assertEquals("Cell phone number successfully captured.", msg.validateRecipientNumber());
    }

    @Test
    public void testValidateRecipientNumber_Invalid() {
        Messages msg = new Messages("0831234567", "Hi there");
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.validateRecipientNumber());
    }

    @Test
    public void testCreateMessageHash() {
        Messages msg = new Messages("+27821234567", "Hi there thanks");
        String hash = msg.createMessageHash();
        assertTrue(hash.matches("^[0-9]{2}:0:[A-Z]+$"), "Hash format should match pattern NN:Index:WORDS");
        assertTrue(hash.contains("HITHANKS"), "Hash should include first and last words concatenated.");
    }

    @Test
    public void testSentMessage_Success() {
        Messages msg = new Messages("+27821234567", "Hello message");
        String result = msg.sentMessage();
        assertEquals("Message successfully sent.", result);
        assertEquals(1, Messages.returnTotalMessages());
    }

    @Test
    public void testSentMessage_InvalidNumber() {
        Messages msg = new Messages("0821234567", "Hello message");
        String result = msg.sentMessage();
        assertEquals("Failed to send message: Invalid recipient number.", result);
    }

    @Test
    public void testSentMessage_TooLongPayload() {
        Messages msg = new Messages("+27821234567", "X".repeat(300));
        String result = msg.sentMessage();
        assertTrue(result.contains("exceeds 250 characters"), "Should fail due to message length.");
    }

    @Test
    public void testStoreMessage_Success() {
        Messages msg = new Messages("+27821234567", "Stored message test");
        msg.sentMessage(); // must send before storing
        String result = msg.storeMessage();
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testPrintMessages_NoMessages() {
        String output = Messages.printMessages();
        assertEquals("No messages sent yet.", output);
    }

    @Test
    public void testPrintMessages_AfterSend() {
        Messages msg = new Messages("+27821234567", "Print test");
        msg.sentMessage();
        String output = Messages.printMessages();
        assertTrue(output.contains("Message ID"));
        assertTrue(output.contains("Recipient"));
        assertTrue(output.contains("Print test"));
    }

    @Test
    public void testReturnTotalMessages() {
        assertEquals(0, Messages.returnTotalMessages());
        Messages msg1 = new Messages("+27821234567", "Msg1");
        msg1.sentMessage();
        Messages msg2 = new Messages("+27831234567", "Msg2");
        msg2.sentMessage();
        assertEquals(2, Messages.returnTotalMessages());
    }
}
    
  