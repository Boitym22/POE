/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package chatapp;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testValidUsername() {

        assertTrue(
                ChatApp.checkUsername("ab_c"));
    }

    @Test
    public void testInvalidUsername() {

        assertFalse(
                ChatApp.checkUsername("abcdef"));
    }

    @Test
    public void testValidPassword() {

        assertTrue(
                ChatApp.checkPassword("Password1!"));
    }

    @Test
    public void testInvalidPassword() {

        assertFalse(
                ChatApp.checkPassword("password"));
    }

    @Test
    public void testValidCellphone() {

        assertTrue(
                ChatApp.checkCellphone("+27831234567"));
    }

    @Test
    public void testInvalidCellphone() {

        assertFalse(
                ChatApp.checkCellphone("0831234567"));
    }
}