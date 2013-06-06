/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.GameObject;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aidan
 */
public class Renderer {
    
    ArrayList gameObjects = new ArrayList();
    Camera camera;
    double screenSize;
    
    /**
     * Create a renderer object, which is a factory that has the ability to 
     *      render a scene when given a game object and a camera object
     * pre: none
     * post: A renderer object has been created
     * @param o - an array of GameObjects (to possibly be rendered)
     * @param c - a camera object
     * @param s - the size of the screen (measured diagonally)
     */
    Renderer(GameObject[] o, Camera c, double s){
        gameObjects.addAll(Arrays.asList(o));
        camera = c;
        screenSize = s;
    }
    
    /**
     * Returns true if a point is within the field of view, false otherwise
     * pre: none
     * post: true has been returned if the point is in FOV, false otherwise
     * @param v - The point to be tested
     * @return boolean - true if point is in FOV, else false
     */
    public boolean pointInFOV(Vector3 v){
        Vector3 d1 = camera.getPositionVector(); 
        Vector3 d2 = v.subtractVector(camera.getPositionVector());
        
       double angle = d1.getAngle(d2);
        
        if ((camera.fov > angle)&&(angle > -camera.fov)){
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
     * Given a Vector3, returns the rendered (2d) coordinates, if they are in the FOV
     * pre: none
     * post: the point 's rendered coordinates have been returned
     * @param v - The Vector3 to be converted
     * @return  Point Object - The Vector3 rendered coordinates
     *                       - Null returned if point not in FOV
     */
    public Point V3toV2(Vector3 v){
        double xCoordinate, yCoordinate;
        double xyAngle, xzAngle;
        
        if (pointInFOV(v)){
            Vector3 d1 = camera.getDirectionVector();
            Vector3 d2 = v.subtractVector(camera.getPositionVector());

            Vector3 d1xy = new Vector3(d1.getMagnitude_componentX(), d1.getMagnitude_componentY(), 0);
            Vector3 d2xy = new Vector3(d2.getMagnitude_componentX(), d2.getMagnitude_componentY(), 0);
            Vector3 d1xz = new Vector3(d1.getMagnitude_componentX(), 0, d1.getMagnitude_componentZ());
            Vector3 d2xz = new Vector3(d2.getMagnitude_componentX(), 0, d2.getMagnitude_componentZ());
            
            xyAngle = d1xy.getAngle(d2xy);
            xzAngle = d1xz.getAngle(d2xz);
            
            xCoordinate = screenSize * Math.atan(xyAngle);
            yCoordinate = screenSize * Math.atan(xzAngle);
                        
            return new Point((int)xCoordinate, (int)yCoordinate);
        } else {
            return null;
        }
    }
}