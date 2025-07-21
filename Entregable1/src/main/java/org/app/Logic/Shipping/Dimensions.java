package org.app.Logic.Shipping;

public class Dimensions {
    private double height;
    private double width;
    private double length;

    public Dimensions(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getVolume() {
        return height * width * length;
    }

}
