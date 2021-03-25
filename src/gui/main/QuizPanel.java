package gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

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
    List<Integer> order = new ArrayList<Integer>();
    ArrayList<String> questions = new ArrayList<String>(3);
    ArrayList<String> aAnswer = new ArrayList<String>(3);
    ArrayList<String> bAnswer = new ArrayList<String>(3);
    ArrayList<String> cAnswer = new ArrayList<String>(3);
    ArrayList<String> dAnswer = new ArrayList<String>(3);
    ArrayList<String> correct = new ArrayList<String>(3);
    JLabel introLabel;
    JButton startButton;
    JLabel resultLabel;
    JButton againButton;
    public QuizPanel(){

        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(new Color(128, 128, 128));
        this.setLayout(null);
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft-=DELAY;
                timeBar.setValue(timeLeft);
                if(timeLeft<=0){
                    displayAnswer("");
                }
            }
        });

        introLabel = new JLabel("Press 'Start' to begin!");
        Font font = new Font("Ink Free",Font.BOLD, 35);
        introLabel.setFont(font);
        FontMetrics metrics = getFontMetrics(introLabel.getFont());
        introLabel.setBounds((SCREEN_SIZE.width-metrics.stringWidth(introLabel.getText()))/2,SCREEN_SIZE.height/3,
                metrics.stringWidth(introLabel.getText()), metrics.getHeight());
        introLabel.setForeground(Color.WHITE);
        this.add(introLabel);
        startButton = new JButton("Start");
        startButton.setFocusable(false);
        startButton.setSize(100,50);
        startButton.setBounds((SCREEN_SIZE.width-startButton.getWidth())/2,3*SCREEN_SIZE.height/4,
                startButton.getWidth(),startButton.getHeight());
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel = null;
                introLabel.setVisible(false);
                startButton.setVisible(false);
                initComponents();
                startQuiz();
            }
        });
        this.add(startButton);
    }
    private void initComponents() {
        //number
        Font font = new Font("Ink Free",Font.BOLD, 35);
        numberLabel = new JLabel();
        numberLabel.setFont(font);
        numberLabel.setForeground(Color.WHITE);
        this.add(numberLabel);
        //question
        font = new Font("Ink Free",Font.BOLD, 20);
        questionLabel = new JLabel();
        questionLabel.setFont(font);
        questionLabel.setForeground(Color.WHITE);
        this.add(questionLabel);
        //buttons
        aButton = getQuizButton(25,275);
        aButton.addActionListener(this);
        bButton = getQuizButton(275,275);
        bButton.addActionListener(this);
        cButton = getQuizButton(25,375);
        cButton.addActionListener(this);
        dButton = getQuizButton(275,375);
        dButton.addActionListener(this);
        this.add(aButton);
        this.add(bButton);
        this.add(cButton);
        this.add(dButton);
        //progressbar
        timeBar = new JProgressBar(JProgressBar.HORIZONTAL,0,10000);
        timeBar.setForeground(Color.GREEN);
        timeBar.setValue(timeBar.getMaximum());
        timeBar.setBounds(25,480,450,10);
        this.add(timeBar);
    }
    private JButton getQuizButton(int x, int y){
        JButton button = new JButton();
        button.setFocusable(false);
        button.setSize(buttonDimension);
        button.setBounds(x,y,button.getWidth(), button.getHeight());
        return button;
    }/*
    private ImageIcon getScaled(ImageIcon img){
        return new ImageIcon(img.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
    }*/
    private void startQuiz() {
        points=0;
        currentQuestion = -1;
        getRandomQuestions();
        nextQuestion();
    }
    private void getRandomQuestions(){
        questions.add("Jaki jest najważnieszy jezyk w programowaniu?");
        aAnswer.add("A: Python");
        bAnswer.add("B: Java");
        cAnswer.add("C: C++");
        dAnswer.add("D: Angielski");
        correct.add("D: Angielski");

        questions.add("Który z poniższych jezyków jest najstarszy?");
        aAnswer.add("A: PHP");
        bAnswer.add("B: HTML");
        cAnswer.add("C: Python");
        dAnswer.add("D: Java");
        correct.add("C: Python");

        questions.add("Co to jest IDE?");
        aAnswer.add("A: Jest to zintegrowane środowisko programistyczne");
        bAnswer.add("B: Jest to interfejs programowania aplikacji");
        cAnswer.add("C: Jest to darmowe oprogramowanie udpostępniające środowisko niezbędne do programowania w Javie");
        dAnswer.add("D: Jest to rozproszony system kontroli wersji");
        correct.add("A: Jest to zintegrowane środowisko programistyczne");
        for(int i=0;i<numberOfQuestions;i++){
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
            questionLabel.setText(questions.get(order.get(currentQuestion)));
            metrics = getFontMetrics(numberLabel.getFont());
            questionLabel.setBounds(50,150,metrics.stringWidth(questionLabel.getText()), metrics.getHeight());
            System.out.println(questionLabel.getText());
            aButton.setText(aAnswer.get(order.get(currentQuestion)));
            bButton.setText(bAnswer.get(order.get(currentQuestion)));
            cButton.setText(cAnswer.get(order.get(currentQuestion)));
            dButton.setText(dAnswer.get(order.get(currentQuestion)));
            timer.start();
        }
    }
    boolean checkAnswer(String correctAnswer){
        return correctAnswer.equals(correct.get(order.get(currentQuestion)));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String answer = "";
        if (aButton.equals(source)) {
            answer = aButton.getText();
            if(checkAnswer(answer)) {
                points++;
            }
        } else if (bButton.equals(source)) {
            answer = bButton.getText();
            if(checkAnswer(answer)) {
                points++;
            }

        } else if (cButton.equals(source)) {
            answer = cButton.getText();
            if(checkAnswer(answer)){
                points++;
            }

        } else if (dButton.equals(source)) {
            answer = dButton.getText();
            if(checkAnswer(dButton.getText())){
                points++;
            }
        }
        System.out.println(points);
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
            String correctAnswer = correct.get(order.get(currentQuestion));
            System.out.println(correctAnswer);
            if(correctAnswer.equals(aButton.getText()))
                aButton.setBackground(Color.GREEN);
            else if(correctAnswer.equals(bButton.getText()))
                bButton.setBackground(Color.GREEN);
            else if(correctAnswer.equals(cButton.getText()))
                cButton.setBackground(Color.GREEN);
            else
                dButton.setBackground(Color.GREEN);
            if(!answer.equals(correctAnswer)){
                if(answer.equals(aButton.getText()))
                    aButton.setBackground(Color.RED);
                else if(answer.equals(bButton.getText()))
                    bButton.setBackground(Color.RED);
                else if(answer.equals(cButton.getText()))
                    cButton.setBackground(Color.RED);
                else
                    dButton.setBackground(Color.RED);
            }
        }
        Timer pause = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aButton.setBackground(Color.GRAY);
                bButton.setBackground(Color.GRAY);
                cButton.setBackground(Color.GRAY);
                dButton.setBackground(Color.GRAY);
                aButton.setEnabled(true);
                bButton.setEnabled(true);
                cButton.setEnabled(true);
                dButton.setEnabled(true);
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    void displayResults(){
        numberLabel.setVisible(false);
        questionLabel.setVisible(false);
        aButton.setVisible(false);
        bButton.setVisible(false);
        cButton.setVisible(false);
        dButton.setVisible(false);
        timeBar.setVisible(false);
        resultLabel = new JLabel("You got " + points + " points!");
        Font font = new Font("Ink Free",Font.BOLD, 35);
        resultLabel.setFont(font);
        FontMetrics metrics = getFontMetrics(resultLabel.getFont());
        resultLabel.setBounds((SCREEN_SIZE.width-metrics.stringWidth(resultLabel.getText()))/2,SCREEN_SIZE.height/3,
                metrics.stringWidth(resultLabel.getText()), metrics.getHeight());
        resultLabel.setForeground(Color.WHITE);
        this.add(resultLabel);
        againButton = new JButton("Try again");
        againButton.setFocusable(false);
        againButton.setSize(100,50);
        againButton.setBounds((SCREEN_SIZE.width-againButton.getWidth())/2,3*SCREEN_SIZE.height/4,
                againButton.getWidth(),againButton.getHeight());
        againButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setVisible(false);
                againButton.setVisible(false);
                numberLabel.setVisible(true);
                questionLabel.setVisible(true);
                aButton.setVisible(true);
                bButton.setVisible(true);
                cButton.setVisible(true);
                dButton.setVisible(true);
                timeBar.setVisible(true);
                startQuiz();
            }
        });
        this.add(againButton);
    }
}
