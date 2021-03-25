package gui.main;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    int PANEL_WIDTH=150;
    int PANEL_HEIGHT=500;
    MenuPanel(){
    this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(new Color(220,220,220));
        this.setLayout(null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        //g.setColor(new Color(128, 128, 128));
        g.setFont( new Font("Ink Free",Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("QuizApp", (PANEL_WIDTH-metrics.stringWidth("QuizApp"))/2, 45);
        g.setFont( new Font("Ink Free",Font.BOLD, 25));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Menu", (PANEL_WIDTH-metrics.stringWidth("Menu"))/2, 90);
        g.drawLine(25,93,125,93);
    }
}
