package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Login{
   public static void main(String arg[])
   {
       try{
        LoginForm frame=new LoginForm();
        frame.setSize(300,100);
        frame.setVisible(true);
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
   }
}

class LoginForm extends JFrame implements ActionListener{
    JButton SUBMIT;
    JPanel panel;
    JLabel emailLabel, passwordLabel, label1,label2;
    JTextField  loginEmailText, loginPasswordText, text1,text2;
    LoginForm()
    {
        emailLabel = new JLabel();
        emailLabel.setText("Email:");
        loginEmailText = new JTextField(15);
        
        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        loginPasswordText = new JPasswordField(15);
        
        SUBMIT=new JButton("SUBMIT");
        
        panel=new JPanel(new GridLayout(3,1));
        panel.add(emailLabel);
        panel.add(loginEmailText);
        panel.add(passwordLabel);
        panel.add(loginPasswordText);
        panel.add(SUBMIT);
        add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
        setTitle("LOGIN FORM");
    }
    
    public void actionPerformed(ActionEvent ae){
        String email = loginEmailText.getText();
        String password = loginPasswordText.getText();
        
        Database db = new Database();
        List userFound = db.findUser(email, password);
        System.out.println(userFound.size());
        if (userFound.size() == 3){
            GraphPanel graphPanel = new GraphPanel("pie chart...");
            graphPanel.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null, "User not found");
        }
    }
    
}