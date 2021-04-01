package gui.main;

import database.CurrentUser;
import database.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class QuizPanel extends JPanel implements ActionListener{
    final Dimension SCREEN_SIZE = new Dimension(500, 500);
    final int DELAY = 100; // ms
    int timeLeft = 10000; // 1s
    final int numberOfQuestions = 3;
    int currentQuestion;
    int points;
    JLabel numberLabel;
    JLabel questionLabel;
    JButton aButton;
    JButton bButton;
    JButton cButton;
    JButton dButton;
    Dimension buttonDimension = new Dimension(200,50);
    JProgressBar timeBar;
    Timer timer;
    List<Integer>order = new ArrayList<>();
    ArrayList<ArrayList<String>> questions;
    JLabel introLabel;
    JButton startButton;
    JComboBox difficultyComboBox;
    JLabel resultLabel;
    JButton againButton;
    String gameDifficulty;
    public QuizPanel(){
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);
        timer = new Timer(DELAY, e -> {
            timeLeft-=DELAY;
            timeBar.setValue(timeLeft);
            if(timeLeft<=0){
                displayAnswer("");
            }
        });
        initComponents();
        introView();
    }
    private void introView(){
        //Result view components
        resultLabel.setVisible(false);
        againButton.setVisible(false);
        //Intro components
        introLabel.setVisible(true);
        difficultyComboBox.setVisible(true);
        startButton.setVisible(true);

    }
    private void initComponents() {
        introLabel = new JLabel("Press 'Start' to begin!");
        Font font = new Font("Ink Free",Font.BOLD, 35);
        introLabel.setFont(font);
        FontMetrics metrics = getFontMetrics(introLabel.getFont());
        introLabel.setBounds((SCREEN_SIZE.width-metrics.stringWidth(introLabel.getText()))/2,SCREEN_SIZE.height/3,
                metrics.stringWidth(introLabel.getText()), metrics.getHeight());
        introLabel.setForeground(Color.WHITE);
        this.add(introLabel);
        String[] difficulties = {"Easy","Medium","Hard"};
        difficultyComboBox = new JComboBox(difficulties);
        difficultyComboBox.setSelectedIndex(0);
        gameDifficulty =  (String)difficultyComboBox.getSelectedItem();
        difficultyComboBox.addActionListener(e -> gameDifficulty = (String)difficultyComboBox.getSelectedItem());
        difficultyComboBox.setSize(100,25);
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        difficultyComboBox.setRenderer(dlcr);
        difficultyComboBox.setBounds((SCREEN_SIZE.width-difficultyComboBox.getWidth())/2,3*SCREEN_SIZE.height/5,
                difficultyComboBox.getWidth(),difficultyComboBox.getHeight());
        this.add(difficultyComboBox);
        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.setSize(100,50);
        startButton.setBounds((SCREEN_SIZE.width-startButton.getWidth())/2,3*SCREEN_SIZE.height/4,
                startButton.getWidth(),startButton.getHeight());
        startButton.addActionListener(e -> startQuiz());
        this.add(startButton);
        //number
        numberLabel = new JLabel();
        numberLabel.setFont(font);
        numberLabel.setVisible(false);
        numberLabel.setForeground(Color.WHITE);
        this.add(numberLabel);
        //question
        Font questionFont = new Font("Monospaced",Font.BOLD, 22);
        questionLabel = new JLabel();
        questionLabel.setFont(questionFont);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setVisible(false);
        questionLabel.setBounds(50,75,400, 200);
        this.add(questionLabel);
        //buttons
        aButton = getQuizButton(25,275);
        bButton = getQuizButton(275,275);
        cButton = getQuizButton(25,375);
        dButton = getQuizButton(275,375);
        //progressbar
        timeBar = new JProgressBar(JProgressBar.HORIZONTAL,0,10000);
        timeBar.setForeground(Color.GREEN);
        timeBar.setValue(timeBar.getMaximum());
        timeBar.setBounds(25,480,450,10);
        timeBar.setVisible(false);
        this.add(timeBar);
        //resultLabel
        resultLabel = new JLabel("You got " + points + " points!");
        resultLabel.setFont(font);
        metrics = getFontMetrics(resultLabel.getFont());
        resultLabel.setBounds((SCREEN_SIZE.width-metrics.stringWidth(resultLabel.getText()))/2,SCREEN_SIZE.height/3,
                300, 100);
        resultLabel.setForeground(Color.WHITE);
        this.add(resultLabel);
        //againButton
        againButton = new JButton("Try again");
        againButton.setFocusable(false);
        againButton.setSize(100,50);
        againButton.setBounds((SCREEN_SIZE.width-againButton.getWidth())/2,3*SCREEN_SIZE.height/4,
                againButton.getWidth(),againButton.getHeight());
        againButton.addActionListener(e -> introView());
        this.add(againButton);
    }
    private JButton getQuizButton(int x, int y){
        JButton button = new JButton();
        button.setFocusable(false);
        button.setSize(buttonDimension);
        button.setBounds(x,y,button.getWidth(), button.getHeight());
        button.addActionListener(this);
        button.setVisible(false); //by default
        this.add(button);
        return button;
    }/*
    private ImageIcon getScaled(ImageIcon img){
        return new ImageIcon(img.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
    }*/
    private void startQuiz() {
        //Intro components
        introLabel.setVisible(false);
        difficultyComboBox.setVisible(false);
        startButton.setVisible(false);
        //Game components
        numberLabel.setVisible(true);
        questionLabel.setVisible(true);
        aButton.setVisible(true);
        bButton.setVisible(true);
        cButton.setVisible(true);
        dButton.setVisible(true);
        timeBar.setVisible(true);
        points=0;
        currentQuestion = -1;
        getRandomQuestions();
        nextQuestion();
    }
    private void getRandomQuestions(){
        questions = Driver.getQuestions(gameDifficulty);
        System.out.println();
        for(int i = 0; i < questions.size(); i++){
            order.add(i);
        }
        Collections.shuffle(order);
    }
    void nextQuestion(){
        if(currentQuestion>=numberOfQuestions-1)
            displayResults();
        else {
            timeLeft=10000;
            currentQuestion++;
            numberLabel.setText("#"+(currentQuestion+1));
            FontMetrics metrics = getFontMetrics(numberLabel.getFont());
            numberLabel.setBounds(25,25,metrics.stringWidth(numberLabel.getText()), metrics.getHeight());
            String questionText = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", 300,
                    questions.get(order.get(currentQuestion)).get(1));
            questionLabel.setText(questionText);
            //System.out.println(questionLabel.getText());
            aButton.setText(questions.get(order.get(currentQuestion)).get(2));
            bButton.setText(questions.get(order.get(currentQuestion)).get(3));
            cButton.setText(questions.get(order.get(currentQuestion)).get(4));
            dButton.setText(questions.get(order.get(currentQuestion)).get(5));
            timer.start();
        }
    }
    boolean checkAnswer(String correctAnswer){
        return correctAnswer.equals(questions.get(order.get(currentQuestion)).get(6));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String answer = "";
        if (aButton.equals(source)) {
            answer = "a";
            if(checkAnswer(answer)) {
                points+=Integer.parseInt(questions.get(order.get(currentQuestion)).get(7));
            }
        } else if (bButton.equals(source)) {
            answer = "b";
            if(checkAnswer(answer)) {
                points+=Integer.parseInt(questions.get(order.get(currentQuestion)).get(7));
            }

        } else if (cButton.equals(source)) {
            answer = "c";
            if(checkAnswer(answer)){
                points+=Integer.parseInt(questions.get(order.get(currentQuestion)).get(7));
            }

        } else if (dButton.equals(source)) {
            answer = "d";
            if(checkAnswer(dButton.getText())){
                points+=Integer.parseInt(questions.get(order.get(currentQuestion)).get(7));
            }
        }
        //System.out.println(points);
        displayAnswer(answer);
    }
    void displayAnswer(String answer){
        timer.stop();
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        if(answer.equals("")){
            aButton.setBackground(Color.RED);
            bButton.setBackground(Color.RED);
            cButton.setBackground(Color.RED);
            dButton.setBackground(Color.RED);
        } else {
            String correctAnswer =questions.get(order.get(currentQuestion)).get(6);
            //System.out.println(correctAnswer);
            switch (correctAnswer) {
                case "a" -> aButton.setBackground(Color.GREEN);
                case "b" -> bButton.setBackground(Color.GREEN);
                case "c" -> cButton.setBackground(Color.GREEN);
                default -> dButton.setBackground(Color.GREEN);
            }
            if(!answer.equals(correctAnswer)){
                switch (answer) {
                    case "a" -> aButton.setBackground(Color.RED);
                    case "b" -> bButton.setBackground(Color.RED);
                    case "c" -> cButton.setBackground(Color.RED);
                    default -> dButton.setBackground(Color.RED);
                }
            }
        }
        Timer pause = new Timer(1500, e -> {
            aButton.setBackground(Color.GRAY);
            bButton.setBackground(Color.GRAY);
            cButton.setBackground(Color.GRAY);
            dButton.setBackground(Color.GRAY);
            aButton.setEnabled(true);
            bButton.setEnabled(true);
            cButton.setEnabled(true);
            dButton.setEnabled(true);
            nextQuestion();
        });
        pause.setRepeats(false);
        pause.start();
    }
    void displayResults(){
        //Game components
        numberLabel.setVisible(false);
        questionLabel.setVisible(false);
        aButton.setVisible(false);
        bButton.setVisible(false);
        cButton.setVisible(false);
        dButton.setVisible(false);
        timeBar.setVisible(false);
        //Result components
        resultLabel.setVisible(true);
        againButton.setVisible(true);
        resultLabel.setText("You got " + points + " points!");
        Driver.addResult(CurrentUser.getUserID(),points);
        //Clear arrays
        questions.clear();
        order.clear();
    }
}
