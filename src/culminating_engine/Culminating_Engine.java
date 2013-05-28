/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

/**
 *
 * @author tristan
 */
public class Culminating_Engine {
    public static final double PI = Math.PI;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vector3 si = new Vector3(0,1,0);
        log(si.toString());
        si.rotate(PI/2, 0, 0);
        log(si.toString());
        
    }
    
    private static void log(String s){
        System.out.println(s);
    }
}
