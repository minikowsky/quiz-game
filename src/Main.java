import database.Driver;
import gui.login.EntryFrame;
import gui.main.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Driver.connect();
                new EntryFrame();
                //new MainFrame();
            }
        });
    }
}
