package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Part.Tile;
import ovh.helldog136.Game.Wumpus.Engine.WumpusAction;
import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory.Memory;
import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory.MemoryTile;
import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.WumpusPlayer;
import ovh.helldog136.Game.Wumpus.Part.WumpusTile;

import java.util.*;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public class WumpusAI extends WumpusPlayer{

    private Memory memory;

    public WumpusAI(){
        super();
        memory = new Memory();
    }

    @Override
    public void informActions(List<Action> actions) {
        super.informActions(actions);
        memory.completePlan(getPossibleMoveActions());
    }

    @Override
    public Action getAction() {
        memory.completePlan(getPerceptions());
        System.out.println("Current coords: " + memory.getCurrentCoordinate());
        Action chosen = computeNextAction(getAvailableActions());
        return memory.rememberAction(chosen);
    }
    private void mod(HashMap<Action, Integer> m, Action a, int v){
        m.put(a, m.getOrDefault(a,0) + v);
    }

    private Action computeNextAction(List<Action> availableActions) {
        HashMap<Action, Integer> values = new HashMap<Action, Integer>();
        for (Action a : availableActions){
            MemoryTile target = memory.getTileRelative(a.getDirection());
            if(!memory.iHasGold()) {// search mode
                // compute fitness of an action
                if (a.getActionType().equals(WumpusAction.WumpusActionType.MOVE)) {
                    if(target.isExplored()){
                        values.put(a, 0);
                    }else{
                        values.put(a, 2);
                        mod(values, a, -target.getDangerLevel());
                    }
                }else {// shooting action
                    MemoryTile memTarget = memory.getTileRelative(a.getDirection());
                    if(memTarget != null && memTarget.getInfoOrDefault("maybeWumpus", false) && getPerceptions().contains(WumpusTile.Perception.SMELLY) && !target.isExplored() ){
                        System.out.println("Shooting " + a.getDirection());
                        values.put(a, 10);
                    }
                }
            }else{// return mode
                // compute fitness of an action
                if (a.getActionType().equals(WumpusAction.WumpusActionType.MOVE)) {
                    MemoryTile memTarget = memory.getTileRelative(a.getDirection());
                    if(target.isExplored() || (memTarget != null && memTarget.getInfoOrDefault("safe",false))) {
                        values.put(a, -( (target.getX() - memory.getCurrentCoordinate().getX()) + (target.getY() - memory.getCurrentCoordinate().getY())));
                    }
                }
            }
        }
        // retrieve the best computed Action
        ArrayList<Action> res = new ArrayList<Action>();
        int val = -Integer.MIN_VALUE;
        for (Action a : values.keySet()){
            if(values.get(a) > val){
                res.clear();
                res.add(a);
                val = values.get(a);
            }else if(values.get(a) == val){
                res.add(a);
            }
        }
        return res.get(new Random().nextInt(res.size()));
    }

    private ArrayList<WumpusAction> getPossibleMoveActions(){
        ArrayList<WumpusAction> res = new ArrayList<WumpusAction>();
        for (Action a : getAvailableActions()){
            if (((WumpusAction) a).getActionType() == WumpusAction.WumpusActionType.MOVE){
                res.add((WumpusAction)a);
            }
        }
        return res;
    }
}
