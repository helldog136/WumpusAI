package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Engine.Player;

import java.util.List;
import java.util.Random;

/**
 * Created by helldog136 on 24/07/15.
 */
public class RandomPlayer extends WumpusPlayer{
    private Random rdn;
    public RandomPlayer(){
        rdn = new Random();
    }
    @Override
    public Action getAction() {
        System.out.println(getAvailableActions());
        List<Action> act = getAvailableActions();
        Action chosen = act.get(rdn.nextInt(act.size()));
        System.out.println(chosen);
        return chosen;
    }
}
