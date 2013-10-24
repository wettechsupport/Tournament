/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingUtilities;
/**
 *
 * @author Maurice
 */
public class TestGUI extends JFrame{
    
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
        tournamentFrame = new JFrame();
        tournamentTitle = new JLabel("DW Tournament");
        tournamentTitle2 = new JLabel("Contestants");
        tournamentPanel = new JPanel();
        
        //setup JFrame
        tournamentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tournamentFrame.setTitle("Tournament");
        tournamentFrame.setSize(600,300);
        tournamentFrame.setLocationRelativeTo(null);
        
        //create GribBagLayout and the GridBagLayout Constraints
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gBConstraints = new GridBagConstraints();
        
        //setup gridBag constraint
        gBConstraints.fill = GridBagConstraints.BOTH;
        gBConstraints.gridx = 2;
        gBConstraints.gridy = 2;
        
        tournamentPanel.setLayout(gridBag);
        tournamentPanel.add(tournamentTitle);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private void createJLabels(){
        final JFrame frame = new JFrame();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for(String contestant : testArray){
                    frame.add(new JLabel(contestant));
                    frame.validate();
                    frame.repaint();                    
                }
            }
        });
    }
    
}
