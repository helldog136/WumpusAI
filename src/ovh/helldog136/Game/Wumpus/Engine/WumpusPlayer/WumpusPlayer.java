package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer;

import ovh.helldog136.Game.Model.Engine.Player;
import ovh.helldog136.Game.Model.Part.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by helldog136 on 28/07/15.
 */
public abstract class WumpusPlayer extends Player {
    private List<Tile.Qualification> perceptions = new ArrayList<Tile.Qualification>();

    protected List<Tile.Qualification> getPerceptions() {
        return Collections.unmodifiableList(perceptions);
    }
    public void informPerceptions(List<Tile.Qualification> _perceptions) {
        perceptions = _perceptions;
    }
}
