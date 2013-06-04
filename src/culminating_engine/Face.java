/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import java.awt.Color;

/**
 *
 * @author tristan
 */
public class Face {
    
    Vector3[] points;
    Color colour;
    
    Face(Vector3[] p){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(p[i]);
        }
        colour = Color.GRAY;
    }
    
    Face(Face f){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(f.getPoint(i));
        }
        colour = f.getColor();
    }
    
    Face(Vector3 p1, Vector3 p2, Vector3 p3){
        points = new Vector3[3];
        points[0] = new Vector3(p1);
        points[1] = new Vector3(p2);
        points[2] = new Vector3(p3);
        colour = Color.GRAY;
    }
    
    public Vector3 getPoint(int i){
        return points[i];
    }
    
    public Vector3[] getPoints(){
        return points;
    }
    
    public Color getColor(){
        return(colour);
    }
    
    public void setPoint(int i, Vector3 s){
        points[i] = new Vector3(s);
    }
    
    public void setPoints(Vector3[] p){
        for(int i = 0; i < 3; i++){
            points[i] = new Vector3(p[i]);
        }
    }
    
    public void translate(Vector3 t){
        for(Vector3 v : points){
            v.addVector(t);
        }
    }
    
    public void rotate(double a, double b, double c){
        for(Vector3 p: points){
            p.rotate(a, b, c);
        }
    }
    
    @Override
    public String toString(){
        return("{ " + points[0] + ", " + points[1] + ", " + points[2] + " } \n");
    }
}
