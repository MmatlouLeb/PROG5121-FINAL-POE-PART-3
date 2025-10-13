 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapplication;

import java.util.regex.Pattern;

/**
 *Handles user registration and validation.
 * Uses setters and getters for encapsulation.
 * @author RC_Student_Lab
 */
public class Register {
// private fileds to stiore user data and they are hidden from outside access (encapsulation)
    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    // Validation Methods

    /** Username Rules:
     *  - Must contain an underscore (_)
     *  - Must be no more than five characters long
     */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /** Password Rules:
     *  - At least eight characters long
     *  - Must contain a capital letter
     *  - Must contain a number
     *  - Must contain a special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
        return hasUpper && hasDigit && hasSpecial;
    }

    /** Cell Phone Number Rules:
     *  - Must contain the international country code +27
     *  - Must be followed by exactly 9 digits
     *  Example: +27838968976
     */
    public boolean checkCellPhoneNumber(String number) {
        if (number == null) return false;
        return Pattern.matches("^\\+27\\d{9}$", number);
    }

    // Registration Method (kept your spelling) and return a message is everything is corect
    public String registeUser(String username, String password, String number,
                              String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure it contains an underscore and is no more than 5 characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; it must be at least 8 chars, contain a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(number)) {
            return "Cell phone number incorrectly formatted, must include international code +27.";
        }

        // use setters instead of direct assignment
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setCellNumber(number);

        return "FirstName successfully captured.\nLastName successfully captured\nUsername successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // These method aloows  us to get or set user's data in a controlled way
    public String getFirstName() { return firstName; }//return the firstname 
    public void setFirstName(String firstName) { this.firstName = firstName; } 

    public String getLastName() { return lastName; } // set last name for last name 
    public void setLastName(String lastName) { this.lastName = lastName; } // return the last name 

    public String getUsername() { return username; } // set the username for the user
    public void setUsername(String username) { this.username = username; }// return  the last user name foe the user name

    public String getPassword() { return password; } // set the password for the user
    public void setPassword(String password) { this.password = password; } // return the cellphone number for the user

    public String getCellNumber() { return cellNumber; } // set the cell phone number for user's
    public void setCellNumber(String cellNumber) { this.cellNumber = cellNumber; }//return  the cell phone number from the user
}
    

    

