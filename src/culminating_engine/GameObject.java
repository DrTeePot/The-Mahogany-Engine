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
    
    private Vector3[][] worldPoints;
    private Vector3 orientationX;
    private Vector3 orientationY;
    private Vector3 orientationZ;
    
    GameObject(Vector3[][] p){
        worldPoints = p;
        orientationX = new Vector3(1,0,0);
        orientationY = new Vector3(0,1,0);
        orientationZ = new Vector3(0,0,1);
    }
    
    public void translate(Vector3 t){
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                s.addVector(t);
            }
        }
    }
    
    public void translate(double i, double j, double k){
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                s.addVector(i,j,k);
            }
        }
    }
    
    public void rotate(double a, double b, double c){
        for(Vector3[] d : worldPoints){
            for(Vector3 s : d){
                s.rotate(a,b,c);
            }
        }
    }
    
    public void rotateAroundX(double d){ //angle
        for(Vector3[] a : worldPoints){
            for(Vector3 s : a){
                double x = s.getComponents()[0];
                double y = s.getComponents()[1];
                double z = s.getComponents()[2];
                        
                s.setVector(x, Math.cos(d)*y + Math.sin(d) * z, (-1)*Math.sin(d) * y + Math.cos(d) * z);
            }
        }
    }
    
    
    
    //<editor-fold desc="Setter and getters">
    
    public Vector3[] getFace(int i){
        return(worldPoints[i]);
    }
    
    public Vector3 getPoint(int i, int n){
        return(worldPoints[i][n]); //n must be [0,2] <<inclusive
    }
    
    public Vector3[][] getShape(){
        return(worldPoints);
    }
    
    public Vector3[] getOrientation(){
        return new Vector3[]{orientationX, orientationY, orientationZ};
    }
    
    
    
}
