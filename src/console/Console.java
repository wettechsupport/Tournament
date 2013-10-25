/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import data.*;
import java.util.*;

/**
 *
 * @author Davidi
 */
public class Console {
    private MatchList data;
    private Scanner scan;
    
    public Console()
    {
        data = MatchList.getInstance();
        scan = new Scanner(System.in);
        
        System.out.println("***Tournament console***");
        System.out.println("DO NOT USE BOTH CONSOLE AND GUI.  This will cause problems.  \n \n");
        
        
        
        insertNames();
        playTournament();
        
        System.out.println("The tournament is over.  The winner is: ");
        System.out.println(data.getTournamentWinner());
        
        System.out.println("Press enter (or type anything) to quit.");
        scan.nextLine();
        scan.close();
    }
    
    public void insertNames()
    {
        System.out.println("Please insert the names for the tournament.  Type \"start\" when done.");
        String txt = "";
        while(!txt.toLowerCase().equals("start"))
        {
            txt = scan.nextLine();
            if(txt.toLowerCase().equals("start"))
            {
                int size = data.getPlayerSize();
                if(size < 4)
                {
                    System.out.println("You need at least four players!");
                    txt = "";
                }
                
                if(size > 4 && size < 8)
                {
                    trimPlayers(4);
                }
                if(size > 8 && size < 16)
                {
                    trimPlayers(8);
                }
                if(size > 16)
                {
                    trimPlayers(16);
                }
            }
            else
            {
                data.addPlayer(txt);
            }
        }
        
        
        data.startMatch();
        return;
    }
    
    public void playTournament()
    {
        Match cmatch = data.getCurrentMatch();
        while(data.isGameOver() == false)
        {
            System.out.println("Overall tournament: \n" + data.toString());
            
            System.out.println("\n\nCurrent Match: " + cmatch.toString());
            
            System.out.println("Who wins?  (type 0 for " + cmatch.getPlayerID(0) + " and 1 for " + cmatch.getPlayerID(1) + ")");
            
            int choice = -1;
            while(choice < 0 || choice > 1)
            {
                try
                {
                    choice = Integer.parseInt(scan.nextLine());
                }
                catch(Exception e)
                {
                    System.out.println("Numbers only!");
                    choice = -1;
                }
            }
            
            data.setWinnerOnCurrentMatch(choice);
            cmatch = data.getCurrentMatch();
        }
    }
    
    private void trimPlayers(int siz)
    {
        boolean failure = true;
        int killCount = data.getPlayerSize() - siz;
        String s = "";
        System.out.println("We need to change to " + siz + " players.");
        System.out.println("Type \"rand\" to clip random ones.");
        System.out.println("Type \"end\" to trim them from the end of the list.");
        
        
        while(failure == true)
        {
            s = scan.nextLine();
            if(s.equals("end"))
            {
                System.out.println("Trimming from end.");
                failure = false;
                for(int x = 0; x < killCount; x++)
                    data.trimLastPlayer();
                
            }
            if(s.equals("rand"))
            {
                System.out.println("Trimming randomly.");
                failure = false;
                for(int x = 0; x < killCount; x++)
                    data.trimRandomPlayer();
            }
        }
    }
}
