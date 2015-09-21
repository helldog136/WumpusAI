package ovh.helldog136.Game.util;

/**
 * Created by Duncan -Helldog136- De Weireld on 26/05/15.
 */
public class Coordinate {
    private int x,y;
    public int getX(){return x;}
    public int getY(){return y;}
    public Coordinate(int _x, int _y){
        x = _x; y = _y;
    }

    public String toString(){
        return String.format("[%2d", getX())+","+String.format("%2d]", getY());
    }

    public Coordinate move(Direction direction) {
        int nX = getX(), nY = getY();
        if (direction.getAxis() == 'y'){
            nY += direction.getModifier();
        }else {
            nX += direction.getModifier();
        }
        return new Coordinate(nX, nY);
    }

    public boolean equals(Object o){
        return this == o ||
                o instanceof Coordinate &&
                this.getX() == ((Coordinate)o).getX() &&
                this.getY() == ((Coordinate)o).getY();
    }
}
