/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.util.ArrayList;
import java.util.Random;
import data.Match;
import gui.ContentJFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Davidi
 */
public class MatchList {
    private static MatchList instance;
    private ArrayList<String> players;
    private ArrayList<ArrayList<Match>> matches;
    private Match currentMatch;  //Reference: DO NOT INITILIZE
    private int matchColumn;  //horizontal side.  Used to track current match
    private int matchRow;  //vertical side.  Used to track current match.
    
    public MatchList()
    {
        players = new ArrayList<String>();
        matches = new ArrayList<ArrayList<Match>>();
        matchColumn = 0;
        matchRow = 0;
        
        //Call in order:
        //addPlayer (until we have all)
        //startMatch()
        //While(isGameOver() == false)
            //setWinnerOnCurrentMatch() [in loop]
        
        
    }
    
    public ArrayList<String> getPlayers()
    {
        return players;
    }
    
    public void setNextCurrentMatch()
    {
        //Move on to the next match.  
        int colSize = matches.get(matchRow).size();
        int rowSize = matches.size();
        
        matchColumn++;
        if(matchColumn >= colSize)
        {
            matchColumn = colSize;
            matchRow++;
        }
        
        currentMatch = matches.get(matchRow).get(matchColumn);
    }
    
    
    public void addPlayer(String p)
    {
        //Add a player to the list
        players.add(p);
    }
    
    public int getPlayerSize()
    {
        return players.size();
    }
    
    public void trimLastPlayer()
    {
        //Remove the last player in the list
        int id = players.size() - 1;
        players.remove(id);
    }
    
    public void trimRandomPlayer()
    {
        //Remove a random player in the list
        Random r = new Random();
        int id = r.nextInt(players.size() - 1);
        r = null;  //Delete Random from memory.
        players.remove(id);
    }
    
    public void setToTargetSize(int size)
    {
        //MAke a popup that returns 0 for random trimming, 1 for last player.
        ContentJFrame f = ContentJFrame.getInstance();
        Object[] options = {"Randomly", "Trim last ones"};
        int reply = JOptionPane.showOptionDialog(f, "You can only have 4, 8, or 16 players.  How would you like to adjust?",
                "Notice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
        
        
        //int reply = 0;  //Replace with OptionJFrame when we have GUI
        while(players.size() > size)
        {
            if(reply == 0)
                trimRandomPlayer();
            else
                trimLastPlayer();
        }
    }
    
    public boolean startMatch()
    {
        //Check to see if we can start match.  If we can, set the currentMatch to the first!
        int size = players.size();  //Instead of calling players.size() repeatedly...save a little processing
        if(size < 4)
        {
            JOptionPane.showMessageDialog(ContentJFrame.getInstance(),
                "You need at least four players to start.",
                "Notice",
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        //None of the correct player size.  
        if(size > 4 && size < 8)
            setToTargetSize(4);
        if(size > 8 && size < 16)
            setToTargetSize(8);
        if(size > 16)
            setToTargetSize(16);
        
        //Correct sizes!
        if(size == 4)
        {
            for(int i = 0; i < 2; i++)
                matches.add(new ArrayList<Match>());
            //Set the number of matches per row- first row has 2,
            //second row has 1, etc.
            
            
            //Fill up the first row
            matches.get(0).add(new Match(players.get(0), players.get(1)));
            matches.get(0).add(new Match(players.get(2), players.get(3)));
            
            //Create empty rows
            for(int i = 0; i < 1; i++)
                matches.get(1).add(new Match());  


            currentMatch = matches.get(0).get(0);
            return true;
        }
        
        if(size == 8)
        {
            for(int i = 0; i < 3; i++)
                matches.add(new ArrayList<Match>());

            //Set the number of matches per row- first row has 4,
            //second row has 2, etc.
            
            //Fill up the first row
            matches.get(0).add(new Match(players.get(0), players.get(1)));
            matches.get(0).add(new Match(players.get(2), players.get(3)));
            matches.get(0).add(new Match(players.get(4), players.get(5)));
            matches.get(0).add(new Match(players.get(6), players.get(7)));
            
            //Create empty rows
            for(int i = 0; i < 2; i++)
                matches.get(1).add(new Match());  
            for(int i = 0; i < 1; i++)
                matches.get(2).add(new Match());  
            
            currentMatch = matches.get(0).get(0);
            return true;
        }
        
        if(size == 16)
        {
            for(int i = 0; i < 4; i++)
                matches.add(new ArrayList<Match>());

            //Fill up the first row
            matches.get(0).add(new Match(players.get(0), players.get(1)));
            matches.get(0).add(new Match(players.get(2), players.get(3)));
            matches.get(0).add(new Match(players.get(4), players.get(5)));
            matches.get(0).add(new Match(players.get(6), players.get(7)));
            matches.get(0).add(new Match(players.get(8), players.get(9)));
            matches.get(0).add(new Match(players.get(10), players.get(11)));
            matches.get(0).add(new Match(players.get(12), players.get(13)));
            matches.get(0).add(new Match(players.get(14), players.get(15)));
            
            //Set the number of matches per row- first row has 4,
            //second row has 2, etc.
            for(int i = 0; i < 4; i++)
                matches.get(1).add(new Match());  
            for(int i = 0; i < 2; i++)
                matches.get(2).add(new Match());  
            for(int i = 0; i < 1; i++)
                matches.get(3).add(new Match());  
            
            currentMatch = matches.get(0).get(0);
            return true;
        }
        
        //Somehow we didn't get to any of the correct sizes-- it failed.
        return false;
    }
    
    public ArrayList<Match> getRow(int row)
    {
        return matches.get(row);
    }
    
    public ArrayList<ArrayList<Match>> getMatchData()
    {
        return matches;
    }
    
    public void setWinnerOnCurrentMatch(int id)
    {
        //Run the match.  This should be called when you press the winner button.
        currentMatch.setWinner(id);
        
        //Pass the current match winner to the below match
        int targetRow = matchRow + 1;
        int targetCol = (matchColumn + 1) / 2;
        int side = (matchColumn + 1) % 2;
        
        matches.get(targetRow).get(targetCol).setPlayerName(side, currentMatch.getWinnerName());
        
        
        //We also need to pass the string of the winner to the lower row...
        setNextCurrentMatch();
    }
    
    
    public boolean isGameOver()
    {
        if(currentMatch == null)  //current match hits a null pointer
            return true;
        else
            return false;
        
    }
    
    public static MatchList getInstance()
    {
        if(instance == null)
            instance = new MatchList();

        return instance;
    }
    
    public Match getCurrentMatch()
    {
        //To get the data for the buttons.
        return currentMatch;
    }
    
    @Override
    public String toString()
    {
        String s = new String("Match list: \n");
        for(int row = 0; row < matches.size(); row++)
        {
            s += "\nRow " + (row+1) + ": ";
            for(int col = 0; col < matches.get(row).size(); col++)
            {
                s += matches.get(row).get(col).toString();
            }
        }
        
        return s;
    }
    
}
