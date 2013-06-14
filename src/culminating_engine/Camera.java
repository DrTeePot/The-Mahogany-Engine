/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.GameObject;

/**
 *
 * @author Aidan
 */
public class Camera extends GameObject {
    
    private double fov;
    private Vector3 directionVector = new Vector3(1,0,0);
    
    final double PI = Math.PI;
    
    /**
     * Create a camera object at point (i1, i2, i3) pointing in direction 
     *      (1,0,0) and with a field of view of (fov)
     * @param i - the camera position, a Vector3
     * @param f - the field of view, in radians (0 < f < pi/2)
     */
    public Camera(Vector3 i, double f){
        super (i);
        fov = f;
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
        Vector3[] o = getOrientation();
        return o[0];
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
    
    /**
     * returns the camera's position
     * pre: none
     * post: the camera's position has been returned
     * @return new Vector3(
     *          super.getMagnitude_componentX(),
     *          super.getMagnitude_componentY(),
     *          super.getMagnitude_componentZ()
     *          )
     */
    public Vector3 getPositionVector(){
        return getOrigin();
    }
    
}
