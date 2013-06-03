/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

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
//        for(int i = 0; i < f.length; i++){
//            shape[i] = new Face(f[i]);
//        }
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    GameObject(Face[] f){
        shape = new Face[f.length];
        shape = f;
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
            s.translate(Vector3.scalarMultiply(-1, shapeOrigin));
        }
    }
    
    public void translate(Vector3 t){
        for(Face a : shape){
            a.translate(t);
        }
        shapeOrigin.addVector(t);
    }
    
    public void translate(double i, double j, double k){
        for(Face a : shape){
            a.translate(new Vector3(i,j,k));
        }
        shapeOrigin.addVector(i, j, k);
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
        for(int i = 0; i < shape.length; i++){
            Face l = new Face(transformShape[i]);
            l.translate(shapeOrigin);
            shape[i] = new Face(l);
        }
    }
    
    //<editor-fold desc="Setter and getters">
    
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
