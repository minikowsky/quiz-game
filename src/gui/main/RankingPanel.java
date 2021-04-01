package gui.main;

import database.Driver;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class RankingPanel extends BasePanel {
    JScrollPane scrollPane;
    JTextArea rankingArea;

    public RankingPanel(String panelName){
        super(panelName);
        initComponents();
        refresh();
    }
    public void refresh() {
        rankingArea.setText("");
        ArrayList<String> usersList = Driver.getUserList();
        for(String s: usersList){
            rankingArea.append(s);
        }

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
