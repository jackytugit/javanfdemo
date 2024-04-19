package sealedclass;

public final class FilledRectangle extends Rectangle
{
    private int red;
    private int green;
    private int blue;

    public FilledRectangle(double length, double width, int red, int green, int blue)
    {
        super(length, width);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }
}
