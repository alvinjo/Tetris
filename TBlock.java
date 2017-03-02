package assignment2;

import java.awt.*;

/**
 * Created by ajosepa on 08/12/2016.
 */
public class TBlock {

    Color color;
    int x;
    int y;
    public static int bSize=25;

    //constructor for block
    protected TBlock(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    //move a block
    protected void move(int x, int y){
        this.x += x;
        this.y+=y;

    }

    //drawing a block
    public void draw(Graphics g){
        g.setColor(color);
        g.fill3DRect(x*bSize, y*bSize, bSize, bSize, true);
    }
}
