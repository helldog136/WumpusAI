package ovh.helldog136.Game.Wumpus.Part;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Part.Board;
import ovh.helldog136.Game.Wumpus.Engine.WumpusAction;
import ovh.helldog136.Game.util.Coordinate;
import ovh.helldog136.Game.util.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public class WumpusBoard extends Board {
    public static final boolean LIGHT = false;

    public WumpusBoard() {
        this(5, 5);
    }
    public WumpusBoard(int dimX, int dimY) {
        super(dimX, dimY);
    }

    @Override
    protected void populateBoard() {
        for (int y = board[0].length-1; y >= 0; y--) {
            for (int x = 0; x < board.length; x++) {
                board[x][y] = new WumpusTile(x,y,board);
            }
        }
        Random random = new Random();
        //generateWumpus
        ((WumpusTile)board[1+random.nextInt(board.length - 1)][1+random.nextInt(board[0].length - 1)]).setWumpus();
        //generateGold
        int x,y;
        do {
            x = 1 + random.nextInt(board.length - 1);
            y = 1 + random.nextInt(board[0].length - 1);
        }while (((WumpusTile)board[x][y]).isWumpus());
        ((WumpusTile) board[x][y]).setGold();
        //generatePits
        int nbPits = Math.max(2, random.nextInt(caseNumber)/Math.min(board.length, board[0].length));
        System.out.println(nbPits + " pits");
        for (int i = 0; i < nbPits; i++) {
            do {
                x = 1 + random.nextInt(board.length - 1);
                y = 1 + random.nextInt(board[0].length - 1);
            }while (((WumpusTile)board[x][y]).isWumpus() || ((WumpusTile)board[x][y]).isGold());
            ((WumpusTile) board[x][y]).setPit();
        }
        //checkFeasability
        if(board[0][0].getPerceptions().size() > 1){System.out.println("Ooops this board was impossible or too easy!");populateBoard();}
    }

    public String getStringRepresentation(){
        return "Wumpus"+super.getStringRepresentation();
    }

    @Override
    public ArrayList<Direction> getAvailableDirections(Coordinate playerCoordinates) {
        int x = playerCoordinates.getX(), y = playerCoordinates.getY();
        ArrayList<Direction> res = new ArrayList<Direction>();
        try{
            if(board[x][y-1] != null){
                res.add(Direction.UP);
            }
        }catch(IndexOutOfBoundsException ignored){}
        try{
            if(board[x][y+1] != null){
                res.add(Direction.DOWN);
            }
        }catch(IndexOutOfBoundsException ignored){}
        try{
            if(board[x+1][y] != null){
                res.add(Direction.RIGHT);
            }
        }catch(IndexOutOfBoundsException ignored){}
        try{
            if(board[x-1][y] != null){
                res.add(Direction.LEFT);
            }
        }catch(IndexOutOfBoundsException ignored){}
        return res;
    }

    @Override
    public void informMove(Action playerAction) {
        if (playerAction.getActionType() == WumpusAction.WumpusActionType.MOVE) {
            board[playerAction.getOrigin().getX()][playerAction.getOrigin().getY()].visit();
            board[playerAction.getDestination().getX()][playerAction.getDestination().getY()].visit();
        }
    }
}
