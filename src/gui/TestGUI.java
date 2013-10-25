/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
/**
 *
 * @author Maurice
 */
public class TestGUI extends JPanel{
    
    private ArrayList <String> testArray;
    private JFrame tournamentFrame;
    private JLabel tournamentTitle;
    private JLabel tournamentTitle2;
    private JPanel tournamentPanel;
    
    public TestGUI(){
        loadTestArray();
        initComponents();
    }

    private void loadTestArray() {
        testArray = new ArrayList();
        testArray.add("Marcus");
        testArray.add("Avery");
        testArray.add("Linus");
        testArray.add("Sean");
    }

    private void initComponents() {
        tournamentFrame = ContentJFrame.getInstance();
        
        //setup JFrame
        tournamentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tournamentFrame.setTitle("Tournament");
        tournamentFrame.setSize(643, 391);
        tournamentFrame.setLocationRelativeTo(null);
        
        //create GribBagLayout and the GridBagLayout Constraints
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gBConstraintTitle = new GridBagConstraints();
        
        tournamentPanel = new JPanel();
        tournamentPanel.setLayout(gridBag);
        
        //set up title, add constraint, add to JPanel
        tournamentTitle = new JLabel("DW Tournament of Champions");
        tournamentTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        gBConstraintTitle.fill = GridBagConstraints.PAGE_START;
        gBConstraintTitle.gridx = 0;
        gBConstraintTitle.gridy = 0;
        gridBag.setConstraints(tournamentTitle, gBConstraintTitle);
        tournamentPanel.add(tournamentTitle);
        
        //set up title, add constraint, add to JPanel
        tournamentTitle2 = new JLabel("Contestants:");
        tournamentTitle2.setFont(new java.awt.Font("Tahoma", 1, 18));
        gBConstraintTitle.fill = GridBagConstraints.LINE_START;
        gBConstraintTitle.gridx = 0;
        gBConstraintTitle.gridy = 1;
        gridBag.setConstraints(tournamentTitle2, gBConstraintTitle);
        tournamentPanel.add(tournamentTitle2);      
        
        createJLabels(gridBag, gBConstraintTitle);
        
        JButton Player1 = new JButton();
        JButton Player2 = new JButton();
        Player1.setName(testArray.get(0));
        Player2.setName(testArray.get(2));
        
        gBConstraintTitle.fill = GridBagConstraints.HORIZONTAL;
        gBConstraintTitle.gridx = 0;
        gBConstraintTitle.gridy = 4;
        gBConstraintTitle.weightx=0.5;
        gridBag.setConstraints(Player1, gBConstraintTitle);
        tournamentPanel.add(Player1);
        tournamentPanel.validate();
        tournamentPanel.repaint();
        
        gBConstraintTitle.fill = GridBagConstraints.HORIZONTAL;
        gBConstraintTitle.gridx = 0;
        gBConstraintTitle.gridy = 4;
        gBConstraintTitle.weightx=0.5;
        gridBag.setConstraints(Player2, gBConstraintTitle);
        tournamentPanel.add(Player2);
        tournamentPanel.validate();
        tournamentPanel.repaint();  
    }
    
    
    
    private void createJLabels(final GridBagLayout g, final GridBagConstraints c ){       
         SwingUtilities.invokeLater(new Runnable() {            
            @Override
            public void run() {
                 for(String contestant : testArray){
                    JLabel temp = new JLabel();
                    temp.setName(contestant);
                    temp.setFont(new java.awt.Font("Tahoma", 1, 18));
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.weightx=0.5;
                    g.setConstraints(temp, c);
                    tournamentPanel.add(temp);                  
                    tournamentPanel.validate();
                    tournamentPanel.repaint();                    
                }
            }
        });
        
    }
    
}
