/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a rectangular based pyramid shaped GameObject. Adds small functionality related 
 * to rectangular base pyramids. 
 * TODO add functionality for rectangular pyramids
 * @author tristan
 */
public class RectangularPyramid extends GameObject{
    
    private double depth; //the distance the base extends back
    private double width; //the distance the base extends across the front
    private double height; // the distance between the center of the base and the vertex at the top
    private double sideLength; //the distance from a point on the base to the vertex at the top.
    
    /**
     * Creates a pyramid with a rectangular base with the functionality of a GameObject. <br>
     * pre: none
     * post: A rectangular pyramid is created with specified base dimensions and a specified height.
     * @param origin - the point that this object is build around, the axis of movement.
     * @param l - the depth of the base (distance away from a viewpoint
     * @param w - the width of the base (distance across)
     * @param h - the height of the pyramid
     */
    public RectangularPyramid(Vector3 origin, double l, double w, double h){
        super(); //place holder, creates a blank GameObject that is changed later.
        
        depth = l;
        width = w;
        height = h;
        
        //coordinates of the origin
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        //declare the points of the shape
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        final Vector3 E; //point at tip of pyramid
        
        //for ease, these variables are used.
        double halfw = width / 2;
        double halfd = depth / 2;
        double halfh = height / 2;
        
       
        double hyp = Math.sqrt(depth * depth + width * width);
        sideLength = Math.sqrt((hyp/2) * (hyp/2) + height * height);
        //double lowerHeight = (sideLength * Math.cos(supplementTetrahedralAngle)) / (2 * Math.cos(lambda));
        
         //limits trig funciton calls, and increases readability
        double lambda = Math.acos((hyp/2)/sideLength); //angle between D - B - E
        double lowerHeight = sideLength * Math.sin(lambda) / 2;
        
        // define the points of the shape
        A = new Vector3(x - halfw, y - halfd, z - lowerHeight);
        B = new Vector3(x + halfw, y - halfd, z - lowerHeight);
        C = new Vector3(x + halfw, y + halfd, z - lowerHeight);
        D = new Vector3(x - halfw, y + halfd, z - lowerHeight);
        E = new Vector3(x,y,z + height - lowerHeight);
        
        //set the GameObject to have the points defined above. 
        this.setGameObject(new Face[]{
            new Face(A,B,C), //bottom 1
            new Face(A,D,B), //bottom 2
            new Face(A,B, E), //front
            new Face(A,D,E), //left
            new Face(D,C,E), //back
            new Face(C,B,E), //right
        }, origin);
    }
}
