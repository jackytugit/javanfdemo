package sealedclass;

public non-sealed class Square extends Shape
{
    private double side;
    public Square(double side)
    {
        this.side = side;
    }

    public double getSide()
    {
        return side;
    }
}
