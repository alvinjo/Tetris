package assignment2;

import javax.swing.*;
import java.awt.*;
import static assignment2.TBlock.bSize;

/**
 * Created by ajosepa on 08/12/2016.
 */
public class Frame extends JFrame {

    JLabel gameState = new JLabel("");
    JLabel score = new JLabel("");
    JLabel resetText = new JLabel("");
    JLabel doubleSpeedText = new JLabel("");

    public static void main(String[] args) {
        Frame ff = new Frame();
        Play pp = new Play(ff);

        ff.add(pp);
        ff.setVisible(true);
        pp.start();
    }


    public Frame(){
        //creating labels and setting colours for them
        JLabel lStudent = new JLabel("Alvin Joseph - 1504409");
        lStudent.setForeground(Color.white);

        JLabel pauseText = new JLabel("    Press 'P' to pause");
        pauseText.setForeground(Color.white);

        score.setForeground(Color.white);
        gameState.setForeground(Color.red);
        resetText.setForeground(Color.white);
        doubleSpeedText.setForeground(Color.yellow);

        //creating the jpanel and adding the jlabels to them
        JPanel jPan = new JPanel(new GridLayout(10,1));
        jPan.setBackground(Color.darkGray);
        jPan.add(lStudent);
        jPan.add(score);
        jPan.add(pauseText);
        jPan.add(gameState);
        jPan.add(resetText);
        jPan.add(doubleSpeedText);

        //setting frame properties
        add(jPan, BorderLayout.EAST);
        setTitle("Alvin Joseph - 1504409");
        setSize(10*bSize+137, 20*bSize+29);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
