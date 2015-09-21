package ovh.helldog136.Game.Model.Engine;

import ovh.helldog136.Game.Model.Part.Tile;

import java.util.List;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public abstract class Player {
    public abstract Action getAction();
    private List<Action> availableActions;
    public void informActions(List<Action> actions) {
        availableActions = actions;
    }
    protected List<Action> getAvailableActions(){return availableActions;}
}
