/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatmessagingapp;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple chat window for sending and viewing messages.
 * @author mmatl
 */
/**
 * MessagesForm provides a GUI for composing, sending,
 * and storing messages in the ChatMessagingAPP system.
 * 
 * It interacts with the Messages class for validation,
 * message hashing, and JSON storage, while maintaining
 * the same visual identity as the RegistrationForm.
 * 
 * @author RC_Student_lab
 */
public class MessagesForm extends javax.swing.JFrame {

    /** The ChatMessagingAPP instance controlling navigation between forms. */
    private final ChatMessagingAPP QUICK_CHAT;

    /** The Messages instance representing the active message. */
    private Messages message;

    /**
     * Constructs a new MessagesForm linked to the main app controller.
     * Initializes GUI components and sets theme.
     * 
     * @param quickChat the ChatMessagingAPP object managing form transitions
     */
    public MessagesForm(ChatMessagingAPP quickChat) {
        this.QUICK_CHAT = quickChat;
        initComponents();
    }

    /**
     * Initializes GUI components for the message form.
     * WARNING: Do NOT modify this code unless you know what you're doing.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanelLeft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelRight = new javax.swing.JPanel();
        jScrollPaneTitle = new javax.swing.JScrollPane();
        jTextPaneTitle = new javax.swing.JTextPane();
        jScrollPaneDescription = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jPanelForm = new javax.swing.JPanel();
        recipientLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        recipientField = new javax.swing.JTextField();
        jScrollPaneMessage = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        storeButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(960, 540));

        // Left panel (branding)
        jPanelLeft.setBackground(new java.awt.Color(7, 94, 84));

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeftLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Right panel (content area)
        jPanelRight.setBackground(new java.awt.Color(236, 229, 221));

        // Title
        jTextPaneTitle.setFont(new java.awt.Font("Cascadia Code", 1, 18)); 
        jTextPaneTitle.setText("Send a Message");
        jTextPaneTitle.setFocusable(false);
        jScrollPaneTitle.setViewportView(jTextPaneTitle);

        // Description
        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        jTextAreaDescription.setRows(5);
        jTextAreaDescription.setText("Compose your message below.\nMake sure the number starts with +27 and message is under 250 characters.");
        jTextAreaDescription.setFocusable(false);
        jScrollPaneDescription.setViewportView(jTextAreaDescription);

        // Inner form panel
        jPanelForm.setBackground(new java.awt.Color(255, 255, 255));

        recipientLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        recipientLabel.setText("Recipient Number (+27...)");

        messageLabel.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        messageLabel.setText("Message Text");

        recipientField.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 

        messageArea.setColumns(20);
        messageArea.setFont(new java.awt.Font("Cascadia Code", 0, 12)); 
        messageArea.setRows(5);
        jScrollPaneMessage.setViewportView(messageArea);

        sendButton.setBackground(new java.awt.Color(37, 211, 102));
        sendButton.setFont(new java.awt.Font("Cascadia Code", 1, 14)); 
        sendButton.setForeground(new java.awt.Color(17, 42, 70));
        sendButton.setText("Send Message");
        sendButton.addActionListener(evt -> sendButtonActionPerformed(evt));

        storeButton.setBackground(new java.awt.Color(7, 94, 84));
        storeButton.setFont(new java.awt.Font("Cascadia Code", 1, 14)); 
        storeButton.setForeground(new java.awt.Color(255, 255, 255));
        storeButton.setText("Store Message");
        storeButton.addActionListener(evt -> storeButtonActionPerformed(evt));

        viewButton.setBackground(new java.awt.Color(240, 185, 50));
        viewButton.setFont(new java.awt.Font("Cascadia Code", 1, 14)); 
        viewButton.setForeground(new java.awt.Color(17, 42, 70));
        viewButton.setText("View Last Message");
        viewButton.addActionListener(evt -> viewButtonActionPerformed(evt));

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recipientLabel)
                    .addComponent(messageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(recipientField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPaneMessage))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(storeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recipientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipientField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRight.setLayout(jPanelRightLayout);
        jPanelRightLayout.setHorizontalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRightLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneTitle)
                    .addComponent(jScrollPaneDescription)
                    .addComponent(jPanelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanelRightLayout.setVerticalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRightLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jScrollPaneTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * Handles the Send Message button click.
     * Validates and sends a message via Messages class.
     */
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String recipient = recipientField.getText();
        String payload = messageArea.getText();

        message = new Messages(recipient, payload);
        String response = message.sentMessage();

        JOptionPane.showMessageDialog(this, response, 
            response.contains("successfully") ? "Message Sent" : "Error", 
            response.contains("successfully") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Handles the Store Message button click.
     * Saves the most recent message to a JSON file.
     */
    private void storeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (message == null) {
            JOptionPane.showMessageDialog(this, "No message to store. Please send one first.",
                    "Storage Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String storeResponse = message.storeMessage();
        JOptionPane.showMessageDialog(this, storeResponse, "Storage Result", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Handles the View Last Message button click.
     * Displays details of the last sent message.
     */
    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String output = Messages.printMessages();
        JOptionPane.showMessageDialog(this, output, "Last Sent Message", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel recipientLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextField recipientField;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton storeButton;
    private javax.swing.JButton viewButton;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPaneTitle;
    private javax.swing.JScrollPane jScrollPaneDescription;
    private javax.swing.JScrollPane jScrollPaneMessage;
    private javax.swing.JTextPane jTextPaneTitle;
    private javax.swing.JTextArea jTextAreaDescription;
    // End of variables declaration                   
} 