package gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TutorialPanel extends BasePanel {
    JPanel cards;
    final int images = 3;
    int currentImg = 0;
    JButton backButton;
    JButton nextButton;

    public TutorialPanel(String panelName){
        super(panelName);
        initComponents();
    }

    private void initComponents() {
        cards = new JPanel(new CardLayout());
        cards.setBounds(0,75,500,350);
        //if you add then change "images" value
        cards.add("1",new JLabel(getScaled(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/tutorial/1.jpg"))))));
        cards.add("2",new JLabel(getScaled(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/tutorial/2.jpg"))))));
        cards.add("3",new JLabel(getScaled(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/tutorial/3.jpg"))))));
        backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.setBounds(50,450,75,25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentImg > 0){
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.previous(cards);
                }
                if(currentImg+1 == images)
                    nextButton.setVisible(true);
                currentImg--;
                if(currentImg==0)
                    backButton.setVisible(false);
            }
        });
        nextButton = new JButton("Next");
        nextButton.setFocusable(false);
        nextButton.setBounds(400,450,75,25);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentImg+1 < images){
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.next(cards);
                }
                if(currentImg == 0)
                    backButton.setVisible(true);
                currentImg++;
                if(currentImg+1 == images)
                    nextButton.setVisible(false);

            }
        });
        this.add(cards);
        this.add(backButton);
        this.add(nextButton);
    }
    private ImageIcon getScaled(ImageIcon img){
        return new ImageIcon(img.getImage().getScaledInstance(cards.getWidth(),cards.getHeight(),Image.SCALE_SMOOTH));
    }
}
