package Shapes;

import java.awt.*;

public class PedestalShape  extends Shape implements ShapeInterface {
    Point[] points = {
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, 1)
    };

    ShapeTypes type = ShapeTypes.PEDESTAL;

    public PedestalShape() {
        super.init(points, type);
    }
}
