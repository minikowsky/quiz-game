package gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryRootPanel extends JPanel {
    TopPanel topPanel;
    CenterPanel centerPanel;
    JPanel bottomPanel;
    JLabel  orLabel;
    JButton changeOptionButton;
    EntryRootPanel(){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(new Color(220,220,220));
        initBottomPanel();
        initPanels();
    }

    private void initBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        orLabel = new JLabel("- or -");
        orLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(orLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0,10)));
        changeOptionButton = new JButton("Sign up");
        changeOptionButton.setPreferredSize(new Dimension(300,25));
        changeOptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(centerPanel.getLayout());
                cl.next(centerPanel);

                String s = changeOptionButton.getText();
                if(s.equals("Sign up"))
                    changeOptionButton.setText("Log in");
                else
                    changeOptionButton.setText("Sign up");

            }
        });
        changeOptionButton.setFocusable(false);
        changeOptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(changeOptionButton);
        bottomPanel.setBackground(new Color(220,220,220));
    }

    void initPanels(){
        topPanel = new TopPanel();
        centerPanel = new CenterPanel();
        this.add(topPanel);
        this.add(centerPanel);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(bottomPanel);
        this.add(Box.createRigidArea(new Dimension(0,20)));
    }
}
