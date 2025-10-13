/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapplication;


/** 
 *
 * @author RC_Student_Lab
 */
public class Login {
    
       private final Register register; // link to Register class
    private boolean loggedIn = false;

    public Login(Register register) {
        this.register = register;
    }

    
   // if pasword and user name is i correct , retuen a message that says fail
    public boolean loginUser(String username, String password) {
        if (register.getUsername() == null || register.getPassword() == null) {
            loggedIn = false;
        } else {
            loggedIn = register.getUsername().equals(username) &&
                        register.getPassword().equals(password);
        }
        return loggedIn;
    }
       // if user's logged in , show a message
    public String returnLoginStatus() {
        if (loggedIn) {
            return String.format("Welcome %s ,%s it is great to see you again.",
                    register.getFirstName(), register.getLastName());
        } else {
            // if the log in failed , return a message that says  the username and password was incorrect
            return "Username or password incorrect, please try again.";
        }
    }

    public boolean isLoggedIn() {
        return loggedIn; //Retrun whethwe the user is logged in or not
    }

    
    }


    

