/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quickchatapplication;

import javax.swing.JOptionPane;

public class QuickChatApplication {

    public static void main(String[] args) {

        // Create a new instance of Register
        Register register = new Register();

        String username, password, cell, firstName, lastName;

        // Prompt user for First Name
        do {
            firstName = JOptionPane.showInputDialog("Enter first name:");
            if (firstName == null || firstName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "First name cannot be empty!");
            }
        } while (firstName == null || firstName.trim().isEmpty());

        // Prompt user for Last Name
        do {
            lastName = JOptionPane.showInputDialog("Enter last name:");
            if (lastName == null || lastName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Last name cannot be empty!");
            }
        } while (lastName == null || lastName.trim().isEmpty());

        // Username validation
        do {
            username = JOptionPane.showInputDialog("Enter username (must contain '_' and be <5 characters):");
            if (!register.checkUserName(username)) {
                JOptionPane.showMessageDialog(null,
                        "Invalid username!\nRules:\n- Must contain an underscore (_)\n- No more than 5 characters.");
            }
        } while (!register.checkUserName(username));

        // Password validation
        do {
            password = JOptionPane.showInputDialog("Enter password:\n(Must be <= 8 chars, 1 capital, 1 number, 1 special character)");
            if (!register.checkPasswordComplexity(password)) {
                JOptionPane.showMessageDialog(null,
                        "Invalid password!\nRules:\n- At least 8 characters\n- Must contain a capital letter\n- Must contain a number\n- Must contain a special character");
            }
        } while (!register.checkPasswordComplexity(password));

        // Phone number validation
        do {
            cell = JOptionPane.showInputDialog("Enter cell phone number (must start with +27)");
            if (!register.checkCellPhoneNumber(cell)) {
                JOptionPane.showMessageDialog(null,
                        "Invalid phone number!\nRules:\n- Must start with +27\n- Followed by exactly 9 digits");
            }
        } while (!register.checkCellPhoneNumber(cell));

        // Register user
        String regResult = register.registeUser(username, password, cell, firstName, lastName);
        JOptionPane.showMessageDialog(null, regResult);

        // Attempt login
        Login login = new Login(register);
        boolean loggedIn = false;

        do {
            String loginUser = JOptionPane.showInputDialog("Login - Enter username:");
            String loginPass = JOptionPane.showInputDialog("Login - Enter password:");
            loggedIn = login.loginUser(loginUser, loginPass);
            JOptionPane.showMessageDialog(null, login.returnLoginStatus());
        } while (!loggedIn); // Keep looping until login is successful

        // Start messaging after login
        messages messages = new messages();
        messages.startMessaging();
    }
}
 