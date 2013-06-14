/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.GameObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author tristan
 */
public class Physicser {
    /**
     * Objects, the list of objects that have been added to the Physicser with the purpose of have physics applied. <br>
     * The index of the GameObject in the ArrayList matches that of the forces in the forces array.
     */
    private ArrayList<GameObject> objects;
    /**
     * Forces, the forces that have been added to all gameObjects and must be acted upon. <br>
     * Forces[i][n] = a single Vector3 that is acting on the object, where i is the index of the gameObject
     * in the objects ArrayList, and n is the index of the force acting on the i object.
     * n = 0 is the acceleration due to gravity, if enabled.
     */
    private ArrayList<ArrayList<Vector3>> forces;
    /**
     * Forces that are applied only once, and then disappear, or forces that have become 
     */
    private ArrayList<ArrayList<Vector3>> momentum; //same format as forces, where tempForces is the actual vectors being applied 
    private double accelerationDueToGravity;
    
    
    
    Physicser(){
        objects = new ArrayList();
        accelerationDueToGravity = 9.8;
    }
    
    Physicser(GameObject[] l){
        objects = new ArrayList<GameObject>();
        objects.addAll(Arrays.asList(l));
    }
    
    public void update(){ //needs to be called whenever physics should be updated (ie every frame)
        
    }
    
    public void addForce(GameObject o, Vector3 a){
        if(objects.contains(o)){
            //TODO add code
        }
    }
    
    public void addMomentum(GameObject o, Vector3 a){
        if(objects.contains(o)){
            //TODO add code
        }
    }
    
    public void removeForce(GameObject o, Vector3 a){
        if(objects.contains(o)){
            //TODO add code
        }
    }
    
    public void removeMomentum(GameObject o, Vector3 a){
        if(objects.contains(o)){
            //TODO add code
        }
    }
    
    
}
