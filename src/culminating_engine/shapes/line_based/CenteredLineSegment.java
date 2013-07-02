/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.line_based;

import culminating_engine.Renderbody;
import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public class CenteredLineSegment extends Renderbody{
    private double length;
    
    /**
     * Creates a GameObject that represents a line, between two points equidistant from the origin. <br>
     * pre: none <br>
     * post: A GameObject is created that represents a line. The origin of the line is in the middle of the line.
     * @param origin - the middle point of the line
     * @param v - the distance in one direction of the line. (half the length)
     */
    public CenteredLineSegment(Vector3 v){
        super();
        
        Vector3 l = Vector3.scalarMultiply(-1, v);
                
        this.setShapeLines(new Line[]{
            new Line(l, v)
        });
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
