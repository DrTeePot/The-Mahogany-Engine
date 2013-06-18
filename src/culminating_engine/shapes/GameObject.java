/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Rigidbody;
import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public abstract class GameObject {
    private Vector3 shapeOrigin;
    private Face[] shape;
    private Face[] transformShape;
    private Vector3 orientationX;
    private Vector3 orientationY;
    private Vector3 orientationZ;
    
    private boolean renderable;
    private boolean physicsable;
    private boolean collideable;
    private boolean makesSound;
    
    private Rigidbody physics;
    
    
    /**
     * Creates a new instance of a GameObject with an arbitrary number of faces and an origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param f - the collection of Face objects that make up this object.
     * @param origin - the point of origin for this object. The object will rotate and scale around this point.
     */
    public GameObject(Face[] f, Vector3 origin){
        shape = new Face[f.length];
        for(int i = 0; i < f.length; i++){
            shape[i] = new Face(f[i]);
        }
        shapeOrigin = new Vector3(origin);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        
        
    }
    
    /**
     * Creates a new instance of a GameObject with an arbitrary number of faces and an origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param f - the collection of Face objects that make up this object.
     * @param origin - the point of origin for this object. The object will rotate and scale around this point.
     */
    public GameObject(Face[] f, Vector3 origin, boolean rend, boolean physic, boolean collide, boolean sound){
        shape = new Face[f.length];
        for(int i = 0; i < f.length; i++){
            shape[i] = new Face(f[i]);
        }
        shapeOrigin = new Vector3(origin);
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        
        renderable = rend;
        physicsable = physic;
        collideable = collide;
        makesSound = sound;
    }
            
    /**
     * Creates a new instance of a GameObject with an arbitrary number of faces and default origin.
     * Initialized to have the same orientation as the world. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param f - the collection of Face objects that make up this object.
     */
    public GameObject(Face[] f){
        shape = new Face[f.length];
        for(int i = 0; i < f.length; i++){
            shape[i] = new Face(f[i]);
        }
        
        double nx = 0;
        double px = 0;
        double ny = 0;
        double py = 0;
        double nz = 0;
        double pz = 0;
        
        for(Face l : f){
            for (Vector3 v : l.getPoints()){
                
                if(v.getComponents()[0] > px){
                    px = v.getComponents()[0];
                }else if(v.getComponents()[0] < nx){
                    nx = v.getComponents()[0];
                }
                
                if(v.getComponents()[1] > py){
                    py = v.getComponents()[1];
                } else if(v.getComponents()[1] < ny){
                    ny = v.getComponents()[1];
                }
                
                if(v.getComponents()[2] > pz){
                    pz = v.getComponents()[2];
                } else if(v.getComponents()[2] < nz){
                    nz = v.getComponents()[2];
                }
            }
        }
        
        shapeOrigin = new Vector3(nx + ((px - nx)/2),ny + ((py - ny)/2), nz + ((pz - nz)/2));
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
     * @param f - the collection of Face objects that make up this object
     * @param rend - whether the object is renderable
     * @param physic - whether the object obeys physics
     * @param collide - whether the object performs collision
     * @param sound - whether the object makes sounds
     */
    public GameObject(Face[] f, Rigidbody r){
        shape = new Face[f.length];
        for(int i = 0; i < f.length; i++){
            shape[i] = new Face(f[i]);
        }
        
        double nx = 0;
        double px = 0;
        double ny = 0;
        double py = 0;
        double nz = 0;
        double pz = 0;
        
        for(Face l : f){
            for (Vector3 v : l.getPoints()){
                
                if(v.getComponents()[0] > px){
                    px = v.getComponents()[0];
                }else if(v.getComponents()[0] < nx){
                    nx = v.getComponents()[0];
                }
                
                if(v.getComponents()[1] > py){
                    py = v.getComponents()[1];
                } else if(v.getComponents()[1] < ny){
                    ny = v.getComponents()[1];
                }
                
                if(v.getComponents()[2] > pz){
                    pz = v.getComponents()[2];
                } else if(v.getComponents()[2] < nz){
                    nz = v.getComponents()[2];
                }
            }
            
            
        }
        
        shapeOrigin = new Vector3(nx + ((px - nx)/2),ny + ((py - ny)/2), nz + ((pz - nz)/2));
        populateTransformShape();
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
        physics = r;
    }
    
    /**
     * Creates a new instance of a GameObject from another GameObject. Deep copy.
     * Initialized to have the same orientation as the given GameObject. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param g - the GameObject to be copied.
     */
    public GameObject(GameObject g){
        shape = new Face[g.getShape().length];
        for(int i = 0; i < g.getShape().length; i++){
            shape[i] = new Face(g.getShape()[i]);
        }
        shapeOrigin = new Vector3(g.getOrigin());
        populateTransformShape();
        orientationX = new Vector3(g.getOrientation()[0]);
        orientationY = new Vector3(g.getOrientation()[1]);
        orientationZ = new Vector3(g.getOrientation()[2]);
    }
    
    /**
     * Creates a new instance of a GameObject from another GameObject. Deep copy.
     * Initialized to have the same orientation as the given GameObject. <br>
     * pre: none <br>
     * post: A GameObject object is created.
     * @param g - the GameObject to be copied.
     */
    public GameObject(GameObject g, Rigidbody r){
        shape = new Face[g.getShape().length];
        for(int i = 0; i < g.getShape().length; i++){
            shape[i] = new Face(g.getShape()[i]);
        }
        shapeOrigin = new Vector3(g.getOrigin());
        populateTransformShape();
        orientationX = new Vector3(g.getOrientation()[0]);
        orientationY = new Vector3(g.getOrientation()[1]);
        orientationZ = new Vector3(g.getOrientation()[2]);
        
        physics = r;
    }
    
    /**
     * Creates a point GameObject with an orientation. Consists of one face, that has all points at the origin, 
     * and the shapeOrigin, which is also at the origin. <br>
     * pre: none <br>
     * post: Creates an instance of a GameObject that will render as a point at the origin.
     */
    public GameObject(){
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
     * Creates a point GameObject at the point specified by the Vector3 origin. <br>
     * pre: none <br>
     * post: A GameObject object is created with one face, at the origin. 
     * @param origin 
     */
    public GameObject(Vector3 origin){
        double[] a = origin.getComponents();

        shape = new Face[]{ 
            new Face(new Vector3(a[0],a[1],a[2]), new Vector3(a[0],a[1],a[2]), new Vector3(a[0],a[1],a[2]))      
        };
        shapeOrigin = new Vector3(origin);
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
            s.subtractVector(shapeOrigin);
        }
    }
    
    /**
     * This function resets the position vectors of the object when the position of the shape with respect to
     * its origin (shapeOrigin) has changed.
     */
    private void update(){
        for(int i = 0; i < shape.length; i++){
            Face l = new Face(transformShape[i]);
            l.addVector(shapeOrigin);
            shape[i] = new Face(l);
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
        for(Face d : shape){
            d.rotate(a, b, c);
        }
        populateTransformShape();
        shapeOrigin.rotate(a, b, c);
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
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
        
        for(Face d:transformShape){
            for(Vector3 p : d.getPoints()){
                p.rotateAround(orientationX, a);
            }
        }
        orientationY.rotateAround(orientationX, a);
        orientationZ.rotateAround(orientationX, a);
        
        for(Face d:transformShape){
            for(Vector3 p : d.getPoints()){
                p.rotateAround(orientationY, b);
            }
        }
        orientationX.rotateAround(orientationY, b);
        orientationZ.rotateAround(orientationY, b);
        
        for(Face d:transformShape){
            for(Vector3 p : d.getPoints()){
                p.rotateAround(orientationZ, c);
            }
        }
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
        Face[] trs = new Face[shape.length];
        for(int i = 0; i < shape.length; i++){
            trs[i] = new Face(shape[i]);
            trs[i].subtractVector(v);
            trs[i].rotate(a,b,c);
        }
        
        shapeOrigin.rotate(a, b, c);
        orientationX.rotate(a, b, c);
        orientationY.rotate(a, b, c);
        orientationZ.rotate(a, b, c);
        
        for(int i = 0; i < shape.length; i++){
            Face l = new Face(trs[i]);
            l.addVector(v);
            shape[i] = new Face(l);
        }
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
        for(Face d:shape){
            for(Vector3 p : d.getPoints()){
                p.matrixMultiply(a,b,c);
            }
        }
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
     * Resets the 
     * @param f
     * @param origin 
     */
    public void setGameObject(Face[] f, Vector3 origin){
        shape = new Face[f.length];
        for(int i = 0; i < f.length; i++){
            shape[i] = new Face(f[i]);
        }
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
