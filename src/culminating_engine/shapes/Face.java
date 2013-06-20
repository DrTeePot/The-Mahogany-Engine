/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;
import java.awt.Color;

/**
 * An triangular face defined by three points represented as Vector3 objects. May also have a colour, but that is
 * yet to be implemented. The Renderer class uses the points of a Face class to determine where to draw the lines
 * that make up a GameObject. 
 * @author tristan
 */
public class Face{
    /**
     * The points that make up this face. There must be 3 Vector3 objects in this array. [0-2]
     */
    private Vector3[] points;
    /**
     * The colour of this face as a Colour object. This was never implemented, and was intended as a potential 
     * extra feature, should we get this far. 
     */
    private Color colour;
    
    /**
     * Creates a new Face using an array of Vector3's. Note that there must be 3 values in the given array. <br>
     * pre: none <br>
     * post: A new Face object is created with the designated vertices, and a color of gray.
     * @param p - The array of three Vector3's that will define this face.
     */
    public Face(Vector3[] p){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(p[i]);
        }
        colour = Color.GRAY;
    }
    
    /**
     * Creates a new Face using a previously defined face. Copies the given face. <br>
     * pre: none <br>
     * post: A new Face object is created that is a clone of the given face.
     * @param f - the face to be cloned onto this object.
     */
    public Face(Face f){
        points = new Vector3[3];
        for(int i = 0; i < 3; i ++){
            points[i] = new Vector3(f.getPoint(i));
        }
        colour = f.getColor();
    }
    
    /**
     * Creates a new Face using three Vector3 objects. <br>
     * pre: none<br>
     * post: A new Face object is created with the designated vertices.
     * @param p1 - A Vector3 object that defines a vertex of this face.
     * @param p2 - A Vector3 object that defines a vertex of this face.
     * @param p3 - A Vector3 object that defines a vertex of this face.
     */
    public Face(Vector3 p1, Vector3 p2, Vector3 p3){
        points = new Vector3[3];
        points[0] = new Vector3(p1);
        points[1] = new Vector3(p2);
        points[2] = new Vector3(p3);
        colour = Color.GRAY;
    }
    
    /**
     * Returns the Vector3 object that defines the vertex of the face at the specified index location [0-2]. <br>
     * pre: none <br>
     * post: The vertex at the given index is returned to the client. 
     * @param i - the index position of the vertex [0-2]
     * @return points[i]
     */
    public Vector3 getPoint(int i){
        return points[i];
    }
    
    /**
     * Returns the Vector3 array that defines all vertices of the face. <br>
     * pre: none <br>
     * post: The vertices of the shape are returned as an array. This array will be 3 long. [0-2] 
     * @return points
     */
    public Vector3[] getPoints(){
        return points;
    }
    
    /**
     * Returns the Color object that defines the colour of the face. Coloured faces have not been implemented. <br>
     * pre: none <br>
     * post: The Color object describing this objects colour is returned. 
     * @return colour
     */
    public Color getColor(){
        return(colour);
    }
    
    /**
     * Sets the vertex at the given index to be equivalent to the given Vector3.<br>
     * pre: none <br>
     * post: The vertex at index i is reset to be a given Vector3.
     * @param i - the index of the vertex to be replaced.
     * @param s - the Vector3 object to set the vertex to. 
     */
    public void setPoint(int i, Vector3 s){
        points[i] = new Vector3(s);
    }
    
    /**
     * Sets the three vertices of the face to be the first three Vector3's in the passed array. <br>
     * pre: none <br>
     * post: The vertices of the Face object are changed. 
     * @param p - the array of Vertex3 objects to change the Face's vertices to. 
     */
    public void setPoints(Vector3[] p){
        for(int i = 0; i < 3; i++){
            points[i] = new Vector3(p[i]);
        }
    }
    
    /**
     * Adds a vector to all the vertices of the face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: The face is moved in the direction of the given vector by its magnitude. 
     * @param t - the Vector3 object to add to all vertices of this face. 
     */
    public void addVector(Vector3 t){
        for(Vector3 v : points){
            v.addVector(t);
        }
    }
    
    /**
     * Adds a vector to all the vertices of the face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: The face is moved in the direction of the given vector (i,j,k) by its magnitude. 
     * @param i - the x component of the vector to add to all the vertices of this face.
     * @param j - the y component of the vector to add to all the vertices of this face.
     * @param k - the z component of the vector to add to all the vertices of this face.
     */
    public void addVector(double i, double j, double k){
        for(Vector3 v : points){
            v.addVector(i, j, k);
        }
    }
    
    /**
     * Subtracts a vector from all the vertices of the face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: The face is moved in the opposite direction of the given vector (i,j,k) by its magnitude. 
     * @param t - the vector to subtract from all the vertices of this face.
     */
    public void subtractVector(Vector3 t){
        for(Vector3 v : points){
            v.subtractVector(t);
        }
    }
    
    /**
     * Subtracts a vector from all the vertices of the face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: The face is moved in the opposite direction of the given vector (i,j,k) by its magnitude. 
     * @param i - the x component of the vector to subtract from all the vertices of this face.
     * @param j - the y component of the vector to subtract from all the vertices of this face.
     * @param k - the z component of the vector to subtract from all the vertices of this face.
     */
    public void subtractVector(double i, double j, double k){
        for(Vector3 v : points){
            v.subtractVector(i, j, k);
        }
    }
    
    /**
     * Rotates the face by the specified amount around the worlds x, y, and z axis. Actually rotates each vertex's vector 
     * by a,b,c around the x,y,z axis. This function is only used in GameObject for the rotation of an object
     * around the worlds origin. Not a very useful class, as Face will almost never be used by the user. <br>
     * pre: none <br>
     * post: The face is rotated by the specified amount. (re-positioned) 
     * @param a - the amount to rotate around the x axis in radians.
     * @param b - the amount to rotate around the y axis in radians.
     * @param c - the amount to rotate around the z axis in radians. 
     */
    public void rotate(double a, double b, double c){
        for(Vector3 p: points){
            p.rotate(a, b, c);
        }
    }
    
    /**
     * Returns the string of the object. This is created for debugging purposes, and displays a textual version of the 
     * object. <br>
     * pre: none
     * post: All of the points in the Face are returned in an organized list.
     * @return A string of all the points, organized using returns and brackets is returned to the client.
     */
    @Override
    public String toString(){
        return("{ " + points[0] + ", " + points[1] + ", " + points[2] + " } \n");
    }
    
    /**
     * Adds a vector to all the vertices of the given face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: Returns the face that is equivalent to the given face moved in the direction 
     * of the given vector by its magnitude. 
     * @param f - the Face object to add to. 
     * @param t - the Vector3 object to add to all vertices of the face. 
     */
    public static Face addVector(Face f, Vector3 u){
        Vector3[] l;
        l = f.getPoints();
        
        for(Vector3 s : l){
            s = new Vector3(Vector3.addVectors(s, u));
        }
        
        return(new Face(l));
    }
    
    /**
     * Subtracts a vector from all the vertices of the given face, effectively translating it in three dimensional space. <br>
     * pre: none <br>
     * post: Returns the face that is equivalent to the given face moved in the opposite direction 
     * of the given vector by its magnitude. 
     * @param f - the Face object to subtract from. 
     * @param t - the Vector3 object to add to all vertices of the face. 
     */
    public static Face subtractVector(Face f, Vector3 u){
        Vector3[] l;
        l = f.getPoints();
        
        for(Vector3 s : l){
            s.subtractVector(u);
        }
        
        return(new Face(l));
    }
}
