/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.GameObject;
import culminating_engine.shapes.RectangularPrism;
import culminating_engine.shapes.Face;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        // TODO code application logic here
//        GameObject si = new GameObject(new Face[] { 
//            new Face(new Vector3(0,1,1), new Vector3(0,2,1), new Vector3(0,1,2)),
//            new Face(new Vector3(0,1,1), new Vector3(1,1,1), new Vector3(0,1,2)),
//            new Face(new Vector3(0,1,1), new Vector3(1,1,1), new Vector3(0,2,1)),
//            new Face(new Vector3(1,1,1), new Vector3(0,2,1), new Vector3(0,1,2))
//        }, new Vector3(0,1,1));
        
        GameObject box = new RectangularPrism(new Vector3(10,0,0), 10,7,5);
        GameObject tri = new GameObject(new Face[] {
             new Face(new Vector3(5,0,0), new Vector3(6,0,0),new Vector3(0,5,0))
             }, new Vector3(0,0,0));
        
        box.rotateAroundSelf(PI, PI, PI/5);
        GameObject[] world = {box};
        
        Camera c = new Camera(new Vector3(0,0,0), Math.toRadians(35), new Vector3(1,0,0));
        Renderer r = new Renderer(world, c, 600);
        
        BufferedImage b = (BufferedImage)r.wireFrameRender();
        
        File outputfile = new File("image.jpg");
        ImageIO.write(b, "jpg", outputfile);
        
        Vector3 v = new Vector3(-2,-0.00001,0);
        Vector3 u = new Vector3(5,0,0);
        v.rotateTowards(u, PI/2);
        
        log(v.toString());
        
    }
    
    private static void log(String s){
        System.out.println(s);
    }
}
