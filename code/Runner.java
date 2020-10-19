import java.util.*;
public class Runner 
{
    public static void main(String [] args) throws InterruptedException
    {
        Board board = new Board();
        // args will contain two things, 1. How many players are playing 2. How many minutes the game will run for
        int numPlayers = 2; // 2 players
        int timeLength = 1; // 1 minute
        board.begin(numPlayers, timeLength);
        System.out.println(board.numTiles());
    }
}
