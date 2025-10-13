package quickchatapplication;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskReportTest {

    @BeforeEach
    void setup() {
        messages.TEST_MODE = true;
        TaskReport.TEST_MODE = true;
        TaskReport.loadTestData(); // populate with assignment test cases
    }

    @Test
    void testSentMessagesArrayPopulated() {
        // Expect messages 1 and 4 in sentMessages
        boolean found1 = TaskReport.sentMessages.stream().anyMatch(m -> "Did you get the cake?".equals(m.getText()));
        boolean found4 = TaskReport.sentMessages.stream().anyMatch(m -> "It is dinner time!".equals(m.getText()));
        assertTrue(found1 && found4, "Sent messages should contain the two sent test messages.");
    }

    @Test
    void testDisplayLongestMessage() {
        String longest = TaskReport.getLongestMessageText();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    void testSearchByIDFound() {
        String output = TaskReport.searchByIDText("0838884567");
        assertTrue(output.contains("It is dinner time!"));
    }

    @Test
    void testSearchByRecipient() {
        String out = TaskReport.searchByRecipientText("+27838884567");
        assertTrue(out.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(out.contains("Ok, I am leaving without you."));
    }

    @Test
    void testDeleteByHash() {
        // Pick a stored message hash (message 2)
        String hash = TaskReport.storedMessages.get(0).getHash();
        assertTrue(TaskReport.deleteByHash(hash));
        // After deletion it should no longer be present
        boolean stillThere = TaskReport.storedMessages.stream().anyMatch(m -> hash.equals(m.getHash()));
        assertFalse(stillThere);
    }

    @Test
    void testDisplayReportContent() {
        String report = TaskReport.getFullSentReportText();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}
 