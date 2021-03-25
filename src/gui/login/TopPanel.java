package gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TopPanel extends JPanel{
    JButton plButton;
    JButton engButton;


    TopPanel(){
        this.setPreferredSize(new Dimension(500,35));
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        //System.out.println(layout.getAlignment());
        layout.setHgap(-10);
        //System.out.println(layout.getHgap());
        this.setLayout(layout);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(220,220,220));
        initComponents();

    }
    void initComponents(){
        ImageIcon engIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().getResource("assets/engFlag.png")));
        ImageIcon plIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getClassLoader().getResource("assets/plFlag.png")));
        plIcon = getScaled(plIcon);
        engIcon = getScaled(engIcon);

        plButton = new JButton(plIcon);
        plButton.setFocusable(false);
        plButton.setBorderPainted(false);
        plButton.setEnabled(false);
        plButton.setContentAreaFilled(false);
        plButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: change language to polish
                engButton.setEnabled(true);
                plButton.setEnabled(false);
            }
        });
        this.add(plButton);

        engButton = new JButton(engIcon);
        engButton.setSize(new Dimension(25,25));
        engButton.setFocusable(false);
        engButton.setContentAreaFilled(false);
        engButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO :change language to english
                plButton.setEnabled(true);
                engButton.setEnabled(false);

            }
        });
        this.add(engButton);
    }
    private ImageIcon getScaled(ImageIcon img){
        return new ImageIcon(img.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
    }
}
