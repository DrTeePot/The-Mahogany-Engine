/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.line_based;
import culminating_engine.Renderbody;
import culminating_engine.Vector3;
import java.awt.Color;
/**
 *
 * @author tristan
 */
public class RectangularPrism extends Renderbody{
    private double baseWidth; //the distance across the front of the base
    private double baseDepth; //the distance back the base extends
    private double prismHeight; //the amound the base extends upwards
    
    public RectangularPrism(double width, double height, double depth, Color c){
        super(c);
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
        
        A = new Vector3(halfd, -halfw, -halfh);
        A2 = new Vector3(-halfd, -halfw, -halfh);
        B = new Vector3(halfd, halfw, -halfh);
        B2 = new Vector3(-halfd, halfw, -halfh);
        C = new Vector3(halfd, halfw, halfh);
        C2 = new Vector3(-halfd, halfw, halfh);
        D = new Vector3(halfd, -halfw, halfh);
        D2 = new Vector3(-halfd, -halfw, halfh);
        
        this.setShapeLines(new Line[] {
            new Line(A, B), //front face
            new Line(B, C),
            new Line(C, D),
            new Line(D, A),
            
            new Line(A2, B2), //back face
            new Line(B2, C2),
            new Line(C2, D2),
            new Line(D2, A2),
            
            new Line(A, A2), //connectors
            new Line(B, B2),
            new Line(C, C2),
            new Line(D, D2),
            
        });
    }
    
    public RectangularPrism(double width, double height, double depth){
        super(Color.WHITE);
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
        
        A = new Vector3(halfd, -halfw, -halfh);
        A2 = new Vector3(-halfd, -halfw, -halfh);
        B = new Vector3(halfd, halfw, -halfh);
        B2 = new Vector3(-halfd, halfw, -halfh);
        C = new Vector3(halfd, halfw, halfh);
        C2 = new Vector3(-halfd, halfw, halfh);
        D = new Vector3(halfd, -halfw, halfh);
        D2 = new Vector3(-halfd, -halfw, halfh);
        
        this.setShapeLines(new Line[] {
            new Line(A, B), //front face
            new Line(B, C),
            new Line(C, D),
            new Line(D, A),
            
            new Line(A2, B2), //back face
            new Line(B2, C2),
            new Line(C2, D2),
            new Line(D2, A2),
            
            new Line(A, A2), //connectors
            new Line(B, B2),
            new Line(C, C2),
            new Line(D, D2),
            
        });
    }
    
    public double getPrismHeight(){
        return prismHeight;
    }
    
    public double getBaseWidth(){
        return baseWidth;
    }
    
    public double getBaseDepth(){
        return baseDepth;
    }
}
