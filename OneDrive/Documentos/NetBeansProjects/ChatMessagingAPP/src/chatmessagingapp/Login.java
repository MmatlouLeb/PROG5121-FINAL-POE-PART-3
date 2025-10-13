/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;

import javax.swing.JOptionPane;
/**
 * 
 * @author RC_Student_lab
 */
public class Login {
      
    /** This has the user info so we can check if login is correct. */
    private final Registration registration;
    
    /** True if the last login worked, false if it didn’t. */
    private boolean accessGranted;
    
    /**
     * Makes a new Login object using a Registration object.
     * This links login to the registered users.
     * 
     * @param registration the object that has the saved user info
     */
    public Login(Registration registration) 
    {
        this.registration = registration;
    }
    
    /**
     * Tries to log in a user by checking username and password.
     * Sets accessGranted to true if info matches, false if it doesn’t.
     * 
     * @param userNameAttempt the username the user typed
     * @param passwordAttempt the password the user typed
     * @return true if login works, false if not
     */
    public boolean loginUser(String userNameAttempt, String passwordAttempt) 
    {
        String storedUserName = registration.getUserName();
        String storedPassword = registration.getPassword();
        
        accessGranted = (userNameAttempt != null && userNameAttempt.equals(storedUserName)) &&
                        (passwordAttempt != null && passwordAttempt.equals(storedPassword));
        
        return accessGranted;
    }

    /**
     * Shows a message for the last login attempt.
     * If login worked, says welcome with the user’s name.
     * If login failed, says username or password is wrong.
     * 
     * @return a message about login status
     */
    public String returnLoginStatus() 
    {
        if (accessGranted) 
        {
            return String.format("Welcome %s %s,\nit is great to see you.", 
                registration.getFirstName(), registration.getLastName());
        } else 
        {
            return "Username & Password do not match our records, please try again.";
        }
    }

    /**
     * Gets the username from Registration.
     * This can be used later for other features like messaging.
     * 
     * @return the saved username
     */
    public String getUsername() 
    {
        return registration.getUserName();
    }
}
