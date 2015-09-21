package ovh.helldog136.Game.Model.Engine;

import ovh.helldog136.Game.Model.Part.Board;
import ovh.helldog136.Game.util.Coordinate;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

/**
 * Created by helldog136 on 28/07/15.
 */
public abstract class UI extends JPanel{
    protected JFrame frame;
    public UI(Dimension d, String appName){
        frame = new JFrame(appName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(d);
        frame.add(this);
        frame.revalidate();
        frame.setVisible(true);
        frame.repaint();
    }

    protected Coordinate player;
    public void informPlayerCoordinates(Coordinate playerCoordinates){
        player = playerCoordinates;
    }
    protected Board board;
    public void informBoard(Board b){
        board = b;
    }

    public void paint(Graphics g){paint((Graphics2D)g);}
    public abstract void paint(Graphics2D g);
}
