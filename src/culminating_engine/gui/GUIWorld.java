/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.gui;

import culminating_engine.Physicser;
import culminating_engine.Renderbody;
import culminating_engine.RendererV2;
import culminating_engine.Rigidbody;
import culminating_engine.Vector3;
import culminating_engine.shapes.face_based.Camera;
import culminating_engine.shapes.line_based.CenteredLineSegment;
import culminating_engine.shapes.line_based.Cube;
import culminating_engine.shapes.line_based.GameObject;
import culminating_engine.shapes.line_based.Line;
import culminating_engine.shapes.line_based.Player;
import culminating_engine.shapes.line_based.RectangularPrism;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author tristan
 */
public class GUIWorld extends JPanel implements Runnable{

    private Thread animator; //This is the thread that this class runs in
    private final int DELAY = 20; //This is the delay between draw cycles
    
    private Player player; //used to move the camera 
    
    private Camera camera; 
    private RendererV2 renderer;
    private Physicser physics;
    
    private final double PI = Math.PI;
    
    //will hold all objects
    ArrayList<GameObject> floor = new ArrayList<GameObject>(); 
    
    GameObject p = new GameObject(new Cube(5), new Vector3(0,0,0)) {}; //the game object used to define the player
    
    GameObject tri1 = new GameObject(new Cube(2), new Vector3(1,0,0));
    
    Rigidbody rigidTest = new Rigidbody(10);
    
    private Random r = new Random();
    
    int numRect = 800; 
    int numRectPyr = 0;
    int numTriPyr = 0;
    int numTriPris = 0;
    int numCube = 0;
    int numObjects = numRect + numTriPyr + numTriPris + numCube + numRectPyr;//number of obects to randomly generate
    double spaceObjectsOccupy = 50; //size of cube in which objects are generated
    double maxObjectSize = 3; 
    double maxRotationSpeed = 0.2;
    double maxRotationAroundSelfSpeed = 2;
    double maxRotationDistance = 20;
            
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
    public GUIWorld(){
        //Window must be focused to add a KeyListener
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new Controls()); //allow keyboard input (for controls)
        
        camera = new Camera(new Vector3(0,0,0), Math.toRadians(35)); // the second argument is the field of view
        player = new Player(p, camera); //add the camera to the player, so that 
        
        
        //Randomly generate the objects
        for (int i = 0; i < numRect; i++){
            objects.add(new GameObject(
                    new RectangularPrism(
                        r.nextDouble()*maxObjectSize,
                        r.nextDouble()*maxObjectSize, 
                        r.nextDouble()*maxObjectSize,
                        Color.WHITE),
                    new Vector3(
                        r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                        r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                        r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)))
                    );
        }
        
        /*
        for (int i = 0; i < numRectPyr; i++){
            objects.add(new RectangularPyramid(
                    new Vector3(r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)),
                    r.nextDouble()*maxObjectSize, r.nextDouble()*maxObjectSize, r.nextDouble()*maxObjectSize)
                    );
        }
        for (int i = 0; i < numTriPyr; i++){
            objects.add(new EquilateralTriangularPyramid(
                    new Vector3(r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)), 
                    r.nextDouble()*maxObjectSize ));
        }
        for (int i = 0; i < numTriPris; i++){
            objects.add(new EquilateralTriangularPrism(
                    new Vector3(r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)),
                    r.nextDouble()*maxObjectSize, r.nextDouble()*maxObjectSize*3));
        }
        for (int i = 0; i < numCube; i++){
            objects.add(new Cube(
                    new Vector3(r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2),
                    r.nextDouble()*spaceObjectsOccupy - (spaceObjectsOccupy/2)), 
                    r.nextDouble()*maxObjectSize ));
        }
        */
        
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
        renderer = new RendererV2(player.getCamera(), 1000, 600, 1000);
        renderer.addObjects(objects);
//        Renderbody square = new Renderbody(new Line[]{
//            new Line(new Vector3(-0.5, 0, 0), new Vector3(0.5, 0, 0)),
//        new Line(new Vector3(0, 0.5, 0), new Vector3(0, -0.5, 0))});
        
        for(int x = -20; x < 20; x ++){
            for(int y = -20; y < 20; y ++ ){
                GameObject v = new GameObject(new CenteredLineSegment(new Vector3(0.5,0,0)), new Vector3(x,y,0));
                GameObject h = new GameObject(new CenteredLineSegment(new Vector3(0,0.5,0)), new Vector3(x,y,0));
                floor.add(v);
                floor.add(h);
            }
        }
        //Create the renderer and add all the objects to it
        renderer.addObject(tri1);
        renderer.addObjects(floor);
        //tri1.rotateAroundWorld(Math.toRadians(0), Math.toRadians(0), Math.toRadians(20));
    }
    
    /*
     * Runs after the panel has been initialized. 
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
     * Called with repaint, and does all the drawing
     * Pre: None
     * Post: Draw has been updated
     */
    @Override
    public void paintComponent(Graphics g) { //all drawing done in paint method
        super.paintComponent(g); 
        
        long diff = System.currentTimeMillis() - timeeee;
        
        double fps_ = 1000.0 / (double) diff;
        
        timeeee = System.currentTimeMillis();
        
        //display the rendered output
        g.drawImage(renderer.wireFrameRender(), 0, 0, this);
        g.setColor(Color.WHITE);
        fps = String.valueOf(fps_);
        
        g.drawString(fps, 30, 30);
        
        //System.out.println(String.valueOf(fps_));
        
        repaint();
    }
    long timeeee;
    String fps = "";
    /*
     * Moves all gameObjects, and the player
     * Pre: None
     * Post: gameObjects and player have been moved to appropriate spaces
     */
    public void cycle() { //Change ALL THE VARS
        
        tri1.rotateAroundPoint(Math.toRadians(0), Math.toRadians(0), Math.toRadians(1), new Vector3(0,5,0));
        
        //tri1.translate(0.5,0,0);
        //Move and rotate the camera (based on pressed keys)
        player.move();
        
        
        
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
        
        //renderer.wireFrameRender();
        //System.out.println(tri1.getRenderbody().getWorldLines()[0].toString());
        //update all the physics objects
        //physics.update();
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
