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
    
    private double mass;
    private boolean obeysGravity;
    private double uFriction;
    
    private Vector3 momentum;
    private Vector3 force;
    
    public Rigidbody(double m){
        mass = m;
    }
    
    public Rigidbody(double m, boolean h){
        mass = m;
        obeysGravity = h;
    }
    
    public Rigidbody(double m, double f){
        mass = m;
        uFriction = f;
    }
    
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
    //</editor-fold>
    
    //<editor-fold desc="physics">
    
    public void addMomentum(Vector3 v){
        momentum.addVector(v);
    }
    
    public void addMomentum(Vector3 v, double theta){
        Vector3 l = 
        momentum.addVector();
    }
    
    public void addForce(Vector3 f){
        force = f;
    }
    
    
}
