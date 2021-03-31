package gui.login;

import database.CurrentUser;
import database.Driver;
import gui.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel {
    JLabel loginLabel;
    JLabel passwordLabel;
    JTextField loginField;
    JPasswordField passwordField;
    JButton loginButton;
    String fontName = "Monospaced";
    LogInPanel(){

        Dimension SCREEN_SIZE = new Dimension(500, 450);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);
        initComponents();
        System.out.println(CurrentUser.getUserLogin());
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

        loginButton = new JButton("Log in");
        loginButton.setFocusable(false);
        loginButton.setSize(75,20);
        loginButton.setBounds(375,300, loginButton.getWidth(), loginButton.getHeight());
        loginButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            if(Driver.login(login,password)){
                int ID = Driver.getID(login,password);
                CurrentUser.setUser(ID,login,password);
                ((Window)getRootPane().getParent()).dispose();
                new MainFrame();
            }
            else{
                JOptionPane.showMessageDialog(getRootPane().getParent(),"Incorrect login or password");
                loginField.setText("");
                passwordField.setText("");
            }
        });
        this.add(loginButton);

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 35));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Quiz by minikowsky", (500 - metrics2.stringWidth("Quiz by minikowsky"))/2, 100);
        g.drawString("-Log in-", (500 - metrics2.stringWidth("-Log in-"))/2, 150);
    }
}
