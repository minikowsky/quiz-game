package gui.login;

import javax.swing.*;
import java.util.Objects;

public class EntryFrame extends JFrame{
    ImageIcon img;
    public EntryFrame(){
        //this.setUndecorated(true);
        this.setTitle("Quiz by minik");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().add(new EntryRootPanel());
        img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/quiz.png")));
        this.setIconImage(img.getImage());
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
