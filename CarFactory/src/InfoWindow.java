import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InfoWindow extends JPanel {
    MyProperties properties;

    InfoWindow() throws IOException {
        properties = new MyProperties();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        String str1 =  "Body store size = " + properties.bSize();
        String str2 =  "Engine store size = " + properties.eSize();
        String str3 =  "Accessories store size = " + properties.aSize();
        String str4 =  "Car store size = " + properties.cSize();
        String str5 =  "Workers num = " + properties.wNum();
        String str6 =  "Dealers num = " + properties.dNum();
        String str7 =  "Accessories num = " + properties.accNum();
        String str8 =  "Use log = " + properties.useLog();

        Font font = new Font("About", Font.PLAIN, 20);
        g2.setFont(font);
        g2.drawString(str1, 30, 50);
        g2.drawString(str2, 30, 100);
        g2.drawString(str3, 30, 150);
        g2.drawString(str4, 30, 200);
        g2.drawString(str5, 30, 250);
        g2.drawString(str6, 30, 300);
        g2.drawString(str7, 30, 350);
        g2.drawString(str8, 30, 400);
    }

}