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
public class EquilateralTriangularPrism extends GameObject{
    
    EquilateralTriangularPrism(Vector3 origin, double sideLength, double height){
        super();
        final double PI = Math.PI;
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 A2;
        final Vector3 B2;
        final Vector3 C2;
        
        double hyp = sideLength / (2 * Math.cos(PI/6));
        double halfh = height / 2;
        double cos45 = Math.cos(PI/4);
        double cos15 = Math.cos(PI/12);
        double cos75 = Math.cos((PI * 7) / 12);
        
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        
        A = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z - halfh);
        B = new Vector3(x - (hyp * cos75), y - (cos15 * hyp), z - halfh);
        C = new Vector3(x + (hyp * cos15), y + (cos75 * hyp), z - halfh);
        A2 = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z + halfh);
        B2 = new Vector3(x - (hyp * cos75), y - (cos15 * hyp), z +halfh);
        C2 = new Vector3(x + (hyp * cos15), y + (cos75 * hyp), z + halfh);
        
        this.setGameObject(new Face[]{
            new Face(A, B, C), //bottom
            new Face(A2, B2, C2), //top
            new Face(A, B, B2), //1st triangle 1st side
            new Face(A, A2, B2), //2nd triangle 1st side
            new Face(A, C, C2), //1st triangle 2nd side
            new Face(A, A2, C2), //2nd triangle 2nd side
            new Face(C, C2, B2), //1st triangle 3rd side
            new Face(C, B, B2) }, origin //2nd triangle 3rd side
        );
        
    }
}
