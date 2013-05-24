/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

/**
 *
 * @author tristan
 */
public class Vector3 {
    
    private double x;
    private double y;
    private double z;
    
    /**
     * Create a Vector3 object that stretches from the origin to point (i, j, k). This is a position vector.
     * @param i - the x position
     * @param j - the y position
     * @param k - the z position
     */
    Vector3(double i, double j, double k){
        i = x;
        j = y;
        k = z;
    }
    
    /**
     * Create a Vector3 object that is equivalent to the vector s. 
     * @param s - a Vector3 object representing a vector.
     */
    Vector3(Vector3 s){
        x = s.getComponent_X();
        y = s.getComponent_Y();
        z = s.getComponent_Z();
    }
    
    //<editor-fold desc="Getters and setters">
    public void setVector(Vector3 s){
        x = s.getComponent_X();
        y = s.getComponent_Y();
        z = s.getComponent_Z();
    }
    
    public void setVector(double i, double j, double k){
        x = i;
        y = j;
        z = k;
    }
    
    public double[] getCompoenents(){
        double[] l = {x,y,z};
        return (l);
    }
    
    public double getComponent_X(){
        return(x);
    }
    
    public double getComponent_Y(){
        return(y);
    }
    
    public double getComponent_Z(){
        return(z);
    }
    
    //</editor-fold>
    
    //<editor-fold desc="calculated manipulations and returns">
    /**
     * Add a vector to this vector.
     * pre: none
     * post: The vector is altered to be the vector + the vector s.
     * @param s 
     */
    public void addVector(Vector3 s){
        
    }
    
    /**
     * Add a vector to this vector.
     * pre: none
     * post: The vector is altered to be the vector + the vector defined by (i, j, k)
     * @param i - the x component of the addition vector
     * @param j - the y component of the addition vector
     * @param k - the z component of the addition vector
     */
    public void addVector(double i, double j, double k){
        
    }
    
    /**
     * Return the magnitude of the vector as a double.
     * pre: none
     * post: the vector's magnitude is returned.
     * @return magVector
     */
    public double getMagnitude(){
        return(Math.sqrt((x*x + y*y + z*z)));
    }
    
    /**
     * Return the dot product of the vector by another vector as a double
     * pre: none
     * post: The dot product is returned
     * @param s - the vector to be dotted with this
     * @return this (dot) s
     */
    public double dot(Vector3 s){
        return(x*s.getComponent_X() + y*s.getComponent_Y() + z*s.getComponent_Z());
    }
    
    /**
     * Return the vector that results when the vector is crossed with another vector as a Vector3.
     * pre: none
     * post: returns the vector object that results when the vector is crossed with the vector s
     * @param s - the vector to be crossed
     * @return the vector object that results from this x s
     */
    public Vector3 cross(Vector3 s){
        double x2 = s.getComponent_X();
        double y2 = s.getComponent_Y();
        double z2 = s.getComponent_Z();
        return(new Vector3(y*z2 - z*y2,
                z*x2 - x*z2, 
                x*y2 - y*x2));
    }
    
    /**
     * Returns the vector that results from projecting this vector on the parameter vector.
     * pre: none
     * post: Returns the vector 
     * @param s
     * @return the projection vector
     */
    public Vector3 projectionOn(Vector3 s){
        return(new Vector3(0,0,0));
    }
            
    //</editor-fold>
    
    
    
    
    
}
