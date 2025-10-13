/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template 
 */
package quickchatapplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class RegisterTest {
 

    private Register register;
    private Login login;

    @BeforeAll
    public void setUp() {
        register = new Register();
        login = new Login(register);
    }

    // Username Tests 
    @Test
    public void testValidUsername() {
        assertTrue(register.checkUserName("abc_d"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(register.checkUserName("abcd"));
    }

    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(register.checkUserName("abcdef_"));
    }

    //  Password Tests 
    @Test
    public void testValidPassword() {
        assertTrue(register.checkPasswordComplexity("Abcde1@3"));
    }

    @Test
    public void testInvalidPassword_NoUppercase() {
        assertFalse(register.checkPasswordComplexity("abcdef1@"));
    }

    @Test
    public void testInvalidPassword_NoNumber() {
        assertFalse(register.checkPasswordComplexity("Abcdef@!"));
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse(register.checkPasswordComplexity("Abcdef12"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(register.checkPasswordComplexity("A1@bc"));
    }

    // Cell Phone Tests
    @Test
    public void testValidCellPhone() {
        assertTrue(register.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidCellPhone_WrongPrefix() {
        assertFalse(register.checkCellPhoneNumber("0838968976"));
    }

    @Test
    public void testInvalidCellPhone_TooShort() {
        assertFalse(register.checkCellPhoneNumber("+27838"));
    }

    // Registration Tests 
    @Test
    public void testSuccessfulRegistration() {
        String result = register.registeUser("abc_d", "Abcde1@3", "+27838968976", "John", "Doe");
        assertTrue(result.contains("successfully captured"));
        assertEquals("abc_d", register.getUsername());
        assertEquals("Abcde1@3", register.getPassword());
        assertEquals("+27838968976", register.getCellNumber());
        assertEquals("John", register.getFirstName());
        assertEquals("Doe", register.getLastName());
    }

    @Test
    public void testFailedRegistration_InvalidUsername() {
        String result = register.registeUser("abcdef", "Abcde1@3", "+27838968976", "John", "Doe");
        assertEquals("Username is not correctly formatted, please ensure it contains an underscore and is no more than 5 characters.", result);
    }

    @Test
    public void testFailedRegistration_InvalidPassword() {
        String result = register.registeUser("abc_d", "password", "+27838968976", "John", "Doe");
        assertEquals("Password is not correctly formatted; it must be at least 8 chars, contain a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testFailedRegistration_InvalidCellNumber() {
        String result = register.registeUser("abc_d", "Abcde1@3", "0838968976", "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted, must include international code +27.", result);
    }

    // Login Tests 
    @Test
    public void testSuccessfulLogin() {
        register.registeUser("abc_d", "Abcde1@3", "+27838968976", "John", "Doe");
        assertTrue(login.loginUser("abc_d", "Abcde1@3"));
        assertEquals("Welcome John ,Doe it is great to see you again.", login.returnLoginStatus());
    }

    @Test
    public void testFailedLogin_WrongUsername() {
        register.registeUser("abc_d", "Abcde1@3", "+27838968976", "John", "Doe");
        assertFalse(login.loginUser("wrong", "Abcde1@3"));
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus());
    }

    @Test
    public void testFailedLogin_WrongPassword() {
        register.registeUser("abc_d", "Abcde1@3", "+27838968976", "John", "Doe");
        assertFalse(login.loginUser("abc_d", "wrongPass"));
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus());
    }

    }
    