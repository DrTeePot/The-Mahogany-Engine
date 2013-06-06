/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;
import java.awt.Color;

/**
 *
 * @author tristan
 */
public class Face {
    
    private Vector3[] points;
    private Color colour;
    
    public Face(Vector3[] p){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(p[i]);
        }
        colour = Color.GRAY;
    }
    
    public Face(Face f){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(f.getPoint(i));
        }
        colour = f.getColor();
    }
    
    public Face(Vector3 p1, Vector3 p2, Vector3 p3){
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
    
    public void addVector(Vector3 t){
        for(Vector3 v : points){
            v.addVector(t);
        }
    }
    
    public void addVector(double i, double j, double k){
        for(Vector3 v : points){
            v.addVector(i, j, k);
        }
    }
    
    public void subtractVector(Vector3 t){
        for(Vector3 v : points){
            v.subtractVector(t);
        }
    }
    
    public void subtractVector(double i, double j, double k){
        for(Vector3 v : points){
            v.subtractVector(i, j, k);
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
    
    public static Face addVector(Face f, Vector3 u){
        Vector3[] l;
        l = f.getPoints();
        
        for(Vector3 s : l){
            s.addVector(u);
        }
        
        return(new Face(l));
    }
    
    public static Face subtractVector(Face f, Vector3 u){
        Vector3[] l;
        l = f.getPoints();
        
        for(Vector3 s : l){
            s.subtractVector(u);
        }
        
        return(new Face(l));
    }
}
