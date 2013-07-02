/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a GameObject of any shape. Currently GameObject has no abstract methods, but was
 * originally intended to be abstract. As of now, GameObject can be called as a constructor, however, in future
 * such calls may no longer behave as expected.
 * @author tristan
 */
public class Shape extends GameObject{
    
    /**
     * Allows users to create GameObjects of any type, by defining each face in the GameObject and its origin. 
     * Unadvised, as this is hard to conceptualize, especially for complex shapes. <br>
     * pre: none
     * post: A GameObject is created.
     * @param origin - the center of motion for this object.
     * @param f - the array of Face objects that defines this object. 
     */
    public Shape(Vector3 origin, Face[] f){
        super(f, origin);
    }
}
