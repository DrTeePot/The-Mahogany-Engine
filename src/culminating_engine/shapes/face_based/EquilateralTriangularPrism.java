/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.face_based;

import culminating_engine.Vector3;

/**
 * Allows users to easily create an equilateral triangle based prism shaped GameObject. 
 * Adds small functionality related to triangular prisms. 
 * @author tristan
 */
public class EquilateralTriangularPrism extends GameObject{
    
    private double triangularLength; //length between two vertices on the triangular face
    private double prismHeight; //distance between triangle faces
    
    /**
     * Creates an prism that has an equilateral triangular base.<br>
     * pre:none <br>
     * post: A GameObject with an equilateral triangle base that has a specified side length, 
     * extruded to a certain height, is created. The specified origin will occur in the middle of the shape.
     * @param origin - the point of rotation/scaling located in the center of the shape.
     * @param sideLength - the length of the sides of the equilateral triangle.
     * @param height - the height of the prism. 
     */
    public EquilateralTriangularPrism(Vector3 origin, double sideLength, double height){
        super(); // create an empty game object to start
        final double PI = Math.PI; //minimize calls to Math
        
        //The points that will make up the shape.
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 A2;
        final Vector3 B2;
        final Vector3 C2;
        
        triangularLength = sideLength;
        prismHeight = height;
        
        //Common variables, used to minimize code.
        double hyp = sideLength / (2 * Math.cos(PI/6));//the distance from the origin to a point of the equilateral triangle.
        double halfh = height / 2; //half the height
        
        //trig ratios to minimize calls to trig functions. 
        double cos45 = Math.cos(PI/4);
        double cos15 = Math.cos(PI/12);
        double cos75 = Math.cos((PI * 7) / 12);
        
        //the origin's components
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        //Assign values to the points.
        /*
         * When placed in a square, the points of an equilateral triangle can touch one vertex of the square, 
         * and the two sides that are not adjacent to this vertex. The point A is the point on the vertex, and
         * when a right angle triangle is formed with the hyp (see above) it has angles of 45 degrees. B and C are
         * the points that occur on the non-adjacent edges of the square. 
         */
        
        //Bottom of the prism
        A = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z - halfh);
        B = new Vector3(x + (hyp * cos75), y - (cos15 * hyp), z - halfh);
        C = new Vector3(x + (hyp * cos15), y - (cos75 * hyp), z - halfh);
        //Top of the prism
        A2 = new Vector3(x - (hyp * cos45), y + (cos45 * hyp), z + halfh);
        B2 = new Vector3(x + (hyp * cos75), y - (cos15 * hyp), z + halfh);
        C2 = new Vector3(x + (hyp * cos15), y - (cos75 * hyp), z + halfh);
        
        //set the GameObject to have the points defined above. 
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
    
    /**
     * Returns the distance between two vertices on a triangular face of the object. <br>
     * pre: none <br>
     * post: The length is returned to the client.
     * @return double triangularLength
     */
    public double getTriangularLength(){
        return triangularLength;
    }
    
    /**
     * Returns the distance between the two triangle faces of the object. <br>
     * pre: none <br>
     * post: The height is returned to the client.
     * @return double prismHeight
     */
    public double getHeight(){
        return prismHeight;
    }
}
