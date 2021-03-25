package gui.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RankingPanel extends BasePanel {
    JScrollPane scrollPane;
    JTextArea rankingArea;

    public RankingPanel(String panelName){
        super(panelName);
        initComponents();
        getRanking();
    }

    private void getRanking() {
        rankingArea.append("1.Admin\t100 points\n");
        rankingArea.append("2.Kuba\t 90 points\n");
        rankingArea.append("3.Paulina\t 80 points\n");
        rankingArea.append("4.John\t 70 points\n");
        rankingArea.append("5.Tomasz\t 40 points\n");
        rankingArea.append("6.Maciek\t 10 points\n");
        rankingArea.append("7.Admin\t100 points\n");
        rankingArea.append("8.Kuba\t 90 points\n");
        rankingArea.append("9.Paulina\t 80 points\n");
        rankingArea.append("10.John\t 70 points\n");
        rankingArea.append("11.Tomasz\t 40 points\n");
        rankingArea.append("12.Maciek\t 10 points\n");
        rankingArea.append("13.Admin\t100 points\n");
        rankingArea.append("14.Kuba\t 90 points");
    }

    private void initComponents() {

        rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setBackground(new Color(128, 128, 128));
        rankingArea.setForeground(Color.WHITE);
        rankingArea.setSelectionStart(0);
        //rankingArea.setBorder(new LineBorder(Color.WHITE));
        rankingArea.setBounds(75,100,350,300);
        Font font = new Font("Ink Free",Font.BOLD,20);
        rankingArea.setFont(font);
        this.add(rankingArea);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        scrollPane.setBounds(75,100,350,350);
        scrollPane.getViewport().add(rankingArea);
        this.add(scrollPane);
        repaint();
    }

}
