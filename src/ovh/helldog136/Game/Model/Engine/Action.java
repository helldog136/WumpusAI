package ovh.helldog136.Game.Model.Engine;

import ovh.helldog136.Game.util.Coordinate;
import ovh.helldog136.Game.util.Direction;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public abstract class Action {
    public interface ActionType {}
    private Coordinate from;
    protected Coordinate to;
    private ActionType actionType;
    private Direction direction;
    public Action(Coordinate _from, ActionType _Action_type, Direction _direction){
        from = _from; actionType = _Action_type; direction = _direction;
    }
    public Coordinate getOrigin(){return from;}
    public Coordinate getDestination(){return to;}
    public ActionType getActionType(){return actionType;}
    public Direction getDirection(){return direction;}

    @Override
    public boolean equals(Object o){
        return this == o ||
               o instanceof Action &&
                this.getOrigin().equals(((Action)o).getOrigin()) &&
                this.getDestination().equals(((Action)o).getDestination()) &&
                this.getActionType() == ((Action) o).getActionType() &&
                this.getDirection() == ((Action)o).getDirection();
    }

    public String toString(){return "Action : "+ actionType.toString()+" "+direction+" "+from+" -> "+to;}
}
