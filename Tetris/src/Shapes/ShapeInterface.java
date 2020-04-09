package Shapes;

import java.awt.*;

public interface ShapeInterface {
    abstract Point[] getPoints();
    abstract ShapeTypes getType();

    abstract void setPoint(int num, int x, int y);
    abstract Point getPoint(int num);
}
