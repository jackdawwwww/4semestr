package Shapes;

import java.awt.*;

public class LShape  extends Shape implements ShapeInterface {
    Point[] points = {
            new Point(-1, -1),
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1)
    };

    ShapeTypes type = ShapeTypes.BIGL;

    public LShape() {
        super.init(points, type);
    }
}
