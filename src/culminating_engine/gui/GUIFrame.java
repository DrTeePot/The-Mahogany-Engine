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
        add(new GUIWorld()); //put the world drawable space on the window

        //build the window        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("The Mahogany Engine");
        setResizable(false);
        setVisible(true);
    }
}
