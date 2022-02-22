package lab10;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(this.fillColor);
        g2d.fillOval(0, 0, 100, 100);
        g2d.setColor(this.lineColor);
        g2d.drawOval(0, 0, 100, 100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}
