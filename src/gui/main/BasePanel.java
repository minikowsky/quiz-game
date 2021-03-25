package gui.main;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {
    Dimension SCREEN_SIZE = new Dimension(500, 500);
    JLabel panelNameLabel;
    BasePanel(String panelName){
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);
        initPanelName(panelName);
    }
    private void initPanelName(String panelName) {

        Font font = new Font("Ink Free",Font.BOLD, 35);
        panelNameLabel = new JLabel(panelName);
        panelNameLabel.setFont(font);
        FontMetrics metrics = getFontMetrics(panelNameLabel.getFont());
        panelNameLabel.setBounds((SCREEN_SIZE.width-metrics.stringWidth(panelNameLabel.getText()))/2,10,
                metrics.stringWidth(panelNameLabel.getText()), metrics.getHeight());
        panelNameLabel.setForeground(Color.WHITE);
        this.add(panelNameLabel);
    }
}
