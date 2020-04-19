

public class Controller {
    private Model model;
    View view;

    Controller(View view) {
        this.view = view;
    }

    public void init() {
        model = view.getModel();
        model.init();
    }

    void start() {
        model.start();
    }

    View getView() {
        return view;
    }

    void setStatusBar(String str) {
        view.setStatusBar(str);
    }

    void dropDown() {
        model.dropDown();
    }

    void repaint() {
        view.repaint();
    }

    void doGameCycle() {
        model.doGameCycle();
    }

    void goRight() { model.goRight();}
    void goLeft() { model.goLeft(); }
    void rotateLeft() { model.rotateLeft(); }
    void rotateRight() { model.rotateRight(); }
}
