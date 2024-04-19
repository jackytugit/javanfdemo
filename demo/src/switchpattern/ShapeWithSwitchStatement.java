package switchpattern;

public interface ShapeWithSwitchStatement
{
    public static double getPerimeter(Shape shape) throws IllegalArgumentException {
        switch (shape) {
            case Rectangle r: return 2 * r.length() + 2 * r.width();
            case Circle c:    return 2 * c.radius() * Math.PI;
            default:          throw new IllegalArgumentException("Unrecognized shape");
        }
    }
}
