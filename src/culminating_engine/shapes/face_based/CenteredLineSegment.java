/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.face_based;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a line shaped GameObject. Adds small functionality related to lines. 
 * @author tristan
 */
public class CenteredLineSegment extends GameObject{
    
    private double length;
    
    /**
     * Creates a GameObject that represents a line, between two points equidistant from the origin. <br>
     * pre: none <br>
     * post: A GameObject is created that represents a line. The origin of the line is in the middle of the line.
     * @param origin - the middle point of the line
     * @param v - the distance in one direction of the line. (half the length)
     */
    public CenteredLineSegment(Vector3 origin, Vector3 v){
        super();
        
        Vector3 l = new Vector3(Vector3.subtractVectors(v, origin));
        Vector3 l2 = new Vector3(Vector3.addVectors(origin, v));
        setGameObject(new Face[]{
            new Face(l, l2, l)
        }, origin);
        length = v.getMagnitude() * 2; //distance between origin and ending point.
    }
    
    /**
     * Returns the length of the line. <br>
     * pre: none <br>
     * post: the double value represent the length of this line is returned to the client.
     * @return double length
     */
    public double getLength(){
        return length;
    }
}
