/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.line_based;

import culminating_engine.Renderbody;
import culminating_engine.Rigidbody;
import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public class GameObject {
    
    private Vector3 shapeOrigin; //the origin of the GameObject, usually the center, that object will rotate/scale around
    //the orientation of the GameObject
    private Vector3 orientationX; 
    private Vector3 orientationY;
    private Vector3 orientationZ;
    //Defines the physical properties of the GameObject
    private Rigidbody physics;
    private Renderbody render;
    
    /**
     * Creates a new instance of a GameObject with a given Renderbody and an origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param r - the visible lines that make up this object.
     * @param origin - the point of origin for this object. The object will rotate and scale around this point.
     */
    public GameObject(Renderbody r, Vector3 origin){
        render = (r);
        shapeOrigin = origin;
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        update(); 
    }
    
    public GameObject(Renderbody r){
        render = r;
        shapeOrigin = new Vector3(0,0,0);
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);  
        update();
    }
    
    /**
     * Creates a new instance of a GameObject from another GameObject. Deep copy.
     * Initialized to have the same orientation as the given GameObject. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param g - the GameObject to be copied.
     */
    public GameObject(GameObject g){
        render = g.getRenderbody();
        shapeOrigin = new Vector3(g.getOrigin());
        orientationX = new Vector3(g.getOrientation()[0]);
        orientationY = new Vector3(g.getOrientation()[1]);
        orientationZ = new Vector3(g.getOrientation()[2]);
        update();
    }
    
    /**
     * Creates a new instance of a GameObject from another GameObject. Deep copy.
     * Initialized to have the same orientation as the given GameObject. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param g - the GameObject to be copied.
     */
    public GameObject(GameObject g, Rigidbody r){
        render = g.getRenderbody();
        shapeOrigin = new Vector3(g.getOrigin());
        orientationX = new Vector3(g.getOrientation()[0]);
        orientationY = new Vector3(g.getOrientation()[1]);
        orientationZ = new Vector3(g.getOrientation()[2]);
        
        physics = r;
        update();
    }
    
    /**
     * Creates a point GameObject with an orientation. Consists of one face, that has all points at the origin, 
     * and the shapeOrigin, which is also at the origin. <br>
     * pre: none <br>
     * post: Creates an instance of a GameObject that will render as a point at the origin.
     */
    public GameObject(){
        render = null;
        shapeOrigin = new Vector3(0,0,0);
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        update();
    }
    
    /**
     * Creates a point GameObject at the point specified by the Vector3 origin. <br>
     * pre: none <br>
     * post: A GameObject object is created with one face, at the origin. 
     * @param origin 
     */
    public GameObject(Vector3 origin){
        render = null;;
        shapeOrigin = origin;
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        update();
    }
    
    private void update(){
        if(render != null){
            render.update(shapeOrigin, orientationX, orientationY, orientationZ);
        }
    }
    /**
     * Move the shape in the direction of the Vector3(t) by the magnitude of t. <br>
     * pre: none <br>
     * post: The shape is translated by itself + the given vector. 
     * @param t - the amount and direction in which to move the object.
     */
    public void translate(Vector3 t){
        shapeOrigin.addVector(t);
        update();
    }
    
    /**
     * Move the shape in the direction of the Vector3(i,j,k) by the magnitude of (i,j,k) : Mag = Math.sqrt(a*a + b*b + c*c). <br>
     * pre: none <br>
     * post: The shape is translated by itself + the given vector. 
     * @param i - the amount to move the object in the x direction.
     * @param j - the amount to move the object in the y direction.
     * @param k - the amount to move the object in the z direction.
     */
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
        shapeOrigin.rotate(a, b, c);
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
        update();
    }
    
    /**
     * Rotates this GameObject around the objects origin (shapeOrigin) <br>
     * pre: none <br>
     * post: This object is rotated by the specified amount around its x, y, and z axis.
     * @param a - the rotation around the x axis in radians
     * @param b - the rotation around the y axis in radians
     * @param c - the rotation around the z axis in radians
     */
    public void rotateAroundSelf(double a, double b, double c){
        //rotate around x
        orientationY.rotateAround(orientationX, a);
        orientationZ.rotateAround(orientationX, a);
        
        //rotate around y
        orientationX.rotateAround(orientationY, b);
        orientationZ.rotateAround(orientationY, b);
        
        //rotate around z
        orientationX.rotateAround(orientationZ, c);
        orientationY.rotateAround(orientationZ, c);
        
        update();
    }
    
    /**
     * Rotates this GameObject around the given point as a Vector3. <br>
     * pre: none <br>
     * post: This object is rotated around a point by the specified amount around the x, y, and z axis of the world
     * @param a - the rotation around the x axis in radians
     * @param b - the rotation around the y axis in radians
     * @param c - the rotation around the z axis in radians
     */
    public void rotateAroundPoint(double a, double b, double c, Vector3 v){
        Vector3 rotatePoint = Vector3.subtractVectors(v, shapeOrigin);
        rotatePoint.rotate(a, b, c);
        shapeOrigin.setVector(Vector3.addVectors(rotatePoint, v));
        //orientationX.rotate(a, b, c);
        //orientationY.rotate(a, b, c);
        //orientationZ.rotate(a, b, c);
        
        update();
    }
    
    /**
     * Increase the distance of each point from the origin of the world by multiples of a, b, and c. <br>
     * pre: none <br>
     * post: the GameObject increases in size by a,b, and c. <br>
     * Do not use, this function is not fully implemented, and doesn't do anything.
     * @param a - the amount to scale the x components by.
     * @param b - the amount to scale the y components by.
     * @param c - the amount to scale the z components by.
     */
    public void scaleAroundWorld(double a, double b, double c){
        //TODO finish scaling
    }
    
    /**
     * Increase the distance of each point from the origin of the shape by multiples of a, b, and c. <br>
     * pre: none <br>
     * post: the GameObject increases in size by a,b, and c. <br>
     * Do not use, this function is not fully implemented, and doesn't do anything.
     * @param a - the amount to scale the x components by.
     * @param b - the amount to scale the y components by.
     * @param c - the amount to scale the z components by.
     */
    public void scaleAroundSelf(double a, double b, double c){
        
    }
    
    /**
     * Increase the distance of each point from a specified point by multiples of a, b, and c. <br>
     * pre: none <br>
     * post: the GameObject increases in size by a,b, and c. <br>
     * Do not use, this function is not fully implemented, and doesn't do anything.
     * @param a - the amount to scale the x components by.
     * @param b - the amount to scale the y components by.
     * @param c - the amount to scale the z components by.
     */
    public void scaleAroundPoint(double a, double b, double c, Vector3 v){
        
    }
    
            
    //<editor-fold desc="Setter and getters">
    
    /**
     * Resets the GameObject to have the given faces, and origin. Equivalent to recalling the constructor.<br>
     * pre: none
     * post: The object is re-defined.
     * @param f - the array of faces that defines the object
     * @param origin - the objects center aka point of rotation/scale
     */
    public void setGameObject(Renderbody r, Vector3 origin){
        render = new Renderbody(r);
        shapeOrigin = new Vector3(origin);
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        update();
    }
    
    /**
     * Sets the orientation of the object, specified by three vectors. Care should be taken when using this function,
     * to retain normal functionality the three vectors must be perpendicular to each other, as rotate functions will 
     * rotate around an objects axis, and erratic behavior may occur when axis are are non-perpendicular.<br>
     * pre: none <br>
     * post: the orientation is set to the given values. 
     * @param x - the Vector3 to set the x-orientation to.
     * @param y - the Vector3 to set the y-orientation to.
     * @param z - the Vector3 to set the z-orientation to.
     */
    public void setOrientation(Vector3 x, Vector3 y, Vector3 z){
        orientationX = new Vector3(x);
        orientationY = new Vector3(y);
        orientationZ = new Vector3(z);
    }
    
    /**
     * Changes the origin of the GameObject to the given value. Note that this will affect rotation and scaling. <br>
     * pre: none <br>
     * post: The origin is changed. 
     * @param o - the Vector3 that represents the point where the origin of the shape will reside, with respect to the world. 
     */
    public void setOrigin(Vector3 o){
        shapeOrigin = o;
    }
    
    /**
     * Changes the Rigidbody of the GameObject. Note that this will affect physical motions. <br>
     * pre: none <br>
     * post: The Rigidbody is changed. This is a reference, not a clone. 
     * @param r - the Rigidbody that represents the physics properties of this object. 
     */
    public void setRigidbody(Rigidbody r){
        physics = r;
    }
    
    /**
     * Changes the Renderbody of the GameObject. Note that this will affect the visible portion of this gameobject. <br>
     * pre: none <br>
     * post: The Renderbody is changed. This is a reference, not a clone. 
     * @param r - the Renderbody that represents the visible properties of this object. 
     */
    public void setRenderbody(Renderbody r){
        render = r;
    }
    
    /**
     * Returns the orientation vectors for the GameObject as an array of Vector3 [0-2]. This is a reference, not a clone.<br>
     * pre: none <br>
     * post: the three orientations are returned. 
     * @return Vector3[]{orientationX, orientationY, orientationZ}
     */
    public Vector3[] getOrientation(){
        return new Vector3[]{orientationX, orientationY, orientationZ};
    }
    
    /**
     * Returns the origin of the GameObject as a Vector3 object. This returns a reference, not a clone.<br>
     * pre: none <br>
     * post: The reference to the origin of this object is returned. 
     * @return Vector3 shapeOrigin
     */
    public Vector3 getOrigin(){
        return new Vector3(shapeOrigin);
    }
    
    /**
     * Returns the Rigidbody of the GameObject. This returns a reference, not a clone.<br>
     * pre: none <br>
     * post: The reference to the physics variable of this GameObject is returned. 
     * @return Rigidbody physics
     */
    public Rigidbody getRigidbody(){
        return new Rigidbody(physics);
    }
    
    public Renderbody getRenderbody(){
        return (render);
//        if(render != null)
//            return new Renderbody(render);
//        else
//            return null;
    }
    //</editor-fold>
    
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
        t = t.concat("ShapeLines: " + render.toString()  + "\n" + 
        "Origin: " + shapeOrigin.toString() + "\n" + 
        "OrientationX: " + orientationX.toString() + "\n" + 
        "OrientationY: " + orientationY.toString() + "\n" + 
        "OrientationZ: " + orientationZ.toString() + "\n");
        t = t.concat(" }");
        return t;
    }    
}
