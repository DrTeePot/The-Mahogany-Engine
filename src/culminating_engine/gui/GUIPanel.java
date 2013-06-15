/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.gui;

import culminating_engine.Camera;
import culminating_engine.Renderer;
import culminating_engine.Vector3;
import culminating_engine.shapes.EquilateralTriangularPyramid;
import culminating_engine.shapes.GameObject;
import culminating_engine.shapes.RectangularPrism;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 *
 * @author Aidan
 */
public class GUIPanel extends JPanel implements Runnable{
    
    private Thread animator; //This is the thread that this class runs in
    private final int DELAY = 10; //This is the delay between draw cycles
    
    private Camera camera;
    private Renderer renderer;
    
    private final double PI = Math.PI;
    
    GameObject box1 = new RectangularPrism(new Vector3(10, 0, 0), 2,2,2);
    GameObject box2 = new RectangularPrism(new Vector3(-10, 0, 0),1,1,1);
    GameObject box3 = new RectangularPrism(new Vector3(0, 10, 0), .5,.5,.5);
    GameObject box4 = new RectangularPrism(new Vector3(0, -10, 0), .3,1,1.6);
    GameObject box5 = new RectangularPrism(new Vector3(0, 0, 10), 4,4,4);
    GameObject box6 = new RectangularPrism(new Vector3(0, 0, -10), 5,5,5);
    
    public GUIPanel(){
        camera = new Camera(new Vector3(0,0,0), Math.toRadians(35));
        renderer = new Renderer(camera, 1000, 600);
        
        renderer.addObject(box1);
        renderer.addObject(box2);
        renderer.addObject(box3);
        renderer.addObject(box4);
        renderer.addObject(box5);
        renderer.addObject(box6);
        
        camera.rotateAroundWorld(0, 0, 0);

    }
    
    /*
     * Runs after the maze has been initialize. 
     *      Is necessary, because initialization needs to happen before creating a thread
     * Pre: None
     * Post: This thread has been started
     */    
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    
    /*
     * Called with Repaint, and does all the drawing
     * Pre: None
     * Post: Board has been updated
     */
    @Override
    public void paintComponent(Graphics g) { //all drawing done in paint method
        super.paintComponent(g); 
        
        g.drawImage(renderer.wireFrameRender(), 0, 0, this);
        
    }
    
    
    public void cycle() { //Change ALL THE VARS
        camera.rotateAroundSelf(Math.toRadians(0), Math.toRadians(0), Math.toRadians(0.1));
        box1.rotateAroundPoint(Math.toRadians(0), Math.toRadians(-3), Math.toRadians(0), new Vector3(10,0,0));
        box2.rotateAroundPoint(Math.toRadians(0), Math.toRadians(1), Math.toRadians(0), new Vector3(-10,0,0));
        box3.rotateAroundPoint(Math.toRadians(-1), Math.toRadians(0), Math.toRadians(0), new Vector3(0,10,0));
        box4.rotateAroundPoint(Math.toRadians(.3), Math.toRadians(0.6), Math.toRadians(1.5), new Vector3(0,-10,0));
        
        repaint();
    }
  
    public void delay(long beforeTime){
        long timeDiff, sleep;
        timeDiff = System.currentTimeMillis() - beforeTime;
        sleep = DELAY - timeDiff; //try to keep delay constant by accounting for calculation time

        if (sleep < 0) {
            sleep = 2;
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
    
    /*
     * THE MAIN LOOP!
     * Pre: None
     * Post: ALL THE THINGS HAVE BEEN DONE! (this is the game)
     */
    @Override
    public void run() {
        long beforeTime;
        while (true) {
            beforeTime = System.currentTimeMillis();
            cycle(); //WHERE STUFF GETS DONE O_O !!!
            delay(beforeTime); //PAUSE! (Repeat)                        
        }
    }
}
