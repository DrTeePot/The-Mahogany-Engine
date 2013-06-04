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
public class Cube extends RectangularPrism{
    
    public Cube(Vector3 origin, double dimension){
        super(origin, dimension, dimension, dimension);
    }
}
