package assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;


/**
 * Created by ajosepa on 08/12/2016.
 */
public class Play extends JComponent implements MouseListener, KeyListener{

    Shape movingPiece;   //the current piece being controlled by the player
    Frame ff;
    Timer timer;
    Grid tGrid;
    ArrayList<TBlock> ghostShapes = new ArrayList<>();   //the fallen shapes stored
    boolean atEnd;
    int rotateVal=1;
    static int tetrisScore=0;
    static int gameStateVal=0;
    static int timerSpeed=600;
    boolean doubleSpeed;


    //Game constructor
    public Play(Frame ff) {
        this.ff = ff;
        tGrid = new Grid();
        createShape();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    //randomly creates one of the seven tetris shapes
    private void createShape(){
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(new tSquare());
        shapes.add(new tLine());
        shapes.add(new tL());
        shapes.add(new tLi());
        shapes.add(new tZ());
        shapes.add(new tZi());
        shapes.add(new tUppyDowny());

        movingPiece = shapes.get(((int)(7*Math.random())));
    }


    //drawing the shapes onto the screen
    public void paintComponent(Graphics g){
        for (int i = 0; i < 200 ; i++) {
            tGrid.tBlock[i].draw(g);
        }
        super.paintComponent(g);
        movingPiece.draw(g);
        for (int i = 0; i < ghostShapes.size(); i++) {
            ghostShapes.get(i).draw(g);
        }
    }


    //timed events
    public void start(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameStateVal=0;
                ff.gameState.setText("");
                ff.resetText.setText("");
                ff.score.setText("           Score: "+tetrisScore);
                fall();
                ff.repaint();
            }
        };
        timer.scheduleAtFixedRate(task,0,timerSpeed);
    }


//Changes falling state. Includes double speed, collision and game over states.
    private void fall(){
        if(tetrisScore>100 && doubleSpeed==false){
            doubleSpeed = true;
            timerSpeed=150;
            ff.doubleSpeedText.setText("      [DOUBLE SPEED]");
            timer.cancel();
            start();
        }
        if(onCeiling()){
            timer.cancel();
            gameStateVal=2;
            ff.gameState.setText("         GAME OVER");
            ff.resetText.setText("    Press 'R' to restart");
        }

        if(onFloor()||collisionDetectedY()){
            for (int i = 0; i < 4; i++) {
                ghostShapes.add(movingPiece.TBlock[i]);
            }
            createShape();
            clearLines();
            ff.repaint();
        }else{
            movingPiece.move(0,1);
        }
    }


/*################################### Collision detection #############################################*/

    //returns true if a ghostpiece has reached the cieling
    private boolean onCeiling(){
        for (int i = 0; i < ghostShapes.size(); i++) {
            if(ghostShapes.get(i).y == 0){
                return true;
            }
        }
        return false;
    }

    //returns true if block is on floor, returns false if in air
    private boolean onFloor(){
        for (int i = 0; i <4 ; i++) {
            if(movingPiece.TBlock[i].y == 19){
                return true;
            }
        }
        return false;
    }
    //returns true if movingPiece sits ontop of a ghostpiece
    private boolean collisionDetectedY(){
        for (int i = 0; i < ghostShapes.size(); i++) {
            for (int k = 0; k < 4; k++) {
                if(ghostShapes.get(i).x == movingPiece.TBlock[k].x && ghostShapes.get(i).y-1 == movingPiece.TBlock[k].y){
                    return true;
                }
            }
        }
        return false;
    }
    //returns true if a ghostpiece is to the left/right of movingPiece. -1 to move left, +1 to move right.
    private boolean collisionDetectedX(int direction){
        for (int i = 0; i < ghostShapes.size(); i++) {
            for (int k = 0; k < 4; k++) {
                if(ghostShapes.get(i).x+direction == movingPiece.TBlock[k].x && ghostShapes.get(i).y == movingPiece.TBlock[k].y ){
                    return true;
                }
            }
        }
        return false;
    }
/*####################################################################################################*/


    private void rotate(){
        boolean valid = true;
        if(rotateVal==4){rotateVal=0;}

        //makes copy of movingPiece
        Shape tempShape;
        if(movingPiece.getClass() == tSquare.class){
            tempShape = new tSquare();
        } else if(movingPiece.getClass() == tLine.class) {
            tempShape = new tLine();
        } else if(movingPiece.getClass() == tL.class){
            tempShape = new tL();
        } else if(movingPiece.getClass() == tLi.class){
            tempShape = new tLi();
        } else if(movingPiece.getClass() == tZ.class){
            tempShape = new tZ();
        } else if(movingPiece.getClass() == tZi.class){
            tempShape = new tZi();
        }else{
            tempShape = new tUppyDowny();
        }

        for (int i = 0; i < 4; i++) {
            tempShape.TBlock[i].x = movingPiece.TBlock[i].x;
            tempShape.TBlock[i].y = movingPiece.TBlock[i].y;
        }
        //applies rotation to the copied shape
        for (int i = 0; i < 4; i++) {
            tempShape.TBlock[i].x = tempShape.sticky.x+movingPiece.rotation[rotateVal][i][0];
            tempShape.TBlock[i].y = tempShape.sticky.y+movingPiece.rotation[rotateVal][i][1];
        }
        //checks if the new shape collides with ghostshapes
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < ghostShapes.size(); j++) {
                if(tempShape.TBlock[i].x == ghostShapes.get(j).x && tempShape.TBlock[i].y == ghostShapes.get(j).y){
                    valid = false;
                }
            }
            if(tempShape.TBlock[i].x <= -1 || tempShape.TBlock[i].x >=10){
                valid = false;
            }
        }
        //performs rotation on movingPiece if the tested collion is valid
        if(valid){
            for (int i = 1; i < 4; i++) {
                movingPiece.TBlock[i].x = movingPiece.sticky.x+movingPiece.rotation[rotateVal][i][0];
                movingPiece.TBlock[i].y = movingPiece.sticky.y+movingPiece.rotation[rotateVal][i][1];
            }
            rotateVal++;
        }

    }




    /*########################################### CLEARING ROWS ############################################*/
    //checks if a row has ten blocks in it makes a call to the delete row method with the desired row as the parameter
    private void clearLines(){
        for (int row = 19; row > 0; row--) {
            int blocksInLine=0;
            for (int j = 0; j < ghostShapes.size(); j++) {
                if(ghostShapes.get(j).y==row){
                    blocksInLine++;
                    if(blocksInLine==10){
                        deleteSingleRow(row);
                        row++;
                    }
                }
            }
        }
    }

    //deletes all blocks on a particular row
    private void deleteSingleRow(int row){
        for (int i = 0; i < ghostShapes.size(); i++) {
            if(ghostShapes.get(i).y==row){
                ghostShapes.remove(i);
                i--;
            }
        }
        tetrisScore+=10;
        shiftDown(row);
    }

    //shifts all blocks down up until the highest block on the screen
    private void shiftDown(int row){
        int highestY=19;
        for (int i = 0; i < ghostShapes.size(); i++) {
            if(ghostShapes.get(i).y<highestY){
                highestY = ghostShapes.get(i).y;
            }
        }
        for (int k = 1; k <= highestY; k++) {
            for (int i = 0; i < ghostShapes.size(); i++) {
                if(ghostShapes.get(i).y==row-k){
                    ghostShapes.get(i).move(0,1);
                }
            }
        }
    }

    /*################################################################################################*/


    /*################################## Mouse and Keyboard listeners ##################################*/
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    //keyboard listener used
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_P && !(gameStateVal==2)){
            gameStateVal = (gameStateVal==0)? 1:0;
            if(gameStateVal==1){
                timer.cancel();
                ff.gameState.setText("           [PAUSED]");
            }else{
                start();
                ff.gameState.setText("");
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_SPACE && gameStateVal==0){
            while(!(collisionDetectedY()||onFloor())){
                movingPiece.move(0,1);
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_F && gameStateVal==0){
            if(!(collisionDetectedY()||onFloor())){
                movingPiece.move(0,1);
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_R && gameStateVal==2){
            ghostShapes.clear();
            tetrisScore=0;
            doubleSpeed = false;
            ff.doubleSpeedText.setText("");
            timerSpeed=300;
            start();
        }

        atEnd=false;
        if(e.getKeyCode()==KeyEvent.VK_LEFT && gameStateVal==0){
            for (int i = 0; i < 4; i++) { //for each block in movingpiece
                if(movingPiece.TBlock[i].x == 0){  //check if its reached the end of the window
                    atEnd=true;
                }
            }
            if(atEnd==false && !collisionDetectedX(1)) {  //if not at end of window and no collision
                movingPiece.move(-1, 0);  //move piece left
            }
            ff.repaint(); //only one repaint at end?
        }

        if(e.getKeyCode()==KeyEvent.VK_RIGHT && gameStateVal==0){
            for (int i = 0; i < 4; i++) {
                if(movingPiece.TBlock[i].x == 9){
                    atEnd=true;
                }
            }
            if(atEnd==false && !collisionDetectedX(-1)) { //collision parameter decides left or right comparison
                movingPiece.move(1, 0);
            }
            ff.repaint();
        }

        if((e.getKeyCode()==KeyEvent.VK_UP ||e.getKeyCode()==KeyEvent.VK_DOWN) && gameStateVal==0){
            rotate();
            ff.repaint();
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    //mouse listener used
    public void mousePressed(MouseEvent e) {
        atEnd=false;
        if (e.getButton()==MouseEvent.BUTTON1 && gameStateVal==0){  //checks if paused
            for (int i = 0; i < 4; i++) { //for each block in movingpiece
                if(movingPiece.TBlock[i].x == 0){  //check if its reached the end of the window
                    atEnd=true;
                }
            }
            if(atEnd==false && !collisionDetectedX(1)) {  //if not at end of window and no collision
                movingPiece.move(-1, 0);  //move piece left
            }
            ff.repaint(); //only one repaint at end?

        }else if (e.getButton()==MouseEvent.BUTTON3 && gameStateVal==0){
            for (int i = 0; i < 4; i++) {
                if(movingPiece.TBlock[i].x == 9){
                    atEnd=true;
                }
            }
            if(atEnd==false && !collisionDetectedX(-1)) { //collision parameter decides left or right comparison
                movingPiece.move(1, 0);
            }
            ff.repaint();

        }else if (e.getButton()==MouseEvent.BUTTON2 && gameStateVal==0){
            rotate();
            ff.repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
/*#####################################################################################################*/


}


