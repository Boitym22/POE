/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoginTest {

    // =========================
    // USERNAME TESTS
    // =========================

    @Test
    public void testCorrectUsername() {

        assertTrue(ChatApp.checkUsername("kyl_1"));
    }

    @Test
    public void testIncorrectUsername() {

        assertFalse(ChatApp.checkUsername("kyle!!!!"));
    }

    // =========================
    // PASSWORD TESTS
    // =========================

    @Test
    public void testCorrectPassword() {

        assertTrue(ChatApp.checkPassword("Password@1"));
    }

    @Test
    public void testIncorrectPassword() {

        assertFalse(ChatApp.checkPassword("password"));
    }

    // =========================
    // CELLPHONE TESTS
    // =========================

    @Test
    public void testCorrectCellphone() {

        assertTrue(ChatApp.checkCellphone("+27831234567"));
    }

    @Test
    public void testIncorrectCellphone() {

        assertFalse(ChatApp.checkCellphone("0831234567"));
    }

    // =========================
    // RECIPIENT TESTS
    // =========================

    @Test
    public void testCorrectRecipient() {

        String recipient = "+27685139480";

        boolean valid =
                recipient.matches("^\\+27\\d{9}$");

        assertTrue(valid);
    }

    @Test
    public void testIncorrectRecipient() {

        String recipient = "0685139480";

        boolean valid =
                recipient.matches("^\\+27\\d{9}$");

        assertFalse(valid);
    }

    // =========================
    // MESSAGE LENGTH TESTS
    // =========================

    @Test
    public void testMessageLengthSuccess() {

        String message =
                "Hello this is a short message";

        boolean valid =
                message.length() <= 250;

        assertTrue(valid);
    }

    @Test
    public void testMessageLengthFail() {

        String message =
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        boolean valid =
                message.length() <= 250;

        assertFalse(valid);
    }

    // =========================
    // MESSAGE ID TEST
    // =========================

    @Test
    public void testGenerateMessageID() {

        String id =
                ChatApp.generateMessageID();

        assertEquals(10, id.length());
    }

    // =========================
    // HASH TEST
    // =========================

    @Test
    public void testMessageHashCreation() {

        String hash =
                ChatApp.createMessageHash(
                        "1234567890",
                        1,
                        "Hi thanks");

        assertEquals("12:1:HITHANKS", hash);
    }
}
