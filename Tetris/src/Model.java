import Shapes.ShapeInterface;
import Shapes.ShapeTypes;

import java.awt.*;
import java.util.Random;

public class Model {
    public final int WIDTH = 10;
    public final int HEIGHT = 15;

    private boolean isFallen = true;
    private boolean isStop = false;

    private ShapeInterface currentShape;
    private ShapesFactory factory = new ShapesFactory();
    private ShapeTypes[] board;

    public int currentX = 0;
    public int currentY = 0;

    private int score;


    public Model() {
        currentShape = factory.getShape(ShapeTypes.POINT);
    }

    public void init() {
        board = new ShapeTypes[HEIGHT * WIDTH];
        clear();
    }

    private void setCurrentShape(ShapeTypes currentShape) {
        this.currentShape = factory.getShape(currentShape);
    }

    void newShape() {
        Random rand = new Random();
        int num = rand.nextInt(7);
        ShapeTypes[] types = ShapeTypes.values();

        setCurrentShape(types[num]);

        currentX = WIDTH / 2;
        currentY = HEIGHT - 1 + minY();

        if (!possibleToMove(currentX, currentY)) {
            setCurrentShape(ShapeTypes.POINT);
            isStop = true;
        }
    }

    void clear() {
        for(int i = 0; i < HEIGHT * WIDTH; i++)
            board[i] = ShapeTypes.POINT;
    }

    void droppedDown() {
        Point point;
        for(int i = 0; i < 4; i++) {
            point = currentShape.getPoint(i);
            int x = currentX + point.x;
            int y = currentY - point.y;

            board[(y * WIDTH + x)] = getCurrentShapeType();
        }

        removeFullLines();
        if(!isFallen) newShape();
    }


    private void removeFullLines() {
        int fullLinesNum = 0, i;

        for(i = HEIGHT - 1; i >= 0; i--) {
            boolean isFull = true;

            for(int j = 0; j < WIDTH; j++) {
                if(shapeTypeAt(j, i) == ShapeTypes.POINT) {
                    isFull = false;
                    break;
                }
            }

            if(isFull) {
                fullLinesNum++;

                for(int k = i; k < HEIGHT - 1; k++) {
                    for(int j = 0; j < WIDTH; j++) {
                        board[k * WIDTH + j] = shapeTypeAt(j, k + 1);
                    }
                }
            }
        }

        if(fullLinesNum > 0) {
            score += fullLinesNum * WIDTH;

            isFallen = true;
            setCurrentShape(ShapeTypes.POINT);
        }
    }


    boolean possibleToMove(int x, int y) {
        Point point;
        for (int i = 0; i < 4; i++) {
            point = currentShape.getPoint(i);
            int X = x + point.x;
            int Y = y - point.y;

            if (X < 0 || X >= WIDTH || Y < 0 || Y >= HEIGHT)
                return false;

            if (shapeTypeAt(X, Y) != ShapeTypes.POINT)
                return false;
        }

        currentX = x;
        currentY = y;

        return true;
    }


    ShapeTypes shapeTypeAt(int x, int y) {
        if(WIDTH * HEIGHT <= (y * WIDTH) + x) return ShapeTypes.POINT;
        return board[(y * WIDTH) + x];
    }

    private int rotateCheck() {
        if (currentShape.getType() == ShapeTypes.SQUARE) return 0;

        Point point;
        int i;
        for (i = 0; i < 4; i++) {
            point = currentShape.getPoint(i);
            if(point.y + currentX >= WIDTH || point.y + currentX < 0 ||
                    point.x + currentY < 0 || point.x + currentY >= HEIGHT) break;
        }

        return i;
    }

    void rotateRight() {
        int i = rotateCheck();
        Point point;

        if(i == 4) {
            for(i = 0; i < 4; i++) {
                point = currentShape.getPoint(i);
                currentShape.setPoint(i, -1 * point.y, point.x);
            }
        }
    }

    void rotateLeft() {
        int i = rotateCheck();
        Point point;

        if(i == 4) {
            for(i = 0; i < 4; i++) {
                point = currentShape.getPoint(i);
                currentShape.setPoint(i, point.y, -1 * point.x);
            }
        }
    }

    void goRight() {
        possibleToMove(currentX + 1, currentY);
    }

    void goLeft() {
        possibleToMove(currentX - 1, currentY);
    }

    private int minY() {
        int min = currentShape.getPoint(0).y;

        for (int i = 0; i < 4; i++)
            min = Math.min(min, currentShape.getPoint(i).y);

        return min;
    }

    boolean isFallen() { return isFallen; }

    void setIsFallen(boolean f) { isFallen = f; }

    boolean isStop() { return isStop; }

    int getScore() { return score; }

    Point getPoint(int num) {
        return currentShape.getPoint(num);
    }

    ShapeTypes getCurrentShapeType() { return currentShape.getType(); }
}
