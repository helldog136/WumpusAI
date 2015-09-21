package ovh.helldog136.Game.Wumpus.Part;

import ovh.helldog136.Game.Model.Part.Tile;
import ovh.helldog136.Game.util.Direction;

import java.awt.*;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public class WumpusTile extends Tile {

    public WumpusTile(int _x, int _y, Tile[][] _board) {
        super(_x, _y, _board);
    }

    public boolean pickGold() {
        if(isGold()){
            gold = false;
            unQualifyNeighbors(Perception.GLITTER);
            return true;
        }
        return false;
    }

    public enum Perception implements Tile.Qualification {SMELLY,BREEZE, GOLD, GLITTER}
    private boolean wumpus = false;public boolean isWumpus(){return wumpus;}
    public void setWumpus(){
        wumpus = true;
        qualifyNeighbors(Perception.SMELLY);
    }
    private boolean gold = false;public boolean isGold(){return gold;}
    public void setGold(){
        gold = true;
        qualifyNeighbors(Perception.GLITTER);
        qualifiers.add(Perception.GOLD);
    }
    private boolean pit = false;public boolean isPit(){return pit;}
    public void setPit(){
        if(isWumpus() || isGold())return;
        pit = true;
        qualifyNeighbors(Perception.BREEZE);
    }

    public boolean shoot(Direction dir){
        if(isWumpus()){
            wumpus = false;
            return true;
        }else{ //shoot the arrow further
            try {
                if (dir == Direction.UP) {
                    return ((WumpusTile) board[x][y-1]).shoot(dir);
                } else if (dir == Direction.DOWN) {
                    return ((WumpusTile) board[x][y+1]).shoot(dir);
                } else if (dir == Direction.LEFT) {
                    return ((WumpusTile) board[x-1][y]).shoot(dir);
                }else {
                    return ((WumpusTile) board[x+1][y]).shoot(dir);
                }
            }catch (IndexOutOfBoundsException e){
                return false;
            }
        }
    }
    @Override
    public String getStringRepresentation() {
        String res = "[";
        if(isWumpus()){
            res += "w";
        }else {
            if (isPit()) {
                res += "p";
            } else {
                if (isGold()) {
                    res += "g";
                } else {
                    res += " ";
                }
            }
        }
        res += getPerceptions().size()+"]";
        return res;
    }

    @Override
    public boolean isDeadly() {
        return isPit() || isWumpus();
    }

    @Override
    public void draw(Graphics2D g, int startX, int startY, int size) {
        Color original = g.getColor();
        if(WumpusBoard.LIGHT || visited) {
            if (isWumpus()) g.setColor(Color.RED);
            if (isGold()) g.setColor(Color.ORANGE);
            if (isPit()) g.setColor(Color.BLACK);
            g.fillRect(startX + x * size, startY + y * size, size, size);
            g.setColor(Color.BLACK);
            g.drawRect(startX + x * size, startY + y * size, size, size);
            if (getPerceptions().contains(Perception.BREEZE)) {
                g.setColor(Color.CYAN);
                g.fillRect(startX + (x * size), startY + (y * size) + 2 * size / 10, size, size / 15);
            }
            if (getPerceptions().contains(Perception.SMELLY)) {
                g.setColor(Color.GREEN);
                g.fillRect(startX + (x * size), startY + (y * size) + 3 * size / 10, size, size / 10);
            }
            if (getPerceptions().contains(Perception.GLITTER)) {
                g.setColor(Color.ORANGE.brighter());
                g.fillRect(startX + (x * size), startY + (y * size) + 4 * size / 10, size, size / 10);
            }
            g.setColor(Color.BLACK);
            g.drawString(x + "," + y, startX + (x * size), startY + y * size + 2 * size / 10);
            if(WumpusBoard.LIGHT && !visited){
                g.setColor(Color.BLACK);
                g.fillRect(startX + (x * size), startY + (y * size) + size / 2, size, size / 2);
            }
        }else{
            g.setColor(Color.BLACK);
            g.fillRect(startX + x * size, startY + y * size, size, size);
            g.setColor(Color.WHITE);
            g.drawString(x + "," + y, startX + x * size, startY + y * size + 2 * size / 10);
        }
        g.setColor(original);
    }
}
