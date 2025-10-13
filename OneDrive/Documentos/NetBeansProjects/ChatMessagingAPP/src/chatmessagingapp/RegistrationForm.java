/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;

import javax.swing.JOptionPane;

/**
 * RegistrationForm provides a GUI for registering new users
 * in the ChatMessagingAPP system. It interacts with the
 * Registration class for validation and data storage and
 * uses ChatMessagingAPP to manage form transitions.
 * 
 * Added: Show/Hide password toggle button.
 * 
 * @author RC_Student_lab
 */
class RegistrationForm extends javax.swing.JFrame {

    /** The Registration instance used for validating and storing user data. */
    private final Registration REGISTRATION;
    
    /** The ChatMessagingAPP instance controlling the application flow between forms. */
    private final ChatMessagingAPP QUICK_CHAT;

    /**
     * Constructs a new RegistrationForm with the given Registration and ChatMessagingAPP instances.
     * Initializes the GUI components and links the form to the core logic and app controller.
     * 
     * @param registration the Registration object for handling user data validation
     * @param quickChat the ChatMessagingAPP object managing form transitions
     */
    public RegistrationForm(Registration registration, ChatMessagingAPP quickChat) {
        this.REGISTRATION = registration;
        this.QUICK_CHAT = quickChat;
        initComponents();
    }

    /**
     * Initializes GUI components for the registration form.
     * WARNING: Do NOT modify this code unless you know what you're doing.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        firstnameLabel = new javax.swing.JLabel();
        lastnameLabel = new javax.swing.JLabel();
        cellphoneLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        firstnameField = new javax.swing.JTextField();
        lastnameField = new javax.swing.JTextField();
        cellphoneField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        showPasswordButton = new javax.swing.JToggleButton(); // Added toggle button
        registerButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(960, 540));
        setSize(new java.awt.Dimension(960, 540));

        jPanel2.setBackground(new java.awt.Color(7, 94, 84));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(236, 229, 221));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        firstnameLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        firstnameLabel.setText("Enter your First Name:");

        lastnameLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        lastnameLabel.setText("Enter your Last Name:");

        cellphoneLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        cellphoneLabel.setText("Enter your Cellphone:");

        usernameLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        usernameLabel.setText("Create a Username:");

        passwordLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        passwordLabel.setText("Create a Password:");

        firstnameField.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        lastnameField.setFont(new java.awt.Font("Cascadia Code", 0, 12));
        cellphoneField.setFont(new java.awt.Font("Cascadia Code", 0, 12));
        usernameField.setFont(new java.awt.Font("Cascadia Code", 0, 12));
        passwordField.setFont(new java.awt.Font("Cascadia Code", 0, 12));

        // Show/Hide password toggle button
        showPasswordButton.setText("Show");
        showPasswordButton.setFont(new java.awt.Font("Cascadia Code", 0, 12));
        showPasswordButton.addActionListener(evt -> togglePasswordVisibility());

        registerButton.setBackground(new java.awt.Color(37, 211, 102));
        registerButton.setFont(new java.awt.Font("Cascadia Code", 1, 14));
        registerButton.setForeground(new java.awt.Color(17, 42, 70));
        registerButton.setText("Register");
        registerButton.addActionListener(evt -> registerButtonActionPerformed(evt));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cellphoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lastnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(firstnameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastnameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cellphoneField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66))
        );

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cellphoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cellphoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jTextPane1.setFont(new java.awt.Font("Cascadia Code", 1, 18)); 
        jTextPane1.setText("Registration");
        jTextPane1.setFocusable(false);
        jScrollPane1.setViewportView(jTextPane1);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        jTextArea1.setRows(5);
        jTextArea1.setText("Welcome to QuickChat v1!\n\nPlease create a new account\nby entering your credentials.");
        jTextArea1.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>                        

    /**
     * Toggles password visibility between hidden and visible.
     */
    private void togglePasswordVisibility() {
        if (showPasswordButton.isSelected()) {
            passwordField.setEchoChar((char) 0); // Show password
            showPasswordButton.setText("Hide");
        } else {
            passwordField.setEchoChar('•'); // Hide password
            showPasswordButton.setText("Show");
        }
    }

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        // Collect raw input from GUI fields
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String cellphone = cellphoneField.getText();
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();

        // Pass inputs to registerUser for validation and messaging
        String[] result = REGISTRATION.registerUser(username, password, cellphone, firstName, lastName);

        // Check if registration was fully successful using getters and final message
        boolean allCaptured = REGISTRATION.getUserName() != null &&
                              REGISTRATION.getPassword() != null &&
                              REGISTRATION.getCellPhoneNumber() != null &&
                              REGISTRATION.getFirstName() != null &&
                              REGISTRATION.getLastName() != null &&
                              "Registration Successful".equals(result[5]); // Check final message

        // Display all messages (success or errors + final status)
        String feedback = String.join("\n", result);

        if (allCaptured) {
            // Show success dialog and switch forms
            JOptionPane.showMessageDialog(this, feedback, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            QUICK_CHAT.showLoginForm(); // Show LoginForm
            this.dispose();             // Close RegistrationForm after dialog
        } else {
            // Show feedback dialog with errors and "Registration aborted"
            JOptionPane.showMessageDialog(this, feedback, "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField cellphoneField;
    private javax.swing.JLabel cellphoneLabel;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3; 
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JToggleButton showPasswordButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration                   
}
