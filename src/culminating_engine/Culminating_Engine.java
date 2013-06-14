/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.gui.GUIFrame;
import java.io.IOException;

/**
 *
 * @author tristan
 */
public class Culminating_Engine {
    public static final double PI = Math.PI;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         new GUIFrame();
    }
    
    private static void log(String s){
        System.out.println(s);
    }
}
