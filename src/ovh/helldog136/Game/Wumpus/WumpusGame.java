package ovh.helldog136.Game.Wumpus;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Engine.Player;
import ovh.helldog136.Game.Model.Game;
import ovh.helldog136.Game.Model.Part.Board;
import ovh.helldog136.Game.Wumpus.Engine.WumpusAction;
import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.WumpusPlayer;
import ovh.helldog136.Game.Wumpus.Engine.WumpusUI;
import ovh.helldog136.Game.Wumpus.Part.WumpusBoard;
import ovh.helldog136.Game.Wumpus.Part.WumpusTile;
import ovh.helldog136.Game.util.Direction;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public class WumpusGame extends Game {
    private static final int AMMO_START = 3;
    private boolean hasGold = false;
    private int ammo = AMMO_START;
    private boolean hasAmmo(){return ammo > 0;}

    public WumpusGame(WumpusPlayer _player, WumpusBoard _board) {
        super(_player, _board, new WumpusUI(new Dimension(640,480)));
        actualizePlayerActions();
    }
    public WumpusGame(WumpusPlayer _player, WumpusBoard _board, WumpusUI _ui) {
        super(_player, _board, _ui);
        actualizePlayerActions();
    }

    protected void actualizePlayerActions() {
        possiblePlayerActions.clear();
        for (Direction d : board.getAvailableDirections(playerCoordinates)){
            possiblePlayerActions.add(new WumpusAction(playerCoordinates, WumpusAction.WumpusActionType.MOVE, d));
            if(hasAmmo()){
                possiblePlayerActions.add(new WumpusAction(playerCoordinates, WumpusAction.WumpusActionType.SHOOT, d));
            }
        }
    }

    protected void gameLoop(){
        ((WumpusPlayer)player).informPerceptions(board.getTileAt(playerCoordinates).getPerceptions());
        super.gameLoop();
    }

    @Override
    protected void react(Action action) {
        if(!possiblePlayerActions.contains(action)){
            System.out.println("Oops you can't do that!");
            return;
        }
        Action.ActionType type = action.getActionType();
        if (type == WumpusAction.WumpusActionType.MOVE){
            playerCoordinates = action.getDestination();
            if(!hasGold && ((WumpusTile)board.getTileAt(playerCoordinates)).isGold()){
                hasGold = true;
                ((WumpusTile)board.getTileAt(playerCoordinates)).pickGold();
            }
        }else if(type == WumpusAction.WumpusActionType.SHOOT) {
            ammo -= 1;
            if(((WumpusTile) board.getTileAt(playerCoordinates)).shoot(action.getDirection())){
                boolean wumpusDead = true;
                //TODO
            }
        }else{
            System.out.println("Wrong Action");
        }
    }

    @Override
    public boolean isGameOver(){
        return board.getTileAt(playerCoordinates).isDeadly();
    }

    @Override
    public boolean isGameWon() {
        return hasGold && board.getTileAt(playerCoordinates).isStartTile();
    }
}
