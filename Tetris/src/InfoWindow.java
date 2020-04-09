import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JPanel {
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        String str1 =  "Press '<-' or 'A' to move shape left";
        String str2 =  "Press '->' or 'D' to move shape right";
        String str3 =  "Press 'down' or 'S' to tern shape left";
        String str4 =  "Press 'up' or 'W' to tern shape right";
        String str5 =  "Press 'space' to drop shape down";

        Font font = new Font("About", Font.PLAIN, 20);
        g2.setFont(font);
        g2.drawString(str1, 30, 50);
        g2.drawString(str2, 30, 100);
        g2.drawString(str3, 30, 150);
        g2.drawString(str4, 30, 200);
        g2.drawString(str5, 30, 250);
    }

}
