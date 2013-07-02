/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.line_based.Line;
import java.awt.Color;

/**
 * The component of a GameObject that handles its rendering. Renderpoints, colour, and the transformations of these
 * in relation to a GameObject are handled here. 
 * @author tristan
 */
public class Renderbody {
    private Line[] shapeLines;
    private Line[] worldLines;
    
    private Vector3 originalOrientationX;
    private Vector3 originalOrientationY;
    private Vector3 originalOrientationZ;
    
    private Color colour;
    
    private double d180 = Math.toRadians(180);
    public Renderbody(Line[] l){
        shapeLines = new Line[l.length];
        for(int i = 0; i < l.length; i++){
            shapeLines[i] = new Line(l[i]);
        }
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = Color.WHITE;
    }
    
    public Renderbody(Vector3 l){
        shapeLines = new Line[1];
        shapeLines[0] = new Line(l,l);
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = Color.WHITE;
    }
    
    public Renderbody(Line[] l, Color c){
        shapeLines = new Line[l.length];
        for(int i = 0; i < l.length; i++){
            shapeLines[i] = new Line(l[i]);
        }
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = c;
    }
    
    public Renderbody(Vector3 l, Color c){
        shapeLines = new Line[1];
        shapeLines[0] = new Line(l,l);
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = c;
    }
    
    public Renderbody(Renderbody r){
        shapeLines = r.getShapeLines();
        worldLines = r.getWorldLines();
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = r.getColour();
    }
    
    public Renderbody(Color c){
        shapeLines = new Line[1];
        shapeLines[0] = new Line(new Vector3(0,0,0), new Vector3(0,0,0));
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = c;
    }
    
    public Renderbody(){
        shapeLines = new Line[1];
        shapeLines[0] = new Line(new Vector3(0,0,0), new Vector3(0,0,0));
        originalOrientationX = new Vector3(1,0,0);
        originalOrientationY = new Vector3(0,1,0);
        originalOrientationZ = new Vector3(0,0,1);
        //worldLines = shapeLines.clone();
        initializeWorldLines();
        colour = Color.WHITE;
    }
    
    /**
     * This function resets the position vectors of the object when the position of the shape with respect to
     * its origin (shapeOrigin) has changed.
     */
    public void update(Vector3 origin, Vector3 orienX, Vector3 orienY, Vector3 orienZ){
        Vector3 rotate = new Vector3(0,0,0);
        double d;
        d = Vector3.getAngle(orienX, originalOrientationX);
        //System.out.println("distance of rotation x: " + d);
        if(d > 0 && d != 180){
            rotate = new Vector3(Vector3.crossMultiply(orienX, originalOrientationX));
        } else {
            d = Vector3.getAngle(orienY, originalOrientationY);
            //System.out.println("distance of rotation y: " + d);
            if(d > 0 && d != 180){
                rotate = new Vector3(Vector3.crossMultiply(orienY, originalOrientationY));
            } else {
                d = Vector3.getAngle(orienZ, originalOrientationZ);
                //System.out.println("distance of rotation z: " + d);
                if(d > 0 && d != 180){
                    rotate = new Vector3(Vector3.crossMultiply(orienZ, originalOrientationZ));
                }
            }
        }
        
        if(d > 0){
            for(Line i : shapeLines){
                i.rotateAround(rotate, d);
            }
            originalOrientationX = new Vector3(orienX);
            originalOrientationY = new Vector3(orienY);
            originalOrientationZ = new Vector3(orienZ);
        }
        
        for(int i = 0; i < worldLines.length; i++){
            worldLines[i] = (Line.addVector(shapeLines[i], origin));
        }
    }
    
    private void initializeWorldLines() {
        worldLines = new Line[shapeLines.length];
        for(int i = 0; i < shapeLines.length; i++){
            worldLines[i] = new Line(shapeLines[i]);
        }
    }
    
    public Vector3[] getOriginalOrientations(){
        return new Vector3[]{new Vector3(originalOrientationX), new Vector3(originalOrientationY), new Vector3(originalOrientationZ)};
    }
    
    public Line[] getShapeLines(){
        Line[] l = new Line[shapeLines.length];
        for(int i = 0; i < shapeLines.length; i++){
            l[i] = new Line(shapeLines[i]);
        }
        return l;
    }
    
    public Line[] getWorldLines(){
        Line[] l = new Line[worldLines.length];
        for(int i = 0; i < worldLines.length; i++){
            l[i] = new Line(worldLines[i]);
        }
        return l;
    }
    
    public Color getColour(){
        return colour;
    }
    
    public void setShapeLines(Line[] l){
        shapeLines = l;
        initializeWorldLines();
    }
    
    public void setColour(Color c){
        colour = c;
    }
    
    /**
     * Returns the string of the object. This is created for debugging purposes, and displays a textual version of the 
     * object. <br>
     * pre: none
     * post: All of the points in the GameObject are returned in an organized list.
     * @return A string of all the points, organized using returns and brackets is returned to the client.
     */
    @Override
    public String toString(){
        String t = "{ \n";
        for(Line s : shapeLines){
            t = t.concat(s.toString());
        }
        t = t.concat(" }");
        return t;
    }  
}