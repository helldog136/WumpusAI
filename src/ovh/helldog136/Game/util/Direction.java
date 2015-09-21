package ovh.helldog136.Game.util;

import java.util.ArrayList;

/**
 * Created by helldog136 on 22/07/15.
 */
public enum Direction {
    UP(-1,'y'),
    DOWN(1,'y'),
    LEFT(-1,'x'),
    RIGHT(1,'x');

    private int modifier = 0;
    private char axis = 'x';
    Direction(int val, char _axis){
        modifier = val;
        axis = _axis;
    }

    public int getModifier() {
        return modifier;
    }

    public char getAxis() {
        return axis;
    }

    public static Direction getOpposite(Direction dir){
        if(dir == Direction.UP){
            return DOWN;
        }else if(dir == Direction.RIGHT){
            return LEFT;
        }else if(dir == Direction.DOWN){
            return UP;
        }else if(dir == Direction.LEFT){
            return RIGHT;
        }else{
            return null;
        }
    }
    public static ArrayList<Direction> getOtherDirections(Direction dir){
        ArrayList<Direction> others = new ArrayList<Direction>(3);
        if(dir == Direction.UP){
            others.add(RIGHT);
            others.add(DOWN);
            others.add(LEFT);
        }else if(dir == Direction.RIGHT){
            others.add(UP);
            others.add(DOWN);
            others.add(LEFT);
        }else if(dir == Direction.DOWN){
            others.add(UP);
            others.add(RIGHT);
            others.add(LEFT);
        }else if(dir == Direction.LEFT){
            others.add(UP);
            others.add(RIGHT);
            others.add(DOWN);
        }
        return others;
    }

    public int getXModifier() {
        return (axis == 'x' ? modifier : 0);
    }
    public int getYModifier() {
        return (axis == 'y' ? modifier : 0);
    }
}
