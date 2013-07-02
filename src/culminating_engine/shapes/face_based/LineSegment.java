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
public class LineSegment extends GameObject{
    
    private double length;
    
    /**
     * Creates a GameObject that represents a line, beginning at point a and ending at point b. <br>
     * pre: none <br>
     * post: A GameObject is created that represents a line. The origin of the line is the first point of the line.
     * @param origin - the starting point of the line
     * @param pointb - the ending point of the line.
     */
    public LineSegment(Vector3 origin, Vector3 pointb){
        super(new Face[]{
            new Face(new Vector3(pointb), Vector3.addVectors(origin, pointb), new Vector3(origin))
        }, origin);
        length = Vector3.subtractVectors(origin, pointb).getMagnitude(); //distance between origin and ending point.
    }
    
    /**
     * Creates a GameObject that represents a line, beginning at point a and ending at point b. If l = true, point b 
     * will be equivalent to the first vector + the second<br>
     * pre: none <br>
     * post: A GameObject is created that represents a line. The origin of the line is the first point of the line.
     * @param origin - the starting point of the line
     * @param pointb - the movement away from the original point (the line part) if l = true. Otherwise the end 
     * point of the line.
     * @param l - whether to add the origin and distance or draw from the origin to distance. 
     */
    public LineSegment(Vector3 origin, Vector3 pointb, boolean l){
        super(new Face[]{
            new Face(new Vector3(origin), (l) ? (Vector3.addVectors(origin, pointb)) : (new Vector3(pointb)), new Vector3(origin))
        }, origin);
        
        // the distance between the origin (1st face, 1st point) and the second point (1st face, 2nd point)
        length = Vector3.subtractVectors(this.getFace(0).getPoint(0), this.getFace(0).getPoint(1)).getMagnitude();
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
