/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.line_based;

import culminating_engine.Vector3;
import java.awt.Color;

/**
 * Replaces the Face class in order to minimize on processing power. GameObjects will in future be drawn as collections of 
 * lines. 
 * @author tristan
 */
public class Line {
    
    /**
     * The points that make up this line. 
     */
    private Vector3 pointA;
    private Vector3 pointB;
    
    Color colour;
    
    public Line(Vector3 a, Vector3 b){
        pointA = a;
        pointB = b;
        colour = Color.WHITE;
    }
    
    public Line(Line l){
        pointA = l.getPointA();
        pointB = l.getPointB();
        colour = l.getColour();
    }
    
    public Line(Vector3 a, Vector3 b, Color c){
        pointA = a;
        pointB = b;
        colour = c;
    }
    
    public Line(Line l, Color c){
        pointA = l.getPointA();
        pointB = l.getPointB();
        colour = c;
    }
    
    //<editor-fold desc="Getters and setters">
    /**
     * Returns the Vector3 representing the first point of this line. Returns a clone, not a reference.
     * @return new Vector3(pointA)
     */
    public Vector3 getPointA(){
        return new Vector3(pointA);
    }
    
    /**
     * Returns the Vector3 representing the second point of this line. Returns a clone, not a reference.
     * @return new Vector3(pointB)
     */
    public Vector3 getPointB(){
        return new Vector3(pointB);
    }
    
    /**
     * Returns the Vector3s representing both points of this line. Returns a clone, not a reference.
     * @return new Vector3[]{clone(pointA), clone(pointB)}
     */
    public Vector3[] getPoints(){
        return new Vector3[]{new Vector3(pointA), new Vector3(pointB)};
    }
    
    /**
     * Returns the Color object that represents the draw colour of this line. Returns a reference, not a clone.
     * @return 
     */
    public Color getColour(){
        return colour;
    }
    
    /**
     * Sets the starting point of the line to be the given Vector3. It is set as a reference, not a clone.
     * @param a 
     */
    public void setPointA(Vector3 a){
        pointA = a;
    }
    
    /**
     * Sets the ending point of the line to be the given Vector3. It is set as a reference, not a clone.
     * @param b
     */
    public void setPointB(Vector3 b){
        pointB = b;
    }
    
    /**
     * Sets the starting and ending points of the line to be the given Vector3's. Set as references not clones.
     * @param a
     * @param b 
     */
    public void setPoints(Vector3 a, Vector3 b){
        pointA = a; 
        pointB = b;
    }
    
    /**
     * Sets the draw color of the line to be the given Color. Sets as reference not clone.
     * @param c 
     */
    public void setColour(Color c){
        colour = c;
    }
    
    //</editor-fold>
    
    public void addVector(Vector3 v){
        pointA.addVector(v);
        pointB.addVector(v);
    }
    
    public void subtractVector(Vector3 v){
        pointA.subtractVector(v);
        pointB.subtractVector(v);
    }
    
    public void rotate(double a, double b, double c){
        pointA.rotate(a, b, c);
        pointB.rotate(a, b, c);
    }
    
    public void rotateAround(Vector3 rotate, double d){
        pointA.rotateAround(rotate, d);
        pointB.rotateAround(rotate, d);
    }
    
    public static Line addVector(Line line, Vector3 origin) {
        Vector3 a = Vector3.addVectors(line.getPointA(), origin);
        Vector3 b = Vector3.addVectors(line.getPointB(), origin);
        return(new Line(a, b));
    }
    
    /**
     * Returns the string of the object. This is created for debugging purposes, and displays a textual version of the 
     * object. <br>
     * pre: none
     * post: All of the points in the Line are returned in an organized list.
     * @return A string of all the points, organized using returns and brackets is returned to the client.
     */
    @Override
    public String toString(){
        return("{ " + pointA + ", " + pointB + " } \n");
    }
}
