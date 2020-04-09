package Shapes;

import javax.sound.sampled.Line;
import java.awt.*;

public class LineShape  extends Shape implements ShapeInterface{
    Point[] points = {
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2)
    };

    ShapeTypes type = ShapeTypes.LINE;

    public LineShape() {
        super.init(points, type);
    }
}
