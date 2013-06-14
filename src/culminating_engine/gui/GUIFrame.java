/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.gui;

import javax.swing.JFrame;

/**
 *
 * @author Aidan
 */
public class GUIFrame extends JFrame{
    
    public GUIFrame(){
        add(new GUIPanel()); //put the maze drawable space on the window

        //build the window        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setTitle("The Mahogany Engine");
        setResizable(false);
        setVisible(true);
    }
}
