package ovh.helldog136.Game.Wumpus.Engine;

import ovh.helldog136.Game.Model.Engine.UI;

import java.awt.*;

/**
 * Created by helldog136 on 28/07/15.
 */
public class WumpusUI extends UI{

    public WumpusUI(Dimension d){
        super(d, "Wumpus");
    }

    @Override
    public void paint(Graphics2D g) {
        if(board != null){
            board.draw(g, getWidth(), getHeight());
        }else{
            return;
        }
        if(player != null){
            int boardSize = Math.min(getWidth(), getHeight());
            int tileSize = boardSize/Math.max(board.getWidth(), board.getHeight());
            int startX = (getWidth()-boardSize)/2;
            int startY = (getHeight()-boardSize)/2;
            g.setColor(Color.RED);
            g.fillOval(startX + player.getX()*tileSize, startY + player.getY()*tileSize, tileSize, tileSize);
        }
    }
}
