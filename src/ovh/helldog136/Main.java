package ovh.helldog136;

import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.AI.WumpusAI;
import ovh.helldog136.Game.Wumpus.Engine.WumpusUI;
import ovh.helldog136.Game.Wumpus.Part.WumpusBoard;
import ovh.helldog136.Game.Wumpus.WumpusGame;
import ovh.helldog136.Game.Wumpus.Engine.WumpusPlayer.RandomPlayer;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        WumpusBoard b = new WumpusBoard(20,20);
        System.out.println(b);

        WumpusGame game = new WumpusGame(new WumpusAI(), b);
        game.play();
    }
}
