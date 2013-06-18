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
public class Line extends GameObject{
    /**
     * Creates a GameObject that represents a line, beginning at point a and ending at point b. <br>
     * pre: none <br>
     * post: A GameObject is created that represents a line. The origin of the line is the first point of the line.
     * @param origin
     * @param distance 
     */
    public Line(Vector3 origin, Vector3 distance){
        super(new Face[]{
            new Face(new Vector3(origin), Vector3.addVectors(origin, distance), new Vector3(origin))
        }, origin);
    }
}
