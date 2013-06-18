/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public class Shape extends GameObject{
    public Shape(Vector3 origin, Face[] f){
        super(f, origin);
    }
}
