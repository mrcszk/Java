package graphics;

import java.awt.*;

public class Star implements XmasShape {
    private int x;
    private int y;
    private double scale;
    private int p;
    private Color fillColor;
    private Color lineColor;

    Star(int x, int y, double scale, int p, Color fillColor, Color lineColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.p = p;
        this.fillColor = fillColor;
        this.lineColor = lineColor;
    }
    @Override
    public void render(Graphics2D g2d) {
        int[] xV = {-1, 0, 1};
        int[] yV = {0, -4, 0};

        for (int i = 0; i < this.p; i++) {
            g2d.setColor(fillColor);
            g2d.fillPolygon(xV, yV, xV.length);
            g2d.setColor(lineColor);
            g2d.drawPolygon(xV, yV, xV.length);
            g2d.rotate(2*Math.PI/this.p);
        }
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }


}