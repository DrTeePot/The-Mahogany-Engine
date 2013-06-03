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
        System.arraycopy(p, 0, points, 0, 3);
        colour = Color.GRAY;
    }
    
    Face(Vector3 p1, Vector3 p2, Vector3 p3){
        points = new Vector3[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        colour = Color.GRAY;
    }
    
    public Vector3 getPoint(int i){
        return points[i];
    }
    
    public Vector3[] getPoints(){
        return points;
    }
    
    public void setPoint(int i, Vector3 s){
        points[i] = s;
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
