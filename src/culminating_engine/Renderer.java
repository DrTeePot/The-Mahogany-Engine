/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.Face;
import java.awt.Color;
import java.awt.Graphics2D;
import culminating_engine.shapes.GameObject;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aidan
 */
public class Renderer {
    
    private ArrayList<GameObject> gameObjects = new ArrayList();
    private Camera camera;
    private double screenSize;
    
    private Vector3 directionV = new Vector3(1,0,0); //All is rendered as if the
                                                     //camera's direction vector 
                                                     //were this, and everything
                                                     //else was translated accordingly
    
    
    /**
     * Create a renderer object, which is a factory that has the ability to 
     *      render a scene when given a game object and a camera object
     * pre: none
     * post: A renderer object has been created
     * @param o - an array of GameObjects (to possibly be rendered)
     * @param c - a camera object
     * @param s - the size of the screen (measured diagonally)
     */
    Renderer(GameObject[] o, Camera c, double s){
        gameObjects.addAll(rotateObjectsRelatively(o));
        camera = c;
        screenSize = s;
    }
    
    /**
     * Returns true if a point is within the field of view, false otherwise
     * pre: none
     * post: true has been returned if the point is in FOV, false otherwise
     * @param v - The point to be tested
     * @return boolean - true if point is in FOV, else false
     */
    public boolean pointInFOV(Vector3 v){
        Vector3 d1 = camera.getDirectionVector(); 
        Vector3 d2 = Vector3.subtractVectors(camera.getPositionVector(),v);
        
        double angle = d1.getAngle(d2);
        
        if ((camera.getFOV() > angle)&&(angle > -camera.getFOV())){
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
     * Given a Vector3, returns the 2d coordinates, as if the point 0,0 were the screen center
     * pre: none
     * post: the point 's rendered coordinates have been returned
     * @param v - The Vector3 to be converted
     * @return  Point Object - The Vector3 rendered coordinates
     *                       - Null returned if point not in FOV
     */
    public Point V3toV2(Vector3 v){
        double xCoordinate, yCoordinate;
        double xyAngle , xzAngle;
        
        if (pointInFOV(v)){
//            Vector3 d1 = camera.getDirectionVector();
            Vector3 d1 = directionV;
            Vector3 d2 = Vector3.subtractVectors(camera.getPositionVector(), v);

            Vector3 d1xy = new Vector3(d1.getMagnitude_componentX(), d1.getMagnitude_componentY(), 0);
            Vector3 d2xy = new Vector3(d2.getMagnitude_componentX(), d2.getMagnitude_componentY(), 0);
            Vector3 d1xz = new Vector3(d1.getMagnitude_componentX(), 0, d1.getMagnitude_componentZ());
            Vector3 d2xz = new Vector3(d2.getMagnitude_componentX(), 0, d2.getMagnitude_componentZ());
            
            if (d2.getMagnitude_componentY() < 0){
                xyAngle = d1xy.getAngle(d2xy) * -1;
            } else{
                xyAngle = d1xy.getAngle(d2xy);
            }
            if (d2.getMagnitude_componentZ() <0){
                xzAngle = d1xz.getAngle(d2xz) * -1;
            } else {
                xzAngle = d1xz.getAngle(d2xz);
            }   
            
            xCoordinate = (screenSize/2) * (xyAngle/camera.getFOV());
            yCoordinate = (screenSize/2) * (xzAngle/camera.getFOV());
            
            return new Point((int)xCoordinate, (int)yCoordinate);
        } else {
            System.out.println("Not in FOV");
            return null;
        }
    }
    
    /**
     * Returns a buffered image, flipped vertically
     * pre: none
     * post: The vertically flipped buffered image has been returned
     * @param img - the image to be flipped
     * @return flipped - the flipped buffered image
     */
    private BufferedImage flipVertically(BufferedImage img) {
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage flipped = new BufferedImage(w, h, img.getColorModel().getTransparency());  
        Graphics2D g = flipped.createGraphics();  
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);  
        g.dispose();  

        return flipped;
    }
    
    private Polygon threePointsInFovTriangle(Face f){
        Point p;
        int[] xCoordinates = new int[3];
        int[] yCoordinates = new int[3];
        
        for (int vector = 0; vector < 3; vector++){
            
            p = V3toV2(f.getPoint(vector));
            xCoordinates[vector] = p.x + (int)screenSize/2;
            yCoordinates[vector] = p.y + (int)screenSize/2;
        
        }
        
        return new Polygon(xCoordinates, yCoordinates, 3);
    }
    
    private Polygon twoPointsInFovTriangle(Face f){
        return null;
    }
    
    private Polygon onePointInFovTriangle(Face f){
        Point p;
        int[] xCoordinates = new int[3];
        int[] yCoordinates = new int[3];
        
        Vector3 pIn = null;
        
        for (int vector = 0; vector < 3; vector++){
            
            if (pointInFOV(f.getPoint(vector))){
                pIn = f.getPoint(vector);
                break;
            }
        }
        
        for (int vector = 0; vector < 3; vector++){
            if (pointInFOV(f.getPoint(vector))){
                p = V3toV2(f.getPoint(vector));
                xCoordinates[vector] = p.x + (int)screenSize/2;
                yCoordinates[vector] = p.y + (int)screenSize/2;
            } else {
                
            }
                
        }
        
        return null;
    }
    
    /**
     * Returns a buffered image of the scene, in which the outline to every Face
     *      object is shown
     * pre: Renderer must have game objects, camera, and screen size
     * post: A buffered image of the rendered WireFrame is returned
     * @return Buffered Image - The rendered WireFrame
     */
    public BufferedImage wireFrameRender(){
        BufferedImage output = new BufferedImage((int)screenSize, (int)screenSize, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D wire2D = output.createGraphics();
                
        for (int gameObject = 0; gameObject < gameObjects.size(); gameObject++){
            for (int face = 0; face < gameObjects.get(gameObject).getShape().length;face++){
                
                int numberOfPointsInFOV = 0;
                for (int vector = 0; vector < 3; vector++){
                    if (pointInFOV(gameObjects.get(gameObject).getFace(face).getPoint(vector))){
                        numberOfPointsInFOV ++;
                    }
                }
                
                Polygon triangle = null;
                
                switch (numberOfPointsInFOV){
                        case 1: triangle = onePointInFovTriangle(gameObjects.get(gameObject).getFace(face));
                        case 2: triangle = twoPointsInFovTriangle(gameObjects.get(gameObject).getFace(face));
                        case 3: triangle = threePointsInFovTriangle(gameObjects.get(gameObject).getFace(face));
                        default: break;
                }
                                
                wire2D.setColor(Color.BLACK);
                wire2D.drawPolygon(triangle);
            
            }
        }
        
        wire2D.dispose();
        return flipVertically(output);
    }
    
    private ArrayList<GameObject> rotateObjectsRelatively(GameObject[] o){
        ArrayList<GameObject> a = new ArrayList(); 
        
        double xyAngle, xzAngle;
        Vector3 cameraD = camera.getDirectionVector();

        Vector3 CameraDxy = new Vector3(cameraD.getMagnitude_componentX(), cameraD.getMagnitude_componentY(), 0);
        Vector3 directionVxy = new Vector3(directionV.getMagnitude_componentX(), directionV.getMagnitude_componentY(), 0);
        Vector3 CameraDxz = new Vector3(cameraD.getMagnitude_componentX(), 0, cameraD.getMagnitude_componentZ());
        Vector3 directionVxz = new Vector3(directionV.getMagnitude_componentX(), 0, directionV.getMagnitude_componentZ());

        if (cameraD.getMagnitude_componentY() < 0){
            xyAngle = directionVxy.getAngle(CameraDxy) * -1;
        } else{
            xyAngle = directionVxy.getAngle(CameraDxy);
        }
        if (cameraD.getMagnitude_componentZ() <0){
            xzAngle = directionVxz.getAngle(CameraDxz) * -1;
        } else {
            xzAngle = directionVxz.getAngle(CameraDxz);
        }    
                
        for (GameObject g : o){
            GameObject b = new GameObject(o);
            b.rotateAroundPoint(0, -xzAngle ,-xyAngle , camera.getPositionVector());
            a.add(b);
        }
        
        return a;
    }
    
    public BufferedImage box(){
        BufferedImage output = new BufferedImage((int)screenSize, (int)screenSize, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D wire2D = output.createGraphics();
        
        wire2D.setBackground(Color.WHITE );
        wire2D.fillRect(100, 100, 200, 200);
        wire2D.dispose();
        
        return output;
    }
}