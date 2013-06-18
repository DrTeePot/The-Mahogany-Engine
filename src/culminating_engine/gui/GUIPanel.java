/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.gui;

import culminating_engine.shapes.Camera;
import culminating_engine.Renderer;
import culminating_engine.Vector3;
import culminating_engine.shapes.EquilateralTriangularPyramid;
import culminating_engine.shapes.GameObject;
import culminating_engine.shapes.Player;
import culminating_engine.shapes.RectangularPrism;
import culminating_engine.shapes.RectangularPyramid;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Aidan
 */
public class GUIPanel extends JPanel implements Runnable{
    
    private Thread animator; //This is the thread that this class runs in
    private final int DELAY = 20; //This is the delay between draw cycles
    
    private Player player; //used to move the camera 
    
    private Camera camera; 
    private Renderer renderer;
    
    private Random r = new Random();
    
    private final double PI = Math.PI;
    
    GameObject p = new RectangularPrism(new Vector3(0, 0, 0), 0,0,0); //the game object used to define the player
    
    
    /**
     * The following are variables used to demonstrate the Mahogany engines 
     *      capabilities. 
     * It will render a specified number of random rotating shapes, in a floating
     *      world, which the user may then navigate using arrow keys (movement)
     *      and WASD (camera rotation).
     * 
     */
    int numObjects = 200; //number of obects to randomly generate
    double spaceObjectsOccupy = 500; //size of cube in which objects are generated
    double maxObjectSize = 20; 
    double maxRotationSpeed = 0.2;
    double maxRotationAroundSelfSpeed = 2;
    double maxRotationDistance = 100;
            
    //will hold all objects
    ArrayList<GameObject> objects = new ArrayList<GameObject>(); 
    
    //will hold the objects' rotation speed around a point
    ArrayList<Double[]> rotations = new ArrayList<Double[]>(); 
    
    //will hold the objects' rotation speed around itself
    ArrayList<Double[]> rotationsAroundSelf = new ArrayList<Double[]>();
    
    //will hold the distances of the points that objects will rotate about
    ArrayList<Double[]> rotationDistance = new ArrayList<Double[]>();

    /**
     * Initializes the GUIPanel. All global variable should be initialized here
     * pre: none
     * post: the panel has been initialized
     */
    public GUIPanel(){
        //Window must be focused to add a KeyListener
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new Controls()); //allow keyboard input (for controls)
        
        camera = new Camera(new Vector3(0,0,0), Math.toRadians(35));
        player = new Player(p, camera); //add the camera to the player, so that 
                                        //it moves with the player
        
        //Randomly generate the objects
        for (int i = 0; i < numObjects; i++){
            objects.add(new RectangularPrism(
                    new Vector3(r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)),
                    r.nextDouble()*maxObjectSize, r.nextDouble()*maxObjectSize, r.nextDouble()*maxObjectSize)
                    );
        }
        
        //Randomly generate points for the objects to rotate around
        for (int i = 0; i < numObjects; i++){
            rotationDistance.add(new Double[]{
                                objects.get(i).getOrigin().getMagnitude_componentX() + 
                    r.nextDouble() *maxRotationDistance - (maxRotationDistance / 2),
                                objects.get(i).getOrigin().getMagnitude_componentY() + 
                    r.nextDouble() *maxRotationDistance - (maxRotationDistance / 2),
                                objects.get(i).getOrigin().getMagnitude_componentZ() + 
                    r.nextDouble() *maxRotationDistance - (maxRotationDistance / 2)});
        }
        
        //Randomly generate how the objects rotate around their points
        for (int i = 0; i < numObjects; i++){
            rotations.add(new Double[]{r.nextDouble()*maxRotationSpeed - (maxRotationSpeed/2),
                r.nextDouble()*maxRotationSpeed - (maxRotationSpeed/2),
                r.nextDouble()*maxRotationSpeed - (maxRotationSpeed/2)});
        }
        
        //Randomly generate how each object rotates about itself
        for (int i = 0; i < numObjects; i++){
            rotationsAroundSelf.add(new Double[]{
                r.nextDouble()*maxRotationAroundSelfSpeed - (maxRotationAroundSelfSpeed/2),
                r.nextDouble()*maxRotationAroundSelfSpeed - (maxRotationAroundSelfSpeed/2),
                r.nextDouble()*maxRotationAroundSelfSpeed - (maxRotationAroundSelfSpeed/2)});
        }
        
        //Create the renderer and add all the objects to it
        renderer = new Renderer(player.getCamera(), 1000, 600);
        renderer.addObjects(objects);
        
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
        
        long diff = System.currentTimeMillis() - timeeee;
        
        double fps_ = 1000.0 / (double) diff;
        
        timeeee = System.currentTimeMillis();
        
        //display the rendered output
        g.drawImage(renderer.wireFrameRender(), 0, 0, this);

        
        System.out.println(String.valueOf(fps_));
        
        repaint();
    }
    long timeeee;
    
    /*
     * Moves all gameObjects, and the player
     * Pre: None
     * Post: gameObjects and player have been moved to appropriate spaces
     */
    public void cycle() { //Change ALL THE VARS
        
        //Rotate each object properly
        for (int i = 0; i < objects.size(); i++){            
            objects.get(i).rotateAroundPoint(Math.toRadians(rotations.get(i)[0]),
                                            Math.toRadians(rotations.get(i)[1]), 
                                            Math.toRadians(rotations.get(i)[2]),
                    new Vector3(rotationDistance.get(i)[0],rotationDistance.get(i)[1], rotationDistance.get(i)[2]));
            objects.get(i).rotateAroundSelf(Math.toRadians(rotationsAroundSelf.get(i)[0]),
                                            Math.toRadians(rotationsAroundSelf.get(i)[1]), 
                                            Math.toRadians(rotationsAroundSelf.get(i)[2]));//,
                                            //new Vector3(objects.get(i).getOrigin()));
        }
        
        //Move and rotate the camera (based on pressed keys)
        player.move();
        
    }
  
     /*
     * Listens for keys, and reports which keys are down to the player object
     * Pre: None
     * Post: Player object knows what keys are pressed
     */
    class Controls extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode(); // what key was pressed?
                        
            //Controls (pretty self explanatory)
            if ((key == KeyEvent.VK_LEFT) && (!player.getMovingLeft()))  {
                player.setMovingLeft(true);
            }else if ((key == KeyEvent.VK_RIGHT) && (!player.getMovingRight())) {
                player.setMovingRight(true);
            }else if ((key == KeyEvent.VK_UP) && (!player.getMovingForward())) {
                player.setMovingForward(true);
            }else if ((key == KeyEvent.VK_DOWN) && (!player.getMovingBackward())) {
                player.setMovingBackward(true);
            }else if ((key == KeyEvent.VK_SPACE) && (!player.getMovingUp())) {
                player.setMovingUp(true);
            }else if ((key == KeyEvent.VK_SHIFT) && (!player.getMovingDown())) {
                player.setMovingDown(true);
            }
            
            if ((key == KeyEvent.VK_A) && (!player.getRotatingNegativeY()))  {
                player.setRotatingNegativeY(true);
            }else if ((key == KeyEvent.VK_D) && (!player.getRotatingPositiveY())) {
                player.setRotatingPositiveY(true);
            }else if ((key == KeyEvent.VK_W) && (!player.getRotatingPositiveZ())) {
                player.setRotatingPositiveZ(true);
            }else if ((key == KeyEvent.VK_S) && (!player.getRotatingNegativeZ())) {
                player.setRotatingNegativeZ(true);
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode(); // what key was released?
                       
            //Controls (pretty self explanatory)
            if (key == KeyEvent.VK_LEFT) {
                player.setMovingLeft(false);
            } else if (key == KeyEvent.VK_RIGHT) {
                player.setMovingRight(false);
            } else if (key == KeyEvent.VK_UP) {
                player.setMovingForward(false);
            } else if (key == KeyEvent.VK_DOWN) {
                player.setMovingBackward(false);
            } else if (key == KeyEvent.VK_SPACE) {
                player.setMovingUp(false);
            } else if (key == KeyEvent.VK_SHIFT) {
                player.setMovingDown(false);
            }
            
            if (key == KeyEvent.VK_A)  {
                player.setRotatingNegativeY(false);
            }else if (key == KeyEvent.VK_D) {
                player.setRotatingPositiveY(false);
            }else if (key == KeyEvent.VK_W) {
                player.setRotatingPositiveZ(false);
            }else if (key == KeyEvent.VK_S) {
                player.setRotatingNegativeZ(false);
            }
        }
        
    }
    
    /*
     * Delays the program for the set delay (taking into account time spent during calculations
     * Pre: The time before "cycle" occured
     * Post: She gme has been delayed for the delay
     */
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
