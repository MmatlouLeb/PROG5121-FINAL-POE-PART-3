/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatmessagingapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RegistrationTest {

    private Registration registration;


    @Test
    public void testValidRegistration() {
        String[] result = registration.registerUser("Sam_1", "Pass@123", "+27831234567", "John", "Doe");
        assertNotNull(result);
        assertEquals(6, result.length);
        assertEquals("Registration Successful", result[5]);
        assertTrue(result[0].contains("captured"));
        assertTrue(result[1].contains("captured"));
        assertTrue(result[2].contains("captured"));
        assertTrue(result[3].contains("captured"));
        assertTrue(result[4].contains("captured"));
    } 


    @Test
    public void testInvalidFirstName() {
        String[] result = registration.registerUser("Sam_1", "Pass@123", "+27831234567", "John1", "Doe");
        assertEquals("Registration Failed", result[5]);
        assertTrue(result[3].contains("First name is invalid"));
    }

    @Test
    public void testInvalidLastName() {
        String[] result = registration.registerUser("Sam_1", "Pass@123", "+27831234567", "John", "Doe!");
        assertEquals("Registration Failed", result[5]);
        assertTrue(result[4].contains("Last name is invalid"));
    }

    // Basic getter/setter tests

    @Test
    public void testSetAndGetUserName() {
        registration.setUserName("Sam_1");
        assertEquals("Sam_1", registration.getUserName());
    }

    @Test
    public void testSetAndGetPassword() {
        registration.setPassword("Pass@123");
        assertEquals("Pass@123", registration.getPassword());
    }

    @Test
    public void testSetAndGetCellPhoneNumber() {
        registration.setCellPhoneNumber("+27831234567");
        assertEquals("+27831234567", registration.getCellPhoneNumber());
    }

    @Test
    public void testSetAndGetFirstName() {
        registration.setFirstName("John");
        assertEquals("John", registration.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        registration.setLastName("Doe");
        assertEquals("Doe", registration.getLastName());
    }
 
    @Test
    public void testRegisterUser() {
    }

    @Test
    public void testGetUserName() {
    }

    @Test
    public void testGetPassword() {
    }

    @Test
    public void testGetCellPhoneNumber() {
    }

    @Test
    public void testSetUserName() {
    }

    @Test
    public void testSetPassword() {
    }

    @Test
    public void testSetCellPhoneNumber() {
    }

    @Test
    public void testSetFirstName() {
    }

    @Test
    public void testSetLastName() {
    }

    @Test
    public void testGetFirstName() {
    }

    @Test
    public void testGetLastName() {
    }
}
