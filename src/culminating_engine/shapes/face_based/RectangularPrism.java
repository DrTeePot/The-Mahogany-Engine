/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.face_based;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a rectangular prism shaped GameObject. Adds small functionality related to 
 * rectangular prisms. 
 * @author tristan
 */
public class RectangularPrism extends GameObject{
    
    private double baseWidth; //the distance across the front of the base
    private double baseDepth; //the distance back the base extends
    private double prismHeight; //the amound the base extends upwards
    
    public RectangularPrism(Vector3 origin, double width, double height, double depth){
        super();
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        final Vector3 A2;
        final Vector3 B2;
        final Vector3 C2;
        final Vector3 D2;
        
        baseWidth = width;
        baseDepth = depth;
        prismHeight = height;
        
        double halfw = width / 2;
        double halfh = height / 2;
        double halfd = depth / 2;
        
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        A = new Vector3(x - halfd, y - halfw, z - halfh);
        A2 = new Vector3(x + halfd, y - halfw, z - halfh);
        B = new Vector3(x - halfd, y + halfw, z - halfh);
        B2 = new Vector3(x + halfd, y + halfw, z - halfh);
        C = new Vector3(x - halfd, y + halfw, z + halfh);
        C2 = new Vector3(x + halfd, y + halfw, z + halfh);
        D = new Vector3(x - halfd, y - halfw, z + halfh);
        D2 = new Vector3(x + halfd, y - halfw, z + halfh);
        
        this.setGameObject(new Face[] {
            new Face(A, B, D), //front triangle 1
            new Face(D, C, B), //front triangle 2
            new Face(A2, B2, D2), //back triangle 1
            new Face(D2, C2, B2), //back triangle 2
            new Face(A2, A, D), //left triangle 1
            new Face(A2, D2, D), //left triangle 2
            new Face(B2, B, C), //right triangle 1
            new Face(B2, C2, C), //right triangle 2
            new Face(A, B, B2), //bottom triangle 1
            new Face(A, A2, B2), //bottom triangle 2
            new Face(D, C, C2), //top triangle 1
            new Face(D, D2, C2) //top triangle 2
        }, origin);
    }
}
