import HighScores.HighScoreManager;
import HighScores.Highscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private static Dimension menuSize = new Dimension(500, 110);
    private static Dimension buttonSize = new Dimension(100, 60);
    private static Highscore highscore;

    static {
        try {
            HighScoreManager highScoreManager = new HighScoreManager();
            highscore = highScoreManager.getHighscore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new Game().makeGraphicInterface();
    }

    private JButton addMenuButton(String text, int x, JFrame frame) {
        JButton button = new JButton(text);
        button.setLocation(x, 10);
        button.setSize(buttonSize);
        frame.add(button);

        return button;
    }

    public void makeGraphicInterface() {
        JFrame gameWindow = new JFrame("Menu");
        gameWindow.setLayout(null);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(menuSize);

        final JButton newGameButton = addMenuButton("New game", 10, gameWindow);
        newGameButton.addActionListener(e -> startGame());

        final JButton highScoresButton = addMenuButton("High Scores", 120, gameWindow);
        highScoresButton.addActionListener(e -> getHighscores());

        final JButton aboutButton = addMenuButton("About", 230, gameWindow);
        aboutButton.addActionListener(e -> getInfo());

        final JButton exitButton = addMenuButton("Exit", 340, gameWindow);
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(exitButton, "Exit", "OK", JOptionPane.WARNING_MESSAGE );

            HighScoreManager.saveAll();
            System.exit(0);
        });

        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
    }

    void getHighscores() {
        ArrayList<String> strings = highscore.getScores();
        ScoreWindow scoreWindow = new ScoreWindow();
        scoreWindow.setStrings(strings);

        JFrame frame = new JFrame();
        frame.getContentPane().add(scoreWindow);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocation(820, 120);
    }

    void getInfo() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new InfoWindow());
        frame.setSize(400, 330);
        frame.setVisible(true);
        frame.setLocation(410, 120);
    }

    void startGame() {
       JDialog dialog = new JDialog((JFrame) null, "Tetris");
       dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

       final View view = new View();
       dialog.getContentPane().add(view);
       view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                view.keyPressed(e);
            }
        });

       dialog.setVisible(true);
       dialog.pack();
       dialog.setResizable(true);
       dialog.setLocation(0, 120);
       dialog.addWindowListener(new WindowListener() {

           @Override
           public void windowOpened(WindowEvent e) { }

           @Override
           public void windowClosing(WindowEvent e) {
               Object[] options = {"YES", "NO"};
               int n = JOptionPane.showOptionDialog(e.getWindow(), "Save Score?", "Tetris",
                       JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

               if(n == 0) {
                   String name = JOptionPane.showInputDialog(null,
                           "Name", "Score", JOptionPane.INFORMATION_MESSAGE);
                   if (name != null) {
                       name = name + " ";
                       highscore.setScore(view.getModel().getScore(), name);
                   }
                }
               e.getWindow().dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
        view.start();
    }
}

