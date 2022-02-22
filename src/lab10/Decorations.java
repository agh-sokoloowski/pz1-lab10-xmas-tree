package lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Decorations {
    List<XmasShape> decorations = new ArrayList<>();
    Random rand = new Random();

    Decorations() {
        this.generateLights();
        this.generateBubbles();
    }

    private void generateLights() {
        int[][] lightPositions = {
             // { x ,  y , i},
                {385, 130, 4},
                {360, 170, 9},
                {360, 210, 11},
                {310, 250, 15},
                {310, 300, 19}
        };

        for (int[] position : lightPositions) {
            for (int i = 0; i < position[2]; i++) {
                int r, g, b;
                r = g = b = 0;
                while (r + g + b == 0) {
                    r = rand.nextBoolean() ? 255 : 0;
                    g = rand.nextBoolean() ? 255 : 0;
                    b = rand.nextBoolean() ? 255 : 0;
                }

                Light light = new Light();
                light.x = position[0] + 10 * i;
                light.y = position[1] + 2 * i;
                light.fillColor = new Color(r, g, b);

                this.decorations.add(light);
            }
        }
    }

    private void generateBubbles() {
        int[][] bubblePositions = {
                // { x ,  y },
                {378, 142},
                {350, 225},
                {420, 235},
                {309, 315},
                {392, 283},
                {458, 302}
        };

        for (int[] position : bubblePositions) {
            float r = (float) (rand.nextFloat() / 2f + 0.5);
            float g = (float) (rand.nextFloat() / 2f + 0.5);
            float b = (float) (rand.nextFloat() / 2f + 0.5);

            Bubble bubble = new Bubble();
            bubble.x = position[0];
            bubble.y = position[1];
            bubble.scale = 0.25;
            bubble.fillColor = new Color(r, g, b);
            bubble.lineColor = Color.black;

            this.decorations.add(bubble);
        }
    }
}
