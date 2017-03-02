package assignment2;

import java.awt.*;

/**
 * Created by ajosepa on 08/12/2016.
 */
public class Grid {
    TBlock[] tBlock = new TBlock[200];

    //draw the grid
    public Grid() {
        int backPanel = 0;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                tBlock[backPanel] = new TBlock(Color.getHSBColor(0.06f, 0.85f, 0.5f),x,y);
                backPanel++;
            }
        }
    }
}
