import Shapes.ShapeTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class View extends JPanel implements ActionListener {
    public final int WIDTH = 10;
    public final int HEIGHT = 15;

    private JLabel statusBar;
    private Model model;
    private Controller controller;

    private static Dimension dimension = new Dimension(400, 600);

    View() {
        controller = new Controller(this);
        model = new Model(controller);

        statusBar = new JLabel("0");
        add(statusBar, BorderLayout.SOUTH);
        setPreferredSize(dimension);
        setFocusable(true);
        repaint();
    }

    public Model getModel() {
        return model;
    }

    public void setStatusBar(String text) {
        statusBar.setText(text);
        repaint();
    }

    public void start() {
        controller.init();
        controller.start();
    }


    private int squareWidth() {
        return (int) getSize().getWidth() / WIDTH;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    private void draw(Graphics g) {
        var size = getSize();
        int boardTop = (int) size.getHeight() - HEIGHT * squareHeight();

        for (int i = 0; i < HEIGHT; i++) { //рисуем уже установившиеся фигурки
            for (int j = 0; j < WIDTH; j++) {
                ShapeTypes shape = model.shapeTypeAt(j, HEIGHT - i - 1);
                if (shape != ShapeTypes.POINT)
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
            }
        }

        if (model.getCurrentShapeType() != ShapeTypes.POINT) {  //рисуем еще не упавшую до конца фигурку
            Point point;
            for (int i = 0; i < 4; i++) {
                point = model.getPoint(i);
                int x = model.currentX + point.x;
                int y = model.currentY - point.y;

                drawSquare(g, x * squareWidth(), boardTop + (HEIGHT - y - 1) * squareWidth(), model.getCurrentShapeType());
            }
        }
    }

    private void drawSquare(Graphics g, int x, int y, ShapeTypes shape) {
        Color[] colors = { new Color(204, 102, 102), new Color(102, 204, 102),
                new Color(102, 102, 204), new Color(204, 204, 102),
                new Color(204, 102, 204), new Color(102, 204, 204),
                new Color(218, 170, 0),  new Color(0, 0, 0)
        };

        var color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.doGameCycle();
    }

    public void keyPressed(KeyEvent e) {
        if (model.getCurrentShapeType() == ShapeTypes.POINT) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> controller.goLeft();
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> controller.goRight();
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> controller.rotateLeft();
            case KeyEvent.VK_UP, KeyEvent.VK_W -> controller.rotateRight();

            case KeyEvent.VK_SPACE -> controller.dropDown();
        }
        repaint();
    }
}
