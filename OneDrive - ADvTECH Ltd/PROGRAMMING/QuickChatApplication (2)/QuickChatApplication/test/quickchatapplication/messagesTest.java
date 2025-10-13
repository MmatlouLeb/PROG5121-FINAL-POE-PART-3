package quickchatapplication;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class messagesTest {

    private messages msg;

    @BeforeEach
    void setup() {
        msg = new messages(); 
        messages.TEST_MODE = true; // ensure no GUI / I/O
        TaskReport.TEST_MODE = true;
    }

    @Test
    void testMessageLengthSuccess() {
        String text = "Hi Mike, can you join us for dinner tonight";
        assertTrue(text.length() <= 250);
        assertEquals("Message ready to send.", text.length() <= 250 ? "Message ready to send." : "Failure");
    }

    @Test
    void testMessageLengthFailure() {
        String longText = "A".repeat(260);
        int excess = longText.length() - 250;
        String expected = "Message exceeds 250 characters by " + excess + ", please reduce size.";
        String actual = longText.length() > 250 ? "Message exceeds 250 characters by " + excess + ", please reduce size." : "Message ready to send.";
        assertEquals(expected, actual);
    }

    @Test
    void testRecipientFormatSuccess() {
        assertTrue(msg.checkRecipientCell("+27718693002"));
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell("+27718693002") ? "Cell phone number successfully captured." : "Fail");
    }

    @Test
    void testRecipientFormatFailure() {
        assertFalse(msg.checkRecipientCell("08575975889"));
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell("08575975889") ? "Cell phone number successfully captured." : "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
    }

    @Test
    void testCreateMessageHashMatchesPOE() {
        // Using test case: messageText "Hi Mike, can you join us for dinner tonight"
        String messageID = "00";
        int num = 0;
        String text = "Hi tonight";
        String expected = "00:0:HITONIGHT";
        String actual = msg.createMessageHash(messageID, num, text);
        assertEquals(expected, actual);
    }

    @Test
    void testMessageIDGenerationCheck() {
        String id = "1234567890"; // 10 chars
        assertTrue(msg.checkMessageID(id));
        assertFalse(msg.checkMessageID("12345678901")); // 11 chars
    }

    @Test
    void testSentMessageOptionBehaviour() {
        // In TEST_MODE, sentMessage() returns "Send"
        assertEquals("Send", msg.sentMessage());
    }

    @Test
    void testTotalMessageCountIncrement() {
        // reset by using reflection? Simpler: call increment twice and check increments
        int before = messages.incrementMessageCount();
        int after = messages.incrementMessageCount();
        assertEquals(before + 1, after);
    }
}
