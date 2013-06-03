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
    
<<<<<<< HEAD
    private Vector3[][] worldPoints;
=======
    private Vector3 shapeOrigin;
    private Face[] shape;
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
    private Vector3 orientationX;
    private Vector3 orientationY;
    private Vector3 orientationZ;
    
<<<<<<< HEAD
<<<<<<< HEAD
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
        
=======
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
    
    GameObject(Vector3[][] p){
<<<<<<< HEAD
        worldPoints = p;
=======
        shape = new Face[p.length];
        for(int i = 0; i < p.length; i++){
            shape[i] = new Face(p[i]);
        }
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
<<<<<<< HEAD
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
    GameObject(Face[] f){
        shape = new Face[f.length];
        shape = f;
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
<<<<<<< HEAD
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
    }
    public void translate(Vector3 t){
<<<<<<< HEAD
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                s.addVector(t);
            }
=======
        for(Face a : shape){
            a.translate(t);
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
        }
        shapeOrigin.addVector(t);
    }
    
    public void translate(double i, double j, double k){
<<<<<<< HEAD
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                s.addVector(i,j,k);
            }
=======
        for(Face a : shape){
            a.translate(new Vector3(i,j,k));
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
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
<<<<<<< HEAD
<<<<<<< HEAD
    public void rotateAroundWorld(double a, double b, double c){
=======
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
    public void rotate(double a, double b, double c){
<<<<<<< HEAD
        for(Vector3[] d : worldPoints){
            for(Vector3 s : d){
                s.rotate(a,b,c);
            }
=======
<<<<<<< HEAD
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
        for(Face d : shape){
            d.rotate(a, b, c);
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
        }
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
    public void rotateAroundX(double d){ //angle
<<<<<<< HEAD
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                double x = s.getComponents()[0];
                double y = s.getComponents()[1];
                double z = s.getComponents()[2];
                        
                s.setVector(x, Math.cos(d)*y + Math.sin(d) * z, (-1)*Math.sin(d) * y + Math.cos(d) * z);
            }
        }
=======
    public void rotateAroundX(double d){ //angle
<<<<<<< HEAD
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                double x = s.getComponents()[0];
                double y = s.getComponents()[1];
                double z = s.getComponents()[2];
                        
                s.setVector(x, Math.cos(d)*y + Math.sin(d) * z, (-1)*Math.sin(d) * y + Math.cos(d) * z);
            }
        }
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
=======
//        for(Face s : shape){
//            double x = s.getComponents()[0];
//            double y = s.getComponents()[1];
//            double z = s.getComponents()[2];
//
//            s.setVector(x, Math.cos(d)*y + Math.sin(d) * z, (-1)*Math.sin(d) * y + Math.cos(d) * z);
//        } 
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
<<<<<<< HEAD
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
=======
>>>>>>> b697351b22428646b8ebd53870665a586b67c77f
    }
    
    
    
    //<editor-fold desc="Setter and getters">
    
<<<<<<< HEAD
    public Vector3[] getFace(int i){
        return(worldPoints[i]);
    }
    
    public Vector3 getPoint(int i, int n){
        return(worldPoints[i][n]); //n must be [0,2] <<inclusive
    }
    
    public Vector3[][] getShape(){
        return(worldPoints);
=======
    public Face getFace(int i){
        return(shape[i]);
    }
    
    public Vector3 getPoint(int i, int n){
        return(shape[i].getPoint(n)); //n must be [0,2] <<inclusive
    }
    
    public Face[] getShape(){
        return(shape);
>>>>>>> e22765485428378d1986833fce96929e4dc77beb
    }
    
    public Vector3[] getOrientation(){
        return new Vector3[]{orientationX, orientationY, orientationZ};
    }
    
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
