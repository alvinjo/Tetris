package assignment2;

import java.awt.*;
import static java.awt.Color.*;

/**
 * Created by ajosepa on 08/12/2016.
 */
public abstract class Shape {

    Color color;
    int rotation[][][];
    TBlock[] TBlock = new TBlock[4]; //Shape will contain 4 TBlocks
    TBlock sticky;
    TBlock block1;
    TBlock block2;
    TBlock block3;
    static Color[] C = {red, green, blue, yellow, magenta, pink, cyan};

//moving all blocks in shape
    protected void move(int x, int y){
        for(int i=0; i<4; i++){
            TBlock[i].move(x,y);
        }
    }

//drawing all the blocks
    public void draw(Graphics g){
        g.setColor(color);
        for (int i = 0; i <4 ; i++) {
            TBlock[i].draw(g);
        }
    }
}

/*############################### Shape creators #####################################################*/
class tSquare extends Shape {

    public tSquare() {

        rotation = new int[][][]{
                {{0,0},{0,1},{1,0},{1,1}},
                {{0,0},{0,1},{1,0},{1,1}},
                {{0,0},{0,1},{1,0},{1,1}},
                {{0,0},{0,1},{1,0},{1,1}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,0);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}


class tLine extends Shape{

    public tLine(){

        rotation = new int[][][]{
                {{0,0},{-1,0},{1,0},{2,0}},
                {{0,0},{0,-1},{0,1},{0,2}},
                {{0,0},{-1,0},{1,0},{2,0}},
                {{0,0},{0,-1},{0,1},{0,2}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,0);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}


class tZ extends Shape{

    public tZ(){

        rotation = new int[][][]{
                {{0,0},{1,0},{1,-1},{0,1}},
                {{0,0},{0,-1},{-1,-1},{1,0}},
                {{0,0},{1,0},{1,-1},{0,1}},
                {{0,0},{0,-1},{-1,-1},{1,0}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,1);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}


class tZi extends Shape{

    public tZi(){

        rotation = new int[][][]{
                {{0,0},{-1,0},{-1,-1},{0,1}},
                {{0,0},{0,-1},{1,-1},{-1,0}},
                {{0,0},{-1,0},{-1,-1},{0,1}},
                {{0,0},{0,-1},{1,-1},{-1,0}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,1);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}


class tL extends Shape{

    public tL(){

        rotation = new int[][][]{
                {{0,0},{0,1},{0,-1},{1,1}},
                {{0,0},{-1,0},{-1,1},{1,0}},
                {{0,0},{0,1},{0,-1},{-1,-1}},
                {{0,0},{-1,0},{1,0},{1,-1}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,1);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}


class tLi extends Shape{

    public tLi(){

        rotation = new int[][][]{
                {{0,0},{0,-1},{0,1},{-1,1}},
                {{0,0},{-1,0},{-1,-1},{1,0}},
                {{0,0},{0,-1},{1,-1},{0,1}},
                {{0,0},{-1,0},{1,0},{1,1}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,1);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }
}

class tUppyDowny extends Shape{

    public tUppyDowny(){

        rotation = new int[][][]{
                {{0, 0}, {-1, 0}, {0, -1}, {1, 0}},
                {{0, 0}, {0, 1}, {0, -1}, {1, 0}},
                {{0, 0}, {1, 0}, {-1, 0}, {0, 1}},
                {{0, 0}, {0, 1}, {0, -1}, {-1, 0}},
        };

        sticky = new TBlock(C[((int)(7*Math.random()))], 5,0);
        TBlock[0] = sticky;
        block1 = new TBlock(color, sticky.x + rotation[0][1][0], sticky.y + rotation[0][1][1]);
        TBlock[1] = block1;
        block2 = new TBlock(color, sticky.x+rotation[0][2][0], sticky.y+rotation[0][2][1]);
        TBlock[2] = block2;
        block3 = new TBlock(color, sticky.x+rotation[0][3][0], sticky.y+rotation[0][3][1]);
        TBlock[3] = block3;
    }

    /*###########################################################################################*/
}