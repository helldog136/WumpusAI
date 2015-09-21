package ovh.helldog136.Game.Model.Part;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public abstract class Tile {
    protected int x,y;
    protected boolean visited = false;
    public void visit(){visited = true;}

    protected Tile[][] board;
    public Tile(int _x, int _y, Tile[][] _board){
        x = _x; y = _y; board = _board;
    }
    public abstract String getStringRepresentation();

    public abstract boolean isDeadly();

    public boolean isStartTile() {
        return x == 0 && y == 0;
    }

    public interface Qualification{}

    protected ArrayList<Qualification> qualifiers = new ArrayList<Qualification>();
    protected void addQualification(Qualification q){if(!qualifiers.contains(q)){qualifiers.add(q);}}
    protected void rmQualification(Qualification q){if(qualifiers.contains(q)){qualifiers.remove(q);}}
    public List<Qualification> getPerceptions(){return Collections.unmodifiableList(qualifiers);}
    protected void qualifyNeighbors(Qualification qualification) {
        try {
            board[x - 1][y].addQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x + 1][y].addQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x][y - 1].addQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x][y + 1].addQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
    }
    protected void unQualifyNeighbors(Qualification qualification) {
        try {
            board[x - 1][y].rmQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x + 1][y].rmQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x][y - 1].rmQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
        try {
            board[x][y + 1].rmQualification(qualification);
        }catch (IndexOutOfBoundsException ignored){}
    }

    public abstract void draw(Graphics2D g, int startX, int startY, int size);
}
