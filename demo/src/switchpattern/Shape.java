package switchpattern;

public interface Shape {
    public static double getPerimeter(Shape shape) throws IllegalArgumentException {
        if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return 2 * r.length() + 2 * r.width();
        } else if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return 2 * c.radius() * Math.PI;
        } else {
            throw new IllegalArgumentException("Unrecognized shape");
        }
    }
}
