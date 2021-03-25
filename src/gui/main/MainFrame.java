package gui.main;

import database.CurrentUser;

import javax.swing.*;
import java.util.Objects;

public class MainFrame extends JFrame {
    ImageIcon img;
    public MainFrame(){
        this.setTitle("Quiz by minik");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().add(new MainRootPanel());
        img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/quiz.png")));
        this.setIconImage(img.getImage());
        this.pack();
        this.setLocationRelativeTo(null);
        System.out.println(CurrentUser.getUserLogin());
    }
}
