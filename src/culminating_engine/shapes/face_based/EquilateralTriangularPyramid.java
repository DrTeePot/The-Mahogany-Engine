/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.face_based;

import culminating_engine.Vector3;

/**
 * Allows users to easily create a equilateral triangular based pyramid shaped GameObject. Adds small 
 * functionality related to equilateral triangle pyramids. 
 * @author tristan
 */
public class EquilateralTriangularPyramid extends GameObject{
    
    private double sideLength; //the side length of the triangles in the pyramid
    private final double tetrahedralAngle; //the tetrahedral angle. 
    
    /**
     * Creates a GameObject in the shape of a Equilateral Triangular Pyramid. <br>
     * pre: none <br>
     * post: a triangular pyramid is created where all side lengths are equal. 
     * @param origin - The center of the object.
     * @param height - The height of the pyramid.
     */
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
        
        sideLength = height / Math.cos(lambda);
        double lowerHeight = (0.5 * sideLength * Math.cos(lambda)) * Math.cos(supplementTetrahedralAngle);
        
        double hyp = sideLength / (2 * Math.cos(PI/6));
        double cos45 = Math.cos(PI/4);
        double cos15 = Math.cos(PI/12);
        double cos75 = Math.cos((PI * 7) / 12);
        
        A = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z + lowerHeight);
        B = new Vector3(x + (hyp * cos75), y - (cos15 * hyp), z + lowerHeight);
        C = new Vector3(x + (hyp * cos15), y - (cos75 * hyp), z + lowerHeight);
        D = new Vector3(x, y, z + height - lowerHeight);
        
        this.setGameObject(new Face[]{
            new Face(A,B,C),
            new Face(A,B,D),
            new Face(A,C,D),
            new Face(B,C,D)}, origin);
    }
    
    /**
     * Returns the side length of the pyramid. <br>
     * pre: none <br>
     * post: The double representing the side length is returned. 
     * @return double sideLength
     */
    public double getSideLength(){
        return sideLength;
    }
    
    /**
     * Returns the tetrahedral angle, a constant angle representing the angle between two vertices
     * and the center (origin) of the tetrahedral. <br>
     * pre: none <br>
     * post: The tetrahedral angle is returned. 
     * @return the arc cos(1/3)
     */
    public double getTetrahedralAngle(){
        return tetrahedralAngle;
    }
}
