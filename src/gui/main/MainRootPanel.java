package gui.main;

import database.CurrentUser;
import gui.login.EntryFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainRootPanel extends JPanel {
    JPanel menuPanel;
    JPanel cardPanel;
    QuizPanel quizPanel;
    RankingPanel rankingPanel;
    TutorialPanel tutorialPanel;
    SettingsPanel settingsPanel;
    JButton playButton;
    JButton rankingButton;
    JButton tutorialButton;
    JButton settingsButton;
    JButton logoutButton;
    int btnWidth = 100;
    int btnHeight = 25;
    public MainRootPanel(){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        menuPanel = new MenuPanel();
        cardPanel = new JPanel(new CardLayout());
        initMenuPanel();
        initCards();
        this.add(menuPanel);
        this.add(cardPanel);

    }
    private void initMenuPanel() {

        //play
        playButton = getMenuButton("Play",100);
        playButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel,"quiz");
        });
        menuPanel.add(playButton);
        //ranking
        rankingButton = getMenuButton("Ranking",135);
        rankingButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel,"ranking");
        });
        menuPanel.add(rankingButton);
        //Tutorial
        tutorialButton = getMenuButton("Tutorial",170);
        tutorialButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel,"tutorial");
        });
        menuPanel.add(tutorialButton);
        //Settings
        settingsButton = getMenuButton("Settings",205);
        settingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cardPanel.getLayout());
            cl.show(cardPanel,"settings");
        });
        menuPanel.add(settingsButton);
        //logout
        logoutButton = getMenuButton("Logout",240);
        logoutButton.addActionListener(e -> {
            CurrentUser.setUser(0,null,null);
            ((Window)getRootPane().getParent()).dispose();
            new EntryFrame();
        });
        menuPanel.add(logoutButton);

        this.add(menuPanel);
    }

    private void initCards() {
        quizPanel = new QuizPanel();
        cardPanel.add("quiz",quizPanel);
        rankingPanel = new RankingPanel("Ranking");
        cardPanel.add("ranking",rankingPanel);
        tutorialPanel = new TutorialPanel("Tutorial");
        cardPanel.add("tutorial",tutorialPanel);
        settingsPanel = new SettingsPanel("Settings");
        cardPanel.add("settings",settingsPanel);
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel,"ranking");
    }
    private JButton getMenuButton(String name, int y){
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.setSize(btnWidth,btnHeight);
        button.setBounds(25,y, btnWidth, btnHeight);
        return button;
    }
}
