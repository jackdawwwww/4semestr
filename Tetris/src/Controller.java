import javax.swing.*;

public class Controller {
    private Model model;
    private int score;

    private Timer timer;
    View view;

    Controller(View view) {
        this.view = view;
        model = view.getModel();
    }

    public void init() {
        timer = new javax.swing.Timer(400, view);
        timer.start();
        model.init();
    }

    void doGameCycle() {
        if(model.isStop()) {
            view.setStatusBar("Game over");
            return;
        }
        if(model.isFallen()) {
            model.setIsFallen(false);
            model.newShape();
        } else {
            oneLineDown();
        }
        view.repaint();
    }

    void start() {
        model.setIsFallen(false);
        score = 0;
        model.clear();
        model.newShape();
        timer.start();
    }

    private void oneLineDown() {
        if(!model.possibleToMove(model.currentX, model.currentY - 1)) model.droppedDown();
    }

    void dropDown() {
        int newY = model.currentY;

        while(newY > 0) {
            if(!model.possibleToMove(model.currentX, newY - 1)) {
                model.setIsFallen(true);
                break;
            }
            newY--;
            view.viewRepaint();
        }
        model.droppedDown();
        score = model.getScore();
        view.setStatusBar(String.valueOf(score));
    }

    void goRight() { model.goRight();}
    void goLeft() { model.goLeft(); }
    void rotateLeft() { model.rotateLeft(); }
    void rotateRight() { model.rotateRight(); }
}
