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
        /*playButton = new JButton("Play");
        playButton.setFocusable(false);
        playButton.setSize(btnWidth,btnHeight);
        playButton.setBounds(25,100, btnWidth, btnHeight);*/
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel,"quiz");
            }
        });
        menuPanel.add(playButton);

        //ranking
        /*rankingButton = new JButton("Ranking");
        rankingButton.setFocusable(false);
        rankingButton.setSize(btnWidth,btnHeight);
        rankingButton.setBounds(25,135, btnWidth, btnHeight);*/
        rankingButton = getMenuButton("Ranking",135);
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel,"ranking");
            }
        });
        menuPanel.add(rankingButton);

        //Tutorial
        /*tutorialButton = new JButton("Tutorial");
        tutorialButton.setFocusable(false);
        tutorialButton.setSize(btnWidth,btnHeight);
        tutorialButton.setBounds(25,170, btnWidth, btnHeight);*/
        tutorialButton = getMenuButton("Tutorial",170);
        tutorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel,"tutorial");
            }
        });
        //mouseEvent
        /*tutorialButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tutorialButton.setBackground(Color.RED);
                tutorialButton.setC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                tutorialButton.setBackground(UIManager.getColor("control"));
            }
        });*/
        menuPanel.add(tutorialButton);

        //Settings
        /*settingsButton = new JButton("Settings");
        settingsButton.setFocusable(false);
        settingsButton.setSize(btnWidth,btnHeight);
        settingsButton.setBounds(25,205, btnWidth, btnHeight);*/
        settingsButton = getMenuButton("Settings",205);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cardPanel.getLayout());
                cl.show(cardPanel,"settings");
            }
        });
        menuPanel.add(settingsButton);

        //exit
        logoutButton = getMenuButton("Logout",240);
        /*logoutButton = new JButton("Logout");
        logoutButton.setFocusable(false);
        logoutButton.setSize(btnWidth,btnHeight);
        logoutButton.setBounds(25,240, btnWidth, btnHeight);*/
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentUser.setUser(0,null,null);
                ((Window)getRootPane().getParent()).dispose();
                new EntryFrame();
            }
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
