package Shapes;

import java.awt.*;

public class Shape implements ShapeInterface {
    Point[] points;
    ShapeTypes type;

    void init(Point[] points, ShapeTypes type) {
        this.points = points;
        this.type = type;
    }

    @Override
    public Point[] getPoints() {
        Point p1 = points[0];
        Point p2 = points[1];
        Point p3 = points[2];
        Point p4 = points[3];

        return new Point[]{p1, p2, p3, p4};
    }

    public ShapeTypes getType() {
        return type;
    }

    public void setPoint(int num, int x, int y) {
        points[num].x = x;
        points[num].y = y;
    }

    public Point getPoint(int num) {
        return points[num];
    }
}
