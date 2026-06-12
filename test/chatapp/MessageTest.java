/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package chatapp;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void setUp() {

        ChatApp.messageCount = 0;
        ChatApp.numMessagesSent = 0;

        ChatApp.messageIDs = new String[100];
        ChatApp.recipients = new String[100];
        ChatApp.messages = new String[100];
        ChatApp.messageHashes = new String[100];
        ChatApp.statuses = new String[100];
    }

    @Test
    public void testGenerateMessageID() {

        String id = ChatApp.generateMessageID();

        assertNotNull(id);
        assertEquals(10, id.length());
    }

    @Test
    public void testCreateMessageHash() {

        String hash =
                ChatApp.createMessageHash(
                        "1234567890",
                        1,
                        "Hello World");

        assertEquals(
                "12:1:HELLOWORLD",
                hash);
    }

    @Test
    public void testLongestMessage() {

        ChatApp.messages[0] = "Hi";
        ChatApp.messages[1] = "This is the longest message";

        ChatApp.messageCount = 2;

        assertEquals(
                "This is the longest message",
                ChatApp.getLongestMessage());
    }

    @Test
    public void testSearchMessageID() {

        ChatApp.messageIDs[0] = "111";
        ChatApp.recipients[0] = "+27831234567";
        ChatApp.messages[0] = "Hello";
        ChatApp.messageHashes[0] = "11:1:HELLO";

        ChatApp.messageCount = 1;

        String result =
                ChatApp.searchMessageID("111");

        assertTrue(result.contains("Hello"));
    }

    @Test
    public void testSearchRecipient() {

        ChatApp.recipients[0] = "+27831234567";
        ChatApp.messages[0] = "Testing";

        ChatApp.messageCount = 1;

        String result =
                ChatApp.searchRecipient("+27831234567");

        assertTrue(result.contains("Testing"));
    }
}