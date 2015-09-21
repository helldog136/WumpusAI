package ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.Memory;

import ovh.helldog136.Game.util.Direction;
import ovh.helldog136.util.AutoExpendedMatrix;

import java.util.ArrayList;

/**
 * Created by helldog136 on 29/08/15.
 */
public class Map<T> extends AutoExpendedMatrix<T> {
    public static void main(String[] args){
        Map<String> m = new Map<String>();
        m.set(4,2,"4,2");
        System.out.println(m);
        m.set(-2, -5, "-2,-5");
        System.out.println(m);



        for (int x = -5; x <= 5; x++) {
            for (int y = -5; y <= 5; y++) {
                m.set(x,y, String.format("%2d,%2d", x,y));
            }
        }
        System.out.println(m);
    }

    private int offsetX = 0;
    private int offsetY = 0;

    private int getMinXIndex(){return -offsetX;}
    private int getMinYIndex(){return -offsetY;}

    public void set(int x, int y, T val){
        //System.out.println("adding " + val + " at " + x + "," + y);
        //System.out.println("offset: " + offsetX + "," + offsetY);
        //System.out.println("size: " + sizeX + "," + sizeY);
        //modify max sizes if needed
        while (x < getMinXIndex()){
            //System.out.println("increasing offsetX");
            values.add(0,new ArrayList<T>());
            offsetX += 1;
            sizeX += 1;
        }
        while (y < getMinYIndex()){
            //System.out.println("increasing offsetY");
            for (ArrayList<T> l : values){
                l.add(0,null);
            }
            offsetY += 1;
            sizeY += 1;
        }
        sizeX = Math.max(sizeX, x+1+offsetX);
        sizeY = Math.max(sizeY, y+1+offsetY);
        //expand if needed
        while (values.size() < sizeX){
            values.add(new ArrayList<T>());
        }
        while (values.get(x+offsetX).size() <= y+offsetY){
            values.get(x+offsetX).add(null);
        }
        //System.out.println(values);
        values.get(x+offsetX).set(y+offsetY, val);
    }

    public T get(int x, int y){
        try {
            return values.get(x + offsetX).get(y + offsetY);
        }catch (IndexOutOfBoundsException ignored){
            return null;
        }
    }
}
