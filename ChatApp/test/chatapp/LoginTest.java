/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    @Test
public void testUsernameCorrectlyFormatted() {
Login l = new Login();
assertTrue(l.checkUsername("kyl_1"));
}

@Test
public void testUsernameIncorrectlyFormatted() {
Login l = new Login();
assertFalse(l.checkUsername("kyle!!!!!!!"));
}
@Test
public void testPasswordMeetsComplexity() {
Login l = new Login();
assertTrue(l.checkPasswordComplexity("Ch&&sec@ke99!"));
}

@Test
public void testPasswordDoesNotMeetComplexity() {
Login l = new Login();
assertFalse(l.checkPasswordComplexity("password"));
}

@Test
public void testCellphoneCorrectlyFormatted() {
    Login l = new Login();
assertEquals(false, l.checkCellPhoneNumber("‪+27838968976‬"));
}

@Test
public void testCellphoneIncorrectlyFormatted() {
Login l = new Login();
assertFalse(l.checkCellPhoneNumber("08966553"));
}


    
}
