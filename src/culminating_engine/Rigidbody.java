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
