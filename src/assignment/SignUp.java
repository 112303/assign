package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUp{
   public static void main(String arg[])
   {
       try{
        SignUpForm frame=new SignUpForm();
        frame.setSize(300,100);
        frame.setVisible(true);
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
   }
}

class SignUpForm extends JFrame implements ActionListener{
    JButton SUBMIT;
    JPanel panel;
    JLabel usernameLabel,emailLabel, passwordLabel, confirmPasswordLabel;
    JTextField  registerUsernameText,registerEmailText, registerPasswordText, registerConfirmPasswordText;
    
    SignUpForm()
    {
        usernameLabel = new JLabel();
        usernameLabel.setText("Username:");
        registerUsernameText = new JTextField(15);
        
        emailLabel = new JLabel();
        emailLabel.setText("Email:");
        registerEmailText = new JTextField(15);
        
        passwordLabel = new JLabel();
        passwordLabel.setText("Password: ");
        registerPasswordText = new JPasswordField(15);
        
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm Password:");
        registerConfirmPasswordText = new JPasswordField(15);
        
        SUBMIT=new JButton("SUBMIT");
        
        panel=new JPanel(new GridLayout(5,1));
        panel.add(usernameLabel);
        panel.add(registerUsernameText);
        panel.add(emailLabel);
        panel.add(registerEmailText);
        panel.add(passwordLabel);
        panel.add(registerPasswordText);
        panel.add(confirmPasswordLabel);
        panel.add(registerConfirmPasswordText);
        panel.add(SUBMIT);
        add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("REGISTER FORM");
    }
    
    public boolean addUser(String username, String email, String password){
        Database db = new Database();
        boolean userCreated = db.createUser(username, email, password);
        if (userCreated)
            System.out.println("the user "+username+" has been created");
        else
            System.out.println("Unable to create " + username + " \nTry again later");
        return userCreated;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String username =registerUsernameText.getText();
        String email=registerEmailText.getText();
        String password = registerPasswordText.getText();
        String confirmPassword = registerConfirmPasswordText.getText();
        boolean isAdded;
        if (password.equals(confirmPassword) && !email.replace(" ", "").equals("") && !username.replace(" ", "").equals("") && !password.replace(" ", "").equals("s")){
            isAdded = addUser(username, email, password);
            if (isAdded){
                LoginForm frame=new LoginForm();
                frame.setSize(300,100);
                frame.setVisible(true);
                this.setVisible(false);
            }
        }
        else{
            System.out.println("the password and confirm password fields must be equal");
            JOptionPane.showMessageDialog(null, "PASSWORD AND CONFIRM PASSWORD FIELDS MUST MATCH");
        }
    }
}
