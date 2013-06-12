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
public class RectangularPyramid extends GameObject{
    private double length;
    private double width;
    private double height;
    private double sideLength;
    
    RectangularPyramid(Vector3 origin, double l, double w, double h){
        super();
        length = l;
        width = w;
        height = h;
        
        double x = origin.getComponents()[0];
        double y = origin.getComponents()[1];
        double z = origin.getComponents()[2];
        
        final Vector3 A;
        final Vector3 B;
        final Vector3 C;
        final Vector3 D;
        final Vector3 E; //point 
        
        double tetrahedralAngle = Math.acos(1/3);
        double lambda = (180 - tetrahedralAngle)/2; //angle between origin - A - D
        double supplementTetrahedralAngle = (180 - tetrahedralAngle);
        
        sideLength = height / ((180 - Math.acos(1/3)/2));
        double lowerHeight = (sideLength * Math.cos(supplementTetrahedralAngle)) / (2 * Math.cos(lambda));
    }
}
