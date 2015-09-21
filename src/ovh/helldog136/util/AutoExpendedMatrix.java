package ovh.helldog136.util;

import ovh.helldog136.Main;

import java.util.ArrayList;

/**
 * Created by helldog136 on 28/08/15.
 */
public class AutoExpendedMatrix<T> {
    public static void main(String[] args){
        AutoExpendedMatrix<String> mat = new AutoExpendedMatrix<String>();
        System.out.println(mat);
        mat.set(5, 7, "lol");
        System.out.println(mat);
    }

    // default value is null
    // values is a list of columns [x][y]
    protected ArrayList<ArrayList<T>> values = new ArrayList<ArrayList<T>>();
    protected int sizeX = 1;
    protected int sizeY = 1;
    public int getSizeX(){return sizeX;}
    public int getSizeY(){return sizeY;}

    public AutoExpendedMatrix(){
    }

    public void set(int x, int y, T val){
        //modify max sizes if needed
        sizeX = Math.max(sizeX, x+1);
        sizeY = Math.max(sizeY, y+1);
        //expand if needed
        while (values.size() < sizeX){
            values.add(new ArrayList<T>());
        }
        while (values.get(x).size() <= y){
            values.get(x).add(null);
        }
        values.get(x).set(y, val);
    }

    public T get(int x, int y){
        return getColumn(x).get(y);
    }
    public ArrayList<T> getColumn(int x){
        while (values.get(x).size() < sizeY){
            values.get(x).add(null);
        }
        return values.get(x);
    }
    public ArrayList<T> getRow(int y){
        ArrayList<T> ret = new ArrayList<T>(sizeX);
        while (ret.size()<sizeX){
            try{
                ret.add(values.get(ret.size()).get(y));
            }catch (ArrayIndexOutOfBoundsException ignored){
                ret.add(null);
            }
        }
        return ret;
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                try {
                    res += "["+values.get(j).get(i)+"]";
                }catch (IndexOutOfBoundsException ignored){
                    res += "[nul   ]";
                }
            }
            res += "\n";
        }
        return res;
    }
}
