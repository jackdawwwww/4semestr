import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreWindow extends JPanel {
    ArrayList<String> strings;

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        int y = 30;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = new Font("About", Font.PLAIN, 15);
        g2.setFont(font);

        for(String i: strings) {
            g2.drawString(i, 30, y + 30);
            y += 30;
        }
    }
}
