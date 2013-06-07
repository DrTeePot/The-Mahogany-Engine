/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
public class Shape {
    public static Face[] RECTANGLE(Vector3 origin, double width, double length){
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        
        final double halfw = width / 2;
        final double halfl = length / 2;
        
        final double x = origin.getComponents()[0];
        final double y = origin.getComponents()[1];
        final double z = origin.getComponents()[2];
        
        A = new Vector3(x, y - halfw, z - halfl);
        B = new Vector3(x, y + halfw, z - halfl);
        C = new Vector3(x, y + halfw, z + halfl);
        D = new Vector3(x, y - halfw, z + halfl);
        
        return new Face[]{new Face(new Vector3(0,0,0), new Vector3(0,0,0), new Vector3(0,0,0))};
    }
    
    public static Face[] RECTANGULAR_PRISM(Vector3 origin, double width, double height, double depth){
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        final Vector3 A2;
        final Vector3 B2;
        final Vector3 C2;
        final Vector3 D2;
        
        final double halfw = width/2;
        final double halfh = height/2;
        final double halfd = depth/2;
        
        final double x = origin.getComponents()[0];
        final double y = origin.getComponents()[1];
        final double z = origin.getComponents()[2];
        
        A = new Vector3(x - halfd, y - halfw, z - halfh);
        A2 = new Vector3(x + halfd, y - halfw, z - halfh);
        B = new Vector3(x - halfd, y + halfw, z - halfh);
        B2 = new Vector3(x + halfd, y + halfw, z - halfh);
        C = new Vector3(x - halfd, y + halfw, z + halfh);
        C2 = new Vector3(x + halfd, y + halfw, z + halfh);
        D = new Vector3(x - halfd, y - halfw, z + halfh);
        D2 = new Vector3(x + halfd, y - halfw, z + halfh);
        
        return(new Face[] {
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
        });
    }
    
    public static final Face[] CUBE(Vector3 origin, double dimension){
        return(RECTANGULAR_PRISM(origin, dimension, dimension, dimension));
    }
    
    public static final Face[] TRIANGULAR_PYRADMID(Vector3 origin, double dimension){
        //take a height, half height is where origin will fall. 
    }
}
