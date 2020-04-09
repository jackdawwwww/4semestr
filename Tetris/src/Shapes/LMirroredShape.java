package Shapes;

import java.awt.*;

public class LMirroredShape  extends Shape implements ShapeInterface {
    Point[] points = {
            new Point(1, -1),
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1)
    };

    ShapeTypes type = ShapeTypes.BIGLMIRR;

    public LMirroredShape() {
        super.init(points, type);
    }
}
