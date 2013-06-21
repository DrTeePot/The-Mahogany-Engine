/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

/**
 *
 * @author tristan
 */
public class Rigidbody {
    
    private double mass; //the mass of the object
    private boolean obeysGravity; //whether or not this object will obey gravity
    private double uFriction; //the coeficient of friction
    
    private Vector3 momentum; //the momentum of the object at all times until modified
    private Vector3 force; //the force being applied to this object at all times until modified
    
    /**
     * Creates a Rigidbody object that determines all of the physics attributes and behaviours of an object.<br>
     * pre: none <br>
     * post: A Rigidbody is created with a mass and default values. (doesn't obey gravity, frictionless)
     * @param m - the mass of the object in kilograms
     */
    public Rigidbody(double m){
        mass = m;
        obeysGravity = false;
        uFriction = 0;
    }
    
    /**
     * Creates a Rigidbody object that determines all of the physics attributes and behaviours of an object.<br>
     * pre: none <br>
     * post: A Rigidbody is created with a mass and the user may define whether the object obeys gravity. (frictionless)
     * @param m - the mass of the object in kilograms
     * @param h - boolean value, if true this object obeys gravity. 
     */
    public Rigidbody(double m, boolean h){
        mass = m;
        obeysGravity = h;
    }
    
    /**
     * Creates a Rigidbody object that determines all of the physics attributes and behaviours of an object.<br>
     * pre: none <br>
     * post: A Rigidbody is created with a mass and coefficient of friction (does not obey gravity)
     * @param m - the mass of the object in kilograms
     * @param f - double value representing the coefficient of friction. If 0, frictionless, if >1 lots of friction. 
     */
    public Rigidbody(double m, double f){
        mass = m;
        uFriction = f;
    }
    
    /**
     * Creates a Rigidbody object that determines all of the physics attributes and behaviours of an object.<br>
     * pre: none <br>
     * post: A Rigidbody is created with a mass, coefficient of friction, and user may define whether it obeys gravity.
     * @param m - the mass of the object in kilograms
     * @param f - double value representing the coefficient of friction. If 0, frictionless, if >1 lots of friction. 
     * @param h - boolean value, if true this object obeys gravity.
     */
    public Rigidbody(double m, double f, boolean h){
        mass = m;
        uFriction = f;
        obeysGravity = h;
    }
    
    //<editor-fold desc="Setters">
    
    public void setMass(double m){
        mass = m;
    }
    
    public void setFriction(double f){
        uFriction = f;
    }
    
    public void setObeysGravity(boolean t){
        obeysGravity = t;
    }
    
    public void setForce(Vector3 f){
        force = new Vector3(f);
    }
    
    public void setMomentum(Vector3 v){
        momentum = new Vector3(v);
    }
    //</editor-fold>
    
    //<editor-fold desc="Getters">
    
    public double getMass(){
        return mass;
    }
    
    public double getFriction(){
        return uFriction;
    }
    
    public boolean obeysGravity(){
        return(obeysGravity);
    }
    
    public Vector3 getForce(){
        return force;
    }
    
    public Vector3 getMomentum(){
        return momentum;
    }
    //</editor-fold>
    
    //<editor-fold desc="physics">
    
    public void addMomentum(Vector3 v){
        momentum.addVector(v);
    }
    
    public void addForce(Vector3 f){
        force.addVector(f);
    }
    
    public void removeMomentum(Vector3 v){
        momentum.subtractVector(v);
    }
    
    public void removeForce(Vector3 f){
        force.subtractVector(f);
    }
    
    //</editor-fold>
    
    
}
