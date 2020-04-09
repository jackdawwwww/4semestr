package Shapes;

import java.awt.*;

public class SquareShape extends Shape implements ShapeInterface {

    Point[] points = {
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, 1),
            new Point(1, 1)
    };

    ShapeTypes type = ShapeTypes.SQUARE;

    public SquareShape() {
        super.init(points, type);
    }
}
