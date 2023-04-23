package domain;

import javafx.scene.paint.Color;

public class Circle implements Shape{

    private Circular circular;

    public Circle(Circular circular) {
        this.circular = circular;
    }

    @Override
    public void setColor(Color color) {
        java.awt.Color colorawt=new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue());
        circular.setColor(colorawt);
    }

    @Override
    public void scale(float factor) {
        circular.scale(factor);
    }
}
