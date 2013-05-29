/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import java.math.*;

/**
 *
 * @author Aidan
 */
public class Camera extends Vector3 {
    
    double fov;
    Vector3 directionVector;
    
    final double PI = Math.PI;
    
    /**
     * Create a camera object at point (i1, i2, i3) pointing in direction 
     *      (xTheta, yTheta, zTheta) and with a field of view of (fov)
     * @param i - the camera position, a Vector3
     * @param f - the field of view, in radians (0 < f < pi)
     * @param d - a direction vector representing the camera's direction
     */
    Camera(Vector3 i, double f, Vector3 d){
        super (i);
        fov = f;
        directionVector = d;
    }
 
    /**
     * Returns the field of view
     * pre: none
     * post: returns the camera's field of view (in radians)
     * @return fov
     */
    public double getFOV(){
        return fov;
    } 
    
    /**
     * Sets the field of view
     * pre: none
     * post: the field of view has been changed to "f"
     * @param f - the field of view, in radians (0 < f < pi)
     */
    public void setFov(double f){
        fov = f;
    }
    
    /**
     * Returns the direction vector
     * pre: none
     * post: the direction vector has been returned
     * @return directionVector
     */
    public Vector3 getDirectionVector(){
        return directionVector;
    }
    
    /**
     * Sets the directionVector equal to the Vector3 v
     * pre: none
     * post: the direction vector has been set to v
     * @param v - the new directionVector for the camera
     */
    public void setDirectionVector(Vector3 v){
        directionVector = v;
    }
    
    /**
     * Sets the directionVector equal to a position vector with components i, j, and k
     * pre: none
     * post: the direction vector has changed to one with one with coordinates i, j, and k
     * @param i - the i component
     * @param j - the j component
     * @param k - the k component
     */
    public void setDirectionVector(double i, double j, double k){
        directionVector = new Vector3(i, j, k);
    }
}
