package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory;

import ovh.helldog136.Game.Model.Engine.Action;
import ovh.helldog136.Game.Model.Part.Tile;
import ovh.helldog136.Game.Wumpus.Engine.WumpusAction;
import ovh.helldog136.Game.Wumpus.Part.WumpusTile;
import ovh.helldog136.Game.util.Coordinate;
import ovh.helldog136.Game.util.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helldog136 on 29/07/15.
 */
public class Memory {
    private static final int PITDANGER = 4;
    private static final int WUMPUSDANGER = 4;
    private static final int GOLDDANGER = -2;
    private MemoryTile currentTile = null;
    private Map<MemoryTile> map = new Map<MemoryTile>();
    private MemoryTile wumpusDangerToAdd = null;

    public boolean iHasGold() {return hasGold;}
    private boolean hasGold = false;
    private void addEmptyTileToMap(int x, int y){map.set(x, y, new MemoryTile(x, y));}
    private void insertOrUpdateTile(int x, int y){
        if (map.get(x,y) == null){addEmptyTileToMap(x,y);}
    }

    public Memory(){
        //initial tile
        addEmptyTileToMap(0,0);
        currentTile = map.get(0,0);
    }

    public void completePlan(ArrayList<WumpusAction> possibleMoveActions) {
        for (WumpusAction a : possibleMoveActions){
            insertOrUpdateTile(currentTile.getX() + a.getDirection().getXModifier(), currentTile.getY() + a.getDirection().getYModifier());
        }
    }

    public void completePlan(List<ovh.helldog136.Game.Model.Part.Tile.Qualification> perceptions) {
        currentTile.setExplored(true);
        if (perceptions.size() == 0 || (perceptions.size() == 1 && perceptions.contains(WumpusTile.Perception.GLITTER))){//no perceptions = adjacent tiles safe
            for (MemoryTile m : getAdjacentTiles()){
                m.setDangerLevel(0);
                m.setFlag("safe",true);
            }
        }else {
            for (Tile.Qualification p : perceptions) {
                currentTile.setFlag(((WumpusTile.Perception) p).name(), true);
            }
            if (perceptions.contains(WumpusTile.Perception.BREEZE)) {
                for (MemoryTile m : getAdjacentTiles()) {
                    if (m.isExplored()) {
                        m.setDangerLevel(0);
                    } else {
                        if (!m.getInfoOrDefault("safe",false)) {
                            m.modDangerLevel(PITDANGER);
                        }
                    }
                }
            }
            if (perceptions.contains(WumpusTile.Perception.SMELLY)) {
                for (MemoryTile m : getAdjacentTiles()) {
                    if (m.isExplored()) {
                        m.setDangerLevel(0);
                    } else {
                        if (!m.getInfoOrDefault("safe",false)) {
                            m.modDangerLevel(WUMPUSDANGER);
                            wumpusDangerToAdd = m;
                        }
                    }
                }
            }
            if (perceptions.contains(WumpusTile.Perception.GLITTER)) {
                for (MemoryTile m : getAdjacentTiles()) {
                    if (m.isExplored()) {
                        m.setDangerLevel(0);
                    } else {
                        m.modDangerLevel(GOLDDANGER);
                    }
                }
            }
            if (perceptions.contains(WumpusTile.Perception.GOLD)) {
                hasGold = true;
            }
        }
    }

    public Action rememberAction(Action chosen) {
        if(chosen.getActionType().equals(WumpusAction.WumpusActionType.MOVE)){
            currentTile = map.get(chosen.getDestination().getX(), chosen.getDestination().getY());
            currentTile.setExplored(true);
        }
        if(wumpusDangerToAdd != null){
            wumpusDangerToAdd.setFlag("maybeWumpus",true);
            wumpusDangerToAdd = null;
        }
        System.out.println(map);
        return chosen;
    }

    public MemoryTile getTileRelative(Direction direction) {
        return map.get(direction.getXModifier() + currentTile.getX(), direction.getYModifier() + currentTile.getY());
    }

    public Iterable<? extends MemoryTile> getAdjacentTiles() {
        ArrayList<MemoryTile> res = new ArrayList<MemoryTile>(0);
        for(Direction d : Direction.values()){
            MemoryTile tile = getTileRelative(d);
            if(tile != null) {
                res.add(tile);
            }
        }
        System.out.println("----\n"+res+"\n----");
        return res;
    }

    public Coordinate getCurrentCoordinate() {
        return new Coordinate(currentTile.getX(), currentTile.getY());
    }
}
