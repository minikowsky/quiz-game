package gui.login;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    LogInPanel logInPanel;
    SignUpPanel signUpPanel;
    CenterPanel(){
        this.setLayout(new CardLayout());
        logInPanel = new LogInPanel();
        signUpPanel = new SignUpPanel();
        this.add("login",logInPanel);
        this.add("signup",signUpPanel);
    }
}
