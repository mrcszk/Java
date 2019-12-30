package graphics;

import java.awt.*;

public class Gift implements XmasShape{
    private double scale;
    private GradientPaint color;
    private int x;
    private int y;
    private int w;
    private int h;

    Gift(int x, int y, int w, int h, double scale, GradientPaint color) {
        this.color = color;
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
    @Override
    public void render(Graphics2D g2d) {
        g2d.setPaint(color);
        g2d.fillRect(0, 0, w, h);
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0,20, w, 25);
        g2d.fillRect(20, 0,25,h);
    }
}
