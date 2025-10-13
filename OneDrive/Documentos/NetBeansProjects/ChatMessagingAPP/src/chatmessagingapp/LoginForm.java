/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;


import javax.swing.*;

/**
 * GUI login form for QuickChat application.
 * Includes a toggle button for showing/hiding the password with "Show"/"Hide" text.
 */
public class LoginForm extends javax.swing.JFrame {

    private final Login LOGIN;
    private final ChatMessagingAPP QUICK_CHAT;

    /**
     * Constructs the LoginForm.
     *
     * @param login     Login instance handling authentication logic.
     * @param quickChat ChatMessagingAPP instance managing form transitions.
     */
    public LoginForm(Login login, ChatMessagingAPP quickChat) {
        this.LOGIN = login;
        this.QUICK_CHAT = quickChat;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        togglePasswordButton = new JButton(); // Show/Hide button

        // Frame settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650, 400);

        // Left panel
        jPanel2.setBackground(new java.awt.Color(7, 94, 84));
        jLabel1.setText("ChatMessagingAPP");
        jLabel1.setForeground(java.awt.Color.WHITE);
        jPanel2.add(jLabel1);

        // Main panel
        jPanel3.setBackground(new java.awt.Color(236, 229, 221));

        // Inner form panel
        jPanel4.setBackground(java.awt.Color.WHITE);

        usernameLabel.setText("Enter your Username:");
        passwordLabel.setText("Enter your Password:");

        loginButton.setText("Login");
        loginButton.addActionListener(evt -> loginButtonActionPerformed());

        // Show/Hide password button
        togglePasswordButton.setText("Show");
        togglePasswordButton.setFocusable(false);
        togglePasswordButton.addActionListener(evt -> togglePasswordVisibility());

        // Layout
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel))
                .addGap(20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(togglePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(togglePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(loginButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4);

        add(jPanel2, java.awt.BorderLayout.WEST);
        add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }

    /**
     * Toggles the visibility of the password field and updates the button text.
     */
    private void togglePasswordVisibility() {
        if ("Show".equals(togglePasswordButton.getText())) {
            passwordField.setEchoChar((char) 0); // Show password
            togglePasswordButton.setText("Hide");
        } else {
            passwordField.setEchoChar('•'); // Hide password
            togglePasswordButton.setText("Show");
        }
    }

    /**
     * Handles the login button action.
     * Authenticates user input and provides feedback dialogs.
     */
    private void loginButtonActionPerformed() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        boolean success = LOGIN.loginUser(username, password);
        String message = LOGIN.returnLoginStatus();

        if (success) {
            JOptionPane.showMessageDialog(this, message, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
             this.dispose(); //  Completely closes the login window
            QUICK_CHAT.showMessageFeature(); // Navigate to main chat
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, message, "Login Failed", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    // Variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JButton togglePasswordButton; // Show/Hide button
}
 