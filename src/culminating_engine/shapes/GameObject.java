/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Face;
import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public class GameObject {

    private Vector3 shapeOrigin;
    private Face[] shape;
    private Face[] transformShape;
    private Vector3 orientationX;
    private Vector3 orientationY;
    private Vector3 orientationZ;
    
    /**
     * Creates a new instance of a GameObject with an arbitrary number of faces and an origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param f - the collection of Face objects that make up this object.
     * @param origin - the point of origin for this object. The object will rotate and scale around this point.
     */
    GameObject(Face[] f, Vector3 origin){
        shape = new Face[f.length];
        shape = f;
        shapeOrigin = new Vector3(origin);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    /**
     * Creates a new instance of a GameObject with an arbitrary number of faces and default origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param f - the collection of Face objects that make up this object.
     */
    GameObject(Face[] f){
        shape = new Face[f.length];
        shape = f;
        shapeOrigin = new Vector3(0,0,0);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    GameObject(){
        shape = new Face[]{ 
            new Face(new Vector3(0,0,0), new Vector3(0,0,0), new Vector3(0,0,0))      
        };
        shapeOrigin = new Vector3(0,0,0);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    /**
     * The function that creates the relative position vectors to the origin of the object rather than
     * the origin of the world.
     */
    private void populateTransformShape(){
        transformShape = new Face[shape.length];
        for(int i = 0; i < shape.length; i++){
            transformShape[i] = new Face(shape[i]);
        }
        for(Face s: transformShape){
            s.addVector(Vector3.scalarMultiply(-1, shapeOrigin));
        }
    }
    
    private void update(){
        for(int i = 0; i < shape.length; i ++){
            shape[i] = new Face(Face.addVector(transformShape[i], shapeOrigin));
        }
    }
    
    public void translate(Vector3 t){
        shapeOrigin.addVector(t);
        update();
    }
    
    public void translate(double i, double j, double k){
        shapeOrigin.addVector(i, j, k);
        update();
    }
    
    /**
     * Rotates this GameObject around the origin of the world (0,0,0) <br>
     * pre: none <br>
     * post: This object is rotated by the specified amount around the x, y, and z axis of the world
     * @param a - the rotation around the x axis in radians
     * @param b - the rotation around the y axis in radians
     * @param c - the rotation around the z axis in radians
     */
    public void rotateAroundWorld(double a, double b, double c){
        for(Face d : shape){
            d.rotate(a, b, c);
        }
        shapeOrigin.rotate(a, b, c);
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
    }
    
    public void rotateAroundSelf(double a, double b, double c){
        for(Face d : transformShape){
            d.rotate(a, b, c);
        }
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
        update();
    }
    
    public void rotateAroundPoint(double a, double b, double c, Vector3 v){
        Face[] trs = new Face[shape.length];
        for(int i = 0; i < shape.length; i++){
            trs[i] = new Face(shape[i]);
            trs[i].subtractVector(v);
            trs[i].rotate(a,b,c);
        }
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
        for(int i = 0; i < shape.length; i++){
            Face l = new Face(trs[i]);
            l.addVector(v);
            shape[i] = new Face(l);
        }
    }
    
    public void scaleAroundWorld(double a, double b, double c){
        for(Face f : shape){
            for(int i = 0; i < 3; i++)
                f.setPoint(i, Vector3.matrixMultiply(a, b, c, f.getPoint(i)));
        }
    }
    
    public void scaleAroundSelf(double a, double b, double c){
        
    }
    
    public void scaleAroundPoint(double a, double b, double c, Vector3 v){
        
    }
    
    //<editor-fold desc="Setter and getters">
    
    public void setGameObject(Face[] f, Vector3 origin){
        shape = new Face[f.length];
        shape = f;
        shapeOrigin = new Vector3(origin);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    public void setShape(Face[] f){
        shape = f;
        populateTransformShape();
    }
    
    public void setFace(int i, Face f){
        shape[i] = new Face(f);
    }
    
    public void setPoint(int i, int n, Vector3 p){
        shape[i].setPoint(n, p);
    }
    
    public void setOrientation(Vector3 x, Vector3 y, Vector3 z){
        orientationX = new Vector3(x);
        orientationY = new Vector3(y);
        orientationZ = new Vector3(z);
    }
    
    public void setOrigin(Vector3 o){
        shapeOrigin = new Vector3(o);
    }
    
    public Face getFace(int i){
        return(shape[i]);
    }
    
    public Vector3 getPoint(int i, int n){
        return(shape[i].getPoint(n)); //n must be [0,2] <<inclusive
    }
    
    public Face[] getShape(){
        return(shape);
    }
    
    public Vector3[] getOrientation(){
        return new Vector3[]{orientationX, orientationY, orientationZ};
    }
    
    public Vector3 getOrigin(){
        return shapeOrigin;
    }
    
    //</editor-fold>
    
    @Override
    public String toString(){
        String t = "{ \n";
        for(Face s : shape){
            t = t.concat(s.toString());
        }
        t = t.concat(" }");
        return t;
    }
    
    
    
}
