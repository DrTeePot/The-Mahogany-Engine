/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Face;
import culminating_engine.GameObject;
import culminating_engine.Vector3;

/**
 *
 * @author tristan
 */
class RectangularPrism extends GameObject{
    final Vector3 A;
    final Vector3 B;
    final Vector3 C;
    final Vector3 D;
    final Vector3 A2;
    final Vector3 B2;
    final Vector3 C2;
    final Vector3 D2;
    
    private Face[] shape;
    private Vector3 shapeOrigin;
    
    public RectangularPrism(Vector3 origin, double width, double height, double depth){
        super(null);
        double halfw = width/2;
        double halfh = height/2;
        double halfd = depth/2;
        
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
        
        shape = new Face[] {
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
            new Face(D, C, C2),
            new Face(D, D2, C2)
        };
        shapeOrigin = origin;
    }
}
