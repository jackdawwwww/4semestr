package Shapes;

import java.awt.*;

public class PointShape  extends Shape implements ShapeInterface {

    Point[] points = {
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0)
    };

    ShapeTypes type = ShapeTypes.POINT;

    public PointShape() {
        super.init(points, type);
    }
}
