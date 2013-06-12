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
public class EquilateralTriangularPyramid extends GameObject{
    private double sideLength;
    private double tetrahedralAngle;
    
    public EquilateralTriangularPyramid(Vector3 origin, double height){
        super();
        final double PI = Math.PI;
        
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        tetrahedralAngle = Math.acos(1/3);
        double lambda = (180 - tetrahedralAngle)/2; //angle between origin - A - D
        double supplementTetrahedralAngle = (180 - tetrahedralAngle);
        
        sideLength = height / ((180 - Math.acos(1/3)/2));
        double lowerHeight = (sideLength * Math.cos(supplementTetrahedralAngle)) / (2 * Math.cos(lambda));
        
        double hyp = sideLength / (2 * Math.cos(PI/6));
        double cos45 = Math.cos(PI/4);
        double cos15 = Math.cos(PI/12);
        double cos75 = Math.cos((PI * 7) / 12);
        
        A = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z - lowerHeight);
        B = new Vector3(x - (hyp * cos75), y - (cos15 * hyp), z - lowerHeight);
        C = new Vector3(x + (hyp * cos15), y + (cos75 * hyp), z - lowerHeight);
        D = new Vector3(x, y, z + (height - lowerHeight));
        
        this.setGameObject(new Face[]{
            new Face(A,B,C),
            new Face(A,B,D),
            new Face(A,C,D),
            new Face(B,C,D)}, origin);
    }
    
    public double getSideLength(){
        return sideLength;
    }
    
    public double getTetrahedralAngle(){
        return tetrahedralAngle;
    }
}
