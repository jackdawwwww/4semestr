import java.io.IOException;

public class Controller {
    private View view;
    private Model model;


    Controller(View view) {
        this.view = view;
    }

    void init() throws IOException {
        model = view.getModel();
        model.init();
    }

    void start() throws Exception {
        model.start();
    }

    void cancel() {
        model.cancel();
    }

    View getView() {
        return view;
    }

    void repaint() {
        view.repaint();
    }

    void work() { model.work(); }

    void setEngValues(int v1 ,int v2) {
        view.setEngValues(v1, v2);
        repaint();
    }
    void setBodyValues(int v1 ,int v2) {
        view.setBodyValues(v1, v2);
        repaint();
    }
    void setAccValues(int v1 ,int v2) {
        view.setAccValues(v1, v2);
        repaint();
    }
    void setCarValues(int v1 ,int v2) {
        view.setCarValues(v1, v2);
        repaint();
    }


    void setTimeForEngine(int time) {
        model.setTimeForEngine(time);
    }

    void setTimeForBody(int time) {
        model.setTimeForBody(time);
    }

    void setTimeForAcc(int time) {
        model.setTimeForAcc(time);
    }

    void setTimeForDeal(int time) {
        model.setTimeForDeal(time);
    }
}
