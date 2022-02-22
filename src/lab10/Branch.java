package lab10;

import java.awt.*;

public class Branch implements XmasShape {
    int x;
    int y;
    double scale;
    double rotate;

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.decode("#008000"));
        g2d.drawLine(0, 0, 0, 100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale, 1);
        g2d.rotate(Math.toRadians(this.rotate));
    }
}
