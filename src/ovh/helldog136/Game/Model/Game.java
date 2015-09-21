package ovh.helldog136.Game.Model;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Engine.Player;
import ovh.helldog136.Game.Model.Engine.UI;
import ovh.helldog136.Game.Model.Part.Board;
import ovh.helldog136.Game.util.Coordinate;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public abstract class Game {
    private static final long TICK = 1;
    protected Player player;
    protected Board board;
    private UI ui;
    public Game(Player _player, Board _board, UI _ui){
        player = _player;
        board = _board;
        ui = _ui;
        playerCoordinates = new Coordinate(0,0);
    }

    protected void gameLoop(){
        ui.informPlayerCoordinates(playerCoordinates);
        player.informActions(Collections.unmodifiableList(possiblePlayerActions));
        Action playerAction = player.getAction();
        react(playerAction);
        actualizePlayerActions();
        board.informMove(playerAction);
        ui.informPlayerCoordinates(playerCoordinates);
        ui.informBoard(board);
        ui.repaint();
    }

    protected abstract void actualizePlayerActions();

    public void play(){
        int turn = 0;
        while (!isGameEnded()){
            System.out.println(turn);
            gameLoop();
            turn++;
            try {
                Thread.sleep(TICK);
            } catch (InterruptedException ignored){}
        }
        if(isGameWon()){
            System.out.println(turn + "BRAVO!!!!");
        }else{
            System.out.println(turn + "Try Again");
        }
    }

    protected abstract void react(Action action);

    protected Coordinate playerCoordinates;
    protected ArrayList<Action> possiblePlayerActions = new ArrayList<Action>();
    public abstract boolean isGameOver();
    public abstract boolean isGameWon();
    public boolean isGameEnded(){return isGameOver() || isGameWon();}
}
