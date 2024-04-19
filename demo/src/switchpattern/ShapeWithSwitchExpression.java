package switchpattern;

public interface ShapeWithSwitchExpression
{
    public static double getPerimeter(Shape shape) throws IllegalArgumentException {
        return switch (shape) {
            case Rectangle r -> 2 * r.length() + 2 * r.width();
            case Circle c    -> 2 * c.radius() * Math.PI;
            default          -> throw new IllegalArgumentException("Unrecognized shape");
        };
    }
}
