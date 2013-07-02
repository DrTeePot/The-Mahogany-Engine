/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.face_based.GameObject;
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
     * The worlds acceleration downwards. This will be applied as a Vector3 force downwards, to all Rigidbody
     * objects that have the variable obeysGravity set to true. 
     */
    private double accelerationDueToGravity;
    
    
    
    public Physicser(){
        objects = new ArrayList();
        accelerationDueToGravity = 9.8;
    }
    
    public Physicser(GameObject[] l){
        objects = new ArrayList<GameObject>();
        objects.addAll(Arrays.asList(l));
    }
    
    public void update(){ //needs to be called whenever physics should be updated (ie every frame)
        for(GameObject o : objects){
            o.translate(o.getRigidbody().getMomentum());
            o.getRigidbody().addMomentum(o.getRigidbody().getForce());
        }
    }
    
    public void addObject(GameObject o){
        objects.add(o);
    }
    
    public void addObjects(ArrayList<GameObject> o){
        objects.addAll(o);
    }
    
    public void removeObject(GameObject o){
        
    }
    
    public void removeObjects(GameObject[] o){
        
    }
    
    
}
