/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.face_based;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a cube shaped GameObject. Adds small functionality related to Cubes. 
 * @author tristan
 */
public class Cube extends RectangularPrism{
    private double sideLength; //the length of each side
    
    /**
     * Creates a Cube object (extends RectangularPrism extends GameObject) with stated origin and length. <br>
     * pre: none <br>
     * post: A GameObject around the given point, with equal side lengths specified by the user. 
     * @param origin - the point to construct this object around. Point of movement.
     * @param dimension - the length of each side. 
     */
    public Cube(Vector3 origin, double dimension){
        super(origin, dimension, dimension, dimension);
        sideLength = dimension;
    }
    
    /**
     * Returns the length of the sides of this Cube. <br>
     * pre: none <br>
     * post: The length is returned to the client.
     * @return double sideLength
     */
    public double getLength(){
        return sideLength;
    }
}
