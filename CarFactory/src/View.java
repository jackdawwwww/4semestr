import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class View extends JPanel implements ActionListener {
    private Model model;
    private Controller controller;
    JSlider engSlider, bodySlider, accSlider, dealSlider;
    JLabel engStInfo, bodyStInfo, accStInfo, carStInfo, text;

    private static Dimension dimension = new Dimension(900, 400);

    View() throws Exception {
        controller = new Controller(this);
        model = new Model(controller);
        tuneView();
    }

    private void tuneView() {
        setPreferredSize(dimension);
        tuneSliders();
        tuneLabels();

        add(engSlider);
        add(bodySlider);
        add(accSlider);
        add(dealSlider);
        add(text);
        add(engStInfo);
        add(bodyStInfo);
        add(accStInfo);
        add(carStInfo);
    }

    private void tuneLabels() {
        text = setLabel();
        engStInfo = new JLabel();
        bodyStInfo = new JLabel();
        accStInfo = new JLabel();
        carStInfo = new JLabel();

        setEngValues(0, 0);
        setBodyValues(0, 0);
        setAccValues(0, 0);
        setCarValues(0, 0);

        engStInfo.setPreferredSize(new Dimension(900, 50));
        engStInfo.setPreferredSize(new Dimension(500, 50));
        bodyStInfo.setPreferredSize(new Dimension(500, 50));
        accStInfo.setPreferredSize(new Dimension(500, 50));
        carStInfo.setPreferredSize(new Dimension(500, 50));
    }

    private void tuneSliders() {
        engSlider = setSlider();
        engSlider.addChangeListener(e -> controller.setTimeForEngine(((JSlider)e.getSource()).getValue() / 25));

        bodySlider = setSlider();
        bodySlider.addChangeListener(e -> controller.setTimeForBody(((JSlider)e.getSource()).getValue() / 25));

        accSlider = setSlider();
        accSlider.addChangeListener(e -> controller.setTimeForAcc(((JSlider)e.getSource()).getValue() / 25));

        dealSlider = setSlider();
        dealSlider.addChangeListener(e -> controller.setTimeForDeal(((JSlider)e.getSource()).getValue() / 25));
    }

    private JSlider setSlider() {
        JSlider slider = new JSlider();
        slider.setMinorTickSpacing(25);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        Hashtable<Integer, JLabel> position = new Hashtable<>();
        position.put(0, new JLabel("0"));
        position.put(25, new JLabel("1"));
        position.put(50, new JLabel("2"));
        position.put(75, new JLabel("3"));
        position.put(100, new JLabel("4"));

        slider.setLabelTable(position);

        return slider;
    }

    private JLabel setLabel() {
        String text = "Time for one: ", str1 = text + "engine                 ", str2 = text + "body                  ",
                                        str3 = text + "accessory                ", str4 = text + "deal";

        return new JLabel(str1 + str2 + str3 + str4, JLabel.CENTER);
    }

    void appear() {
        setFocusable(true);
        setVisible(true);

        repaint();
    }

    Model getModel() {
        return model;
    }

    void start() throws Exception {
        controller.init();
        controller.start();
    }

    void cancel() {
        controller.cancel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.work();
    }

    void setEngValues(int v1 ,int v2) {
        editLabel(engStInfo, "Engine ", v1, v2);
        repaint();
    }
    void setBodyValues(int v1 ,int v2) {
        editLabel(bodyStInfo, "Body ",  v1, v2);
        repaint();
    }
    void setAccValues(int v1 ,int v2) {
        editLabel(accStInfo, "Accessory ", v1, v2);
        repaint();
    }
    void setCarValues(int v1 ,int v2) {
        editLabel(carStInfo, "Car ", v1, v2);
        repaint();
    }

    void editLabel(JLabel label, String name, int v1, int v2) {
        String s1 =  " was made at all, ", s2 = " - is in the Store now\n";
        String s = name + "store: " + v1 + s1 + v2 + s2;
        label.setText(s);
    }


}
