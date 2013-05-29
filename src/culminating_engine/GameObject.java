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
    private Vector3 orientationX;
    private Vector3 orientationY;
    private Vector3 orientationZ;
    
    
    GameObject(Vector3[][] p){
        shape = new Face[p.length];
        for(int i = 0; i < p.length; i++){
            shape[i] = new Face(p[i]);
        }
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    GameObject(Face[] f){
        shape = new Face[f.length];
        shape = f;
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    public void translate(Vector3 t){
        for(Face a : shape){
            a.translate(t);
        }
    }
    
    public void translate(double i, double j, double k){
        for(Face a : shape){
            a.translate(new Vector3(i,j,k));
        }
    }
    
    /**
     * Rotates this GameObject around the origin of the world (0,0,0) <br>
     * pre: none <br>
     * post: This object is rotated by the specified amount around the x, y, and z axis of the world
     * @param a - the rotation around the x axis in radians
     * @param b - the rotation around the y axis in radians
     * @param c - the rotation around the z axis in radians
     */
    public void rotate(double a, double b, double c){
        for(Face d : shape){
            d.rotate(a, b, c);
        }
    }
    
    public void rotateAroundX(double d){ //angle
//        for(Face s : shape){
//            double x = s.getComponents()[0];
//            double y = s.getComponents()[1];
//            double z = s.getComponents()[2];
//
//            s.setVector(x, Math.cos(d)*y + Math.sin(d) * z, (-1)*Math.sin(d) * y + Math.cos(d) * z);
//        } 
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
