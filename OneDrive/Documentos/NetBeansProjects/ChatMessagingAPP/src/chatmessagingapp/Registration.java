/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;


import java.util.ArrayList;
import java.util.regex.Pattern;
 
/**
 *
 * @author RC_Student_lab
 */
public class Registration
{
    /** Username saved here. Must have underscore and max 5 letters. */
    private String storedUserName;
    
    /** Password saved here. Must have 8+ chars, capital, number, special char. */
    private String storedPassword;
    
    /** Phone number saved here. Must start with +27 and have 9 digits after it. */
    private String storedCellPhoneNumber;
    
    /** First name saved here. Letters only. */
    private String storedFirstName;
    
    /** Last name saved here. Letters only. */
    private String storedLastName;
    
    /**
     * Registers a new user.
     * Checks all fields with the rules and gives messages to the user while the program is running.
     * 
     * @param newUserName the username to register
     * @param newPassword the password to register
     * @param newCellPhoneNumber the phone number to register
     * @param newFirstName the first name to register
     * @param newLastName the last name to register
     * @return messages for each field + final status
     */
    public String[] registerUser(String newUserName, String newPassword, String newCellPhoneNumber, String newFirstName, String newLastName)
    {
        ArrayList<String> messages = new ArrayList<>(); // store messages to show user
        
        // Validate and store username
        if (setUserName(newUserName))
        {
            messages.add("Username successfully captured");
        }
        else
        {
            messages.add("Username must be 5 letters max and include an underscore, please fix it.");
        }
        
        // Validate and store password
        if (setPassword(newPassword))
        {
            messages.add("Password successfully captured");
        }
        else
        {
            messages.add("Password must be at least 8 chars, have a capital letter, a number, and a special character.");
        }
        
        // Validate and store phone number
        if (setCellPhoneNumber(newCellPhoneNumber))
        {
            messages.add("Cellphone number successfully captured");
        }
        else
        {
            messages.add("Phone number must start with +27 and have 9 digits after it.");
        }

        // Validate first name
        if (setFirstName(newFirstName))
        {
            messages.add("First name successfully captured");
        }
        else
        {
            messages.add("First name is invalid, letters only and cannot be empty.");
        }

        // Validate last name
        if (setLastName(newLastName))
        {
            messages.add("Last name successfully captured");
        }
        else
        {
            messages.add("Last name is invalid, letters only and cannot be empty.");
        }
        
        // Final status
        if (storedUserName == null || storedPassword == null || storedCellPhoneNumber == null || storedFirstName == null || storedLastName == null)
        {
            messages.add("Registration Failed");
        }
        else
        {
            messages.add("Registration Successful");
        }
        
        return messages.toArray(String[]::new);
    }

    /** Get username */
    public String getUserName() { return storedUserName; }
    /** Get password */
    public String getPassword() { return storedPassword; }
    /** Get phone number */
    public String getCellPhoneNumber() { return storedCellPhoneNumber; }
    /** Get first name */
    public String getFirstName() { return storedFirstName; }
    /** Get last name */
    public String getLastName() { return storedLastName; }

    /** Set username if valid */
    public boolean setUserName(String userName) 
    {
        if (checkUserName(userName)) 
        {
            storedUserName = userName;
            return true;
        }
        return false;
    }
    
    /** Set password if valid */
    public boolean setPassword(String password) 
    {
        if (checkPasswordComplexity(password)) 
        {
            storedPassword = password;
            return true;
        }
        return false;
    }
    
    /** Set phone number if valid */
    public boolean setCellPhoneNumber(String cellPhoneNumber) 
    {
        if (checkCellPhoneNumber(cellPhoneNumber))
        {
            storedCellPhoneNumber = cellPhoneNumber;
            return true;
        }
        return false;
    }
    
    /** Set first name if valid */
    public boolean setFirstName(String firstName)
    {
        if (firstName != null && !firstName.trim().isEmpty() && firstName.matches("^[a-zA-Z]+$"))
        {
            storedFirstName = firstName;
            return true;
        }
        return false;
    }

    /** Set last name if valid */
    public boolean setLastName(String lastName)
    {
        if (lastName != null && !lastName.trim().isEmpty() && lastName.matches("^[a-zA-Z]+$"))
        {
            storedLastName = lastName;
            return true;
        }
        return false;
    }

    // ------------------ PRIVATE CHECKS ------------------
    
    /** Check username rules: max 5 chars and underscore */
    private boolean checkUserName(String userName)
    {
        if (userName == null || userName.isBlank()) return false;
        return userName.contains("_") && userName.length() <= 5;
    }
    
    /** Check password rules: 8+ chars, capital, number, special char */
    private boolean checkPasswordComplexity(String password)
    {
        if (password == null || password.isBlank()) return false;

        boolean hasCapital = false, hasDigit = false, hasSpecial = false;

        if (password.length() < 8) return false; // must be 8+ chars

        for (char c : password.toCharArray())
        {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasDigit && hasSpecial;
    }
    
    /** Check phone number rules: start +27 and 9 digits after */
    private boolean checkCellPhoneNumber(String cellPhoneNumber) 
    {
        if (cellPhoneNumber == null || cellPhoneNumber.isBlank()) return false;
        return Pattern.matches("^\\+27[0-9]{9}$", cellPhoneNumber);
    }
}
