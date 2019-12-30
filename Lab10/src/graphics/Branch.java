package graphics;

import java.awt.*;

public class Branch implements XmasShape {
    private double scale;
    private GradientPaint  color;
    private double x;
    private double y;

    private int w = 160;
    private int h = 100;

    private int[] xP = new int[] {0, w / 2, w};
    private int[] yP = new int[] {h, 0, h};

    Branch(double x, double y, double scale, GradientPaint color) {
        this.color = color;
        this.scale = scale;
        this.x = x;
        this.y = y;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillPolygon(xP, yP, xP.length);
    }
}