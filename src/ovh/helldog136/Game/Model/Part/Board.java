package ovh.helldog136.Game.Model.Part;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.util.Coordinate;
import ovh.helldog136.Game.util.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public abstract class Board {
    protected Tile[][] board;
    protected int caseNumber;
    protected int dimX,dimY;
    public int getWidth(){return dimX;}
    public int getHeight(){return dimY;}
    public Board(int _dimX, int _dimY) {
        if(_dimX<3 || _dimY<3){throw new TooSmallDimensionException(dimX+"x"+dimY+"is an unacceptable dimension for a Board!");}
        dimX = _dimX; dimY = _dimY;
        board = new Tile[dimX][dimY];
        caseNumber = dimX*dimY;
        this.populateBoard();
    }
    protected abstract void populateBoard();

    public Tile getTileAt(Coordinate c){return getTileAt(c.getX(), c.getY());}
    public Tile getTileAt(int x, int y){return board[x][y];}

    public String getStringRepresentation(){
        String res = "Board\n";
        res += "    ";
        for (int x = 0; x < board.length; x++) {res += "| "+x+"|";}
        res += "\n";
        for (int y = 0; y < board[0].length; y++) {
            res += "| "+y+"|";
            for (int x = 0; x < board.length; x++) {
                res += board[x][y].getStringRepresentation();
            }
            res += "\n";
        }

        return res;
    }
    public String toString(){return getStringRepresentation();}

    public abstract ArrayList<Direction> getAvailableDirections(Coordinate playerCoordinates);

    public abstract void informMove(Action playerAction);

    public class TooSmallDimensionException extends RuntimeException {
        public TooSmallDimensionException(String s) {
            super(s);
        }
    }

    public void draw(Graphics2D g, int fenWidth, int fenHeight){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, fenWidth, fenHeight);

        int boardSize = Math.min(fenWidth, fenHeight);
        int tileSize = boardSize/Math.max(board.length, board[0].length);
        int startX = (fenWidth-boardSize)/2;
        int startY = (fenHeight-boardSize)/2;

        g.setColor(Color.pink);
        for (int y = board[0].length-1; y >= 0; y--) {
            for (int x = 0; x < board.length; x++) {
                board[x][board[0].length-1-y].draw(g, startX, startY, tileSize);
            }
        }
    }
}
