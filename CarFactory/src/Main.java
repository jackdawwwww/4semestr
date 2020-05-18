import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main {
    private static Dimension menuSize = new Dimension(340, 110);
    private static Dimension buttonSize = new Dimension(100, 60);
    private final View view;

    public static void main(String[] args) throws Exception {
        new Main().makeGraphicInterface();
    }

    public Main() throws Exception {
        this.view = new View();
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

        final JButton newGameButton = addMenuButton("Start", 10, gameWindow);
        newGameButton.addActionListener(e -> {
            try {
                startGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        final JButton aboutButton = addMenuButton("Info", 120, gameWindow);
        aboutButton.addActionListener(e -> {
            try {
                getInfo();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        final JButton exitButton = addMenuButton("Exit", 230, gameWindow);
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(exitButton, "Exit", "OK", JOptionPane.WARNING_MESSAGE );
            view.cancel();
            System.exit(0);
        });

        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
    }

    void getInfo() throws IOException {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new InfoWindow());
        frame.setSize(400, 430);
        frame.setVisible(true);
        frame.setLocation(410, 120);
    }

    void startGame() throws Exception {
        JDialog dialog = new JDialog((JFrame) null, "Car factory");
        dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        view.appear();
        dialog.getContentPane().add(view);

        dialog.setVisible(true);
        dialog.pack();
        dialog.setResizable(true);
        dialog.setLocation(0, 120);
        dialog.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosing(WindowEvent e) {

                view.cancel();
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