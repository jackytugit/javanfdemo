package switchpattern;

public class Main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(5,4);
        System.out.println("Perimeter of r is: " + ShapeWithSwitchExpression.getPerimeter(r));
        //System.out.println("Perimeter of r is: " + ShapeWithSwitchStatement.getPerimeter(r));

        Circle c = new Circle(6);
        System.out.println("Perimeter of c is: " + ShapeWithSwitchExpression.getPerimeter(c));
        //System.out.println("Perimeter of c is: " + ShapeWithSwitchStatement.getPerimeter(c));
    }
}
