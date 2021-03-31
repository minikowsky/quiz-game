package gui.login;
import database.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel {
    JLabel loginLabel;
    JLabel passwordLabel;
    JLabel passwordAgainLabel;
    JTextField loginField;
    JPasswordField passwordField;
    JPasswordField passwordAgainField;
    JButton signUpButton;
    String fontName = "Monospaced";
    SignUpPanel(){
        Dimension SCREEN_SIZE = new Dimension(500, 450);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        Font font = new Font(fontName,Font.PLAIN,20);
        FontMetrics fontMetrics = getFontMetrics(font);

        loginLabel = new JLabel("Login");
        loginLabel.setFont(font);
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setSize(fontMetrics.stringWidth("Login"),fontMetrics.getHeight());
        loginLabel.setBounds(70,225,loginLabel.getWidth(),loginLabel.getHeight());
        this.add(loginLabel);

        loginField = new JTextField();
        loginField.setSize(200,25);
        loginField.setBounds(250,225, loginField.getWidth(), loginField.getHeight());
        this.add(loginField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setSize(fontMetrics.stringWidth("Password"),fontMetrics.getHeight());
        passwordLabel.setBounds(70,260, passwordLabel.getWidth(), passwordLabel.getHeight());
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setSize(200,25);
        passwordField.setBounds(250,260, passwordField.getWidth(), passwordField.getHeight());
        this.add(passwordField);

        passwordAgainLabel = new JLabel("Password again");
        passwordAgainLabel.setFont(font);
        passwordAgainLabel.setForeground(Color.WHITE);
        passwordAgainLabel.setSize(fontMetrics.stringWidth("Password again"), fontMetrics.getHeight());
        passwordAgainLabel.setBounds(70,295, passwordAgainLabel.getWidth(), passwordAgainLabel.getHeight());
        this.add(passwordAgainLabel);

        passwordAgainField = new JPasswordField();
        passwordAgainField.setSize(200,25);
        passwordAgainField.setBounds(250,295, passwordAgainField.getWidth(), passwordField.getHeight());
        this.add(passwordAgainField);

        signUpButton = new JButton("Sign up");
        signUpButton.setFocusable(false);
        signUpButton.setSize(75,20);
        signUpButton.setBounds(375,335, signUpButton.getWidth(), signUpButton.getHeight());
        signUpButton.addActionListener(e -> {
            String pass1 = String.valueOf(passwordField.getPassword());
            String pass2 = String.valueOf(passwordAgainField.getPassword());
            String login = loginField.getText();
            if(Driver.isLoginTaken(login)){
                JOptionPane.showMessageDialog(getRootPane().getParent(),"This login is already taken!");
            }
            else if(!login.equals("")){
                if(pass1.equals("")||pass2.equals("")){
                    JOptionPane.showMessageDialog(getRootPane().getParent(),"The password field is required");
                }
                else{
                    if(pass1.equals(pass2)){
                        if(Driver.register(login,pass1)){
                            JOptionPane.showMessageDialog(getRootPane().getParent(),"You have been signed in!\n\tNow log in");
                        } else{
                            JOptionPane.showMessageDialog(getRootPane().getParent(),"Login is already taken :( ");
                        }
                    } else{
                        JOptionPane.showMessageDialog(getRootPane().getParent(),"These two passwords are different");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(getRootPane().getParent(),"The login field is required");
            }
            loginField.setText("");
            passwordField.setText("");
            passwordAgainField.setText("");
        });
        this.add(signUpButton);

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 35));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Quiz by minikowsky", (500 - metrics2.stringWidth("Quiz by minikowsky"))/2, 100);
        g.drawString("-Sign up-", (500 - metrics2.stringWidth("-Sign up-"))/2, 150);
    }
}
