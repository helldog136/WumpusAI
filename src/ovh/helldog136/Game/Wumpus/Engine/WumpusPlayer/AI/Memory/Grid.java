package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory;

import com.sun.applet2.AppletParameters;
import ovh.helldog136.Game.Model.Game;
import ovh.helldog136.Game.util.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by helldog136 on 30/07/15.
 */
public class Grid {
    public Grid getUp() {return neighbours.get(Direction.UP);}
    public Grid getRight() {return neighbours.get(Direction.RIGHT);}
    public Grid getDown() {return neighbours.get(Direction.DOWN);}
    public Grid getLeft() {return neighbours.get(Direction.LEFT);}
    public Grid getNeighbour(Direction d) {return neighbours.get(d);}

    private HashMap<Direction, Grid> neighbours = new HashMap<Direction, Grid>();

    private boolean visited = false; public boolean isVisited() {return visited;}

    public Grid(){}
    private Grid(Grid neigh, Direction from){
        this();
        neighbours.put(from, neigh);

    }

    public boolean isExplored(Direction dir){
        return neighbours.containsKey(dir) && neighbours.get(dir) != null && neighbours.get(dir).isVisited();
    }
    public void complete(ArrayList<Direction> directions){
        for(Direction dir : directions){
            complete(dir);
        }
    }
    public void complete(Direction dir){
        visited = true;
        if(!neighbours.containsKey(dir) || neighbours.get(dir) == null){
            neighbours.put(dir, new Grid(this, Direction.getOpposite(dir)));
        }
    }
    public Grid move(Direction dir){
        if(neighbours.containsKey(dir) && neighbours.get(dir) != null){ //si la direction existe
            return neighbours.get(dir);
        }else{
            System.out.println("Err wrong direction");
            System.exit(-1);
        }
        return null;
    }
}
