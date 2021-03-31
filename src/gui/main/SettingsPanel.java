package gui.main;

import database.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends BasePanel {
    JComboBox comboBox;
    JPasswordField oldPasswordField;
    JPasswordField newPasswordField;
    JPasswordField newPasswordFieldAgain;
    JButton saveButton;
    public SettingsPanel(String panelName){
        super(panelName);
        initComponents();
    }

    private void initComponents() {
        String[] languages = {"Polski", "English"};
        comboBox = new JComboBox(languages);
        comboBox.setFocusable(false);
        comboBox.setBounds(75,125,75,25);
        this.add(comboBox);

        oldPasswordField = getJPasswordField(250);
        this.add(oldPasswordField);
        newPasswordField = getJPasswordField(285);
        this.add(newPasswordField);
        newPasswordFieldAgain = getJPasswordField(320);
        this.add(newPasswordFieldAgain);
        saveButton = new JButton("Save");
        saveButton.setBounds(225,450,75,30);
        saveButton.setFocusable(false);
        saveButton.addActionListener(e -> {
            //TODO: choose language
            //System.out.println(comboBox.getSelectedIndex());

            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword1 = new String(newPasswordField.getPassword());
            String newPassword2 = new String(newPasswordFieldAgain.getPassword());
            if(oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")){
                JOptionPane.showMessageDialog(getRootPane().getParent(),"Error!\n Fill in all fields!");
                return; }
            if(!CurrentUser.getUserPassword().equals(oldPassword)){
                JOptionPane.showMessageDialog(getRootPane().getParent(),"Error!\n Old password is incorrect!");
                return; }
            if(!newPassword1.equals(newPassword2)){
                JOptionPane.showMessageDialog(getRootPane().getParent(),"Error!\n New password fields are not the same!");
                return;
            }
            if(newPassword1.equals(oldPassword)){
                JOptionPane.showMessageDialog(getRootPane().getParent(),"Error!\n New password has to be different!");
                return;
            }
            CurrentUser.setUserPassword(newPassword1);
            CurrentUser.updateUser();
            JOptionPane.showMessageDialog(getRootPane().getParent(),"Success!\n Password has been changed!");
        });
        this.add(saveButton);
    }
    private JPasswordField getJPasswordField(int y){
        JPasswordField password = new JPasswordField();
        password.setBounds(250,y,180,25);
        return password;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 25));
        g.drawString("Language", 50, 100);
        g.drawLine(50,105,165,105);
        g.drawString("Change password", 50, 225);
        g.drawLine(50,230,250,230);
        g.setFont( new Font("Ink Free",Font.BOLD, 18));
        g.drawString("Old password:", 75, 265);
        g.drawString("New password:", 75, 300);
        g.drawString("Again new password:", 75, 335);

    }
}
