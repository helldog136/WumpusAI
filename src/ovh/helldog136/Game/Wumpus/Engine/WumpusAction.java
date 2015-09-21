package ovh.helldog136.Game.Wumpus.Engine;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.util.Coordinate;
import ovh.helldog136.Game.util.Direction;

/**
 * Created by helldog136 on 24/07/15.
 */
public class WumpusAction extends Action {
    public enum WumpusActionType implements ActionType {MOVE, SHOOT}
    public WumpusAction(Coordinate _from, WumpusActionType _type, Direction _direction) {
        super(_from, _type, _direction);
        to = _from.move(_direction);
    }
}
