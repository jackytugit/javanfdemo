package sealedclass;

public sealed class Rectangle extends Shape permits FilledRectangle
{
    private double length;
    private double width;

    public Rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    public double getLength()
    {
        return length;
    }

    public double getWidth()
    {
        return width;
    }
}
