package graphics;

import java.awt.*;

public class Trunk implements XmasShape{
    private double scale;
    private Color  color;
    private int x;
    private int y;

    Trunk(int x, int y, double scale, Color color) {
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
        g2d.setColor(color);
        g2d.fillRect(0, 0, 100, 80);
    }
}
