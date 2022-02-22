package lab10;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    List<XmasShape> branches = new ArrayList<>();

    Tree() {
        Trunk trunk = new Trunk();
        trunk.x = 375;
        trunk.y = 350;
        trunk.scaleX = 0.5;
        trunk.scaleY = 0.25;
        this.branches.add(trunk);

        for (int i = 1; i <= 3; i++) {
            for (double j = -30; j <= 30; j += 0.5) {
                Branch branch = new Branch();
                branch.x = 400;
                branch.y = i * 100 - (i - 1) * 25;
                branch.scale = i;
                branch.rotate = j;

                this.branches.add(branch);
            }
        }
    }
}
