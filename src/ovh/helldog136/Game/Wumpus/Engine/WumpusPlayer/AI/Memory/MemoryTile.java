package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory;

import java.util.HashMap;

/**
 * Created by helldog136 on 28/08/15.
 */
public class MemoryTile {
    private int x,y;
    private HashMap<String, Boolean> flags = new HashMap<String, Boolean>();

    public void setExplored(boolean explored) {
        setFlag("explored",explored);
        if(explored){
            dangerLevel = 0;
        }
    }

    public int getDangerLevel() {return dangerLevel;}
    public void setDangerLevel(int mod) {this.dangerLevel = mod;}
    public void modDangerLevel(int mod) {this.dangerLevel += mod;}
    private int dangerLevel = 0;
    public MemoryTile(int x, int y){
        this.x = x;
        this.y = y;
    }
    public MemoryTile setFlag(String flag, Boolean value){
        flags.put(flag, value);
        return this;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String toString(){
        String res = String.format("%1d,%1d:",x,y)+String.format("%2d", getDangerLevel());
        return res;
    }

    public Boolean getInfo(String key) {
        return flags.get(key);
    }

    public boolean isExplored() {
        return flags.getOrDefault("explored", false);
    }

    public boolean getInfoOrDefault(String key, boolean b) {
        return getInfo(key) == null? b: getInfo(key);
    }
}
