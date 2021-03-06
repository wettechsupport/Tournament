/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.lang.reflect.Array;

/**
 *
 * @author Davidi
 */
public class Match {
    private String[] players;
    private String winner;  //Do not initialize-- this is a pointer.
    private int winnerID;
    
    public Match()
    {
        this("", "");
    }
    
    public Match(String p1, String p2)
    {
        players = new String[] {"---", "---"};
        setPlayerName(0, p1);
        setPlayerName(1, p2);
        winnerID = -1;
    }
    
    public void setPlayerName(int id, String p)
    {
        players[id] = p;
    }
    
    public String getPlayerID(int id)
    {
        return players[id];
    }
    
    public void setWinner(int id)
    {
        winner = players[id];
        winnerID = id;
    }
    
    public String getWinnerName()
    {
        return new String(winner);
        //avoid passing by reference
    }
    
    public int getWinnerID()
    {
        return winnerID;
    }
            
    @Override
    public String toString()
    {
        return "(" + players[0] + " vs " + players[1] + ")";
    }
    
    public String toHTMLString()
    {
        //Makes red text for the loser and green text for the winner.
        //Use this on JLabel.
        if(winnerID == -1)
            return toString();
        
        String red = new String("<font color=\"#FF0000\">");
        String green = new String("<font color=\"#00AA00\">");
        
        String result = new String("<html>");
        if(winnerID == 0)
            result.concat("(" + green + players[0] + "</font>) vs (" + red + players[1] + "</font>)");
        if(winnerID == 1)
            result.concat("(" + red + players[0] + "</font>) vs (" + red + players[1] + "</font>)");
        
        result.concat("</html>");
        return result;
    }
    
}
