import Shapes.*;
import Shapes.ShapeInterface;

public class ShapesFactory {
    public ShapeInterface getShape(ShapeTypes type) {
        return switch (type) {
            case BIGL -> new LShape();
            case BIGLMIRR -> new LMirroredShape();
            case PEDESTAL -> new PedestalShape();
            case SQUARE -> new SquareShape();
            case STAIRSMIRR -> new StairsMirroredShape();
            case STAIRS -> new StairsShape();
            case LINE -> new LineShape();
            default -> new PointShape();
        };
    }
}
