/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

import culminating_engine.shapes.Camera;
import java.awt.Color;
import java.awt.Graphics2D;
import culminating_engine.shapes.GameObject;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Aidan
 */
public class Renderer {
    
    private ArrayList<GameObject> gameObjects = new ArrayList(); //list of all objects to be rendered
    private Camera camera; //the camera used to render
    
    private int screenSize; 
    private int outWidth;
    private int outHeight;
    
    private final double PI = Math.PI;
    
    /**
     * Create a renderer object, which is a factory that has the ability to 
     *      render a scene when given a GameObjects and a camera object
     * pre: none
     * post: A renderer object has been created
     * @param o - an array of GameObjects (to possibly be rendered)
     * @param c - a camera object
     * @param w - the width of the screen
     * @param h - the height of the screen
     */
    public Renderer(GameObject[] o, Camera c, int w, int h){
        camera = c;
        
        outWidth = w;
        outHeight = h;
        screenSize = (int)(Math.max(w, h) * Math.sqrt(2));
        
        gameObjects.addAll(Arrays.asList(o));
    }
    
    /**
     * Create a renderer object, which is a factory that has the ability to 
     *      render a scene when given a GameObjects and a camera object
     * pre: none
     * post: A renderer object has been created
     * @param c - a camera object
     * @param s - the size of the screen (measured diagonally)
     */
    public Renderer(Camera c, int w, int h){
        camera = c;
        outWidth = w;
        outHeight = h;
        screenSize = (int)(Math.max(w, h) * Math.sqrt(2));    
    }
    
    /**
     * Add an ArrayList of objects to the list of objects to be rendered
     * pre: none
     * post: objects have been added to the list
     * @param g - the ArrayList of objects to be added
     */
    public void addObjects(ArrayList<GameObject> g){
        gameObjects.addAll(g);
    }
    
    /**
     * Add a single GameObject to the list of objects to be rendered
     * pre: none
     * post: object has been added to the list
     * @param g - the object to be added
     */
    public void addObject(GameObject g){
        gameObjects.add(g);
    }
    
    /**
     * Returns true if a point is within the field of view, false otherwise
     * pre: none
     * post: true has been returned if the point is in FOV, false otherwise
     * @param v - The point to be tested
     * @return boolean - true if point is in FOV, else false
     */
    public boolean pointInFOV(Vector3 v){
        Vector3 d1 = camera.getOrientation()[0]; 
        Vector3 d2 = Vector3.subtractVectors(camera.getPositionVector(), v);
        
        double angle = d1.getAngle(d2);
        
        //if the angle between the direction the camera is pointed
        //  and the point to be rendered is less than the field of view, 
        //  thenthe point is in the field of view
        if ((camera.getFOV() >= angle)&&(angle >= -camera.getFOV())){
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

        Vector3[] camOrientation = camera.getOrientation(); //the camera's orientation
        Vector3 d2 = Vector3.subtractVectors(camera.getPositionVector(), v); //the points position relative to the camera
        
        Vector3 hd2 = d2.projectionOn(camOrientation[2]); //point projected on to the camera's Z-axis
        Vector3 wd2 = d2.projectionOn(camOrientation[1]); //point projected on to the camera's Y-axis
        
        double vectorMagnitude = d2.getMagnitude();
        
        double width, height;
        
        /**
         * the following checks whether the above projections are parallel or antiparallel,
         *      to the axes they were projected onto. If they are antiparallel, then the resulting
         *      magnitude of the projection needs to be multiplied by negative 1.
         */
        if ((hd2.getComponents()[0] * camOrientation[2].getComponents()[0] < 0)||
                (hd2.getComponents()[1] * camOrientation[2].getComponents()[1] < 0)||
                (hd2.getComponents()[2] * camOrientation[2].getComponents()[2] < 0)){
            height = hd2.getMagnitude() * -1;
        } else {
            height = hd2.getMagnitude();
        }
        if ((wd2.getComponents()[0] * camOrientation[1].getComponents()[0] < 0)||
                (wd2.getComponents()[1] * camOrientation[1].getComponents()[1] < 0)||
                (wd2.getComponents()[2] * camOrientation[1].getComponents()[2] < 0)){
            width = wd2.getMagnitude() * -1;
        } else {
            width = wd2.getMagnitude();
        }
        
        //properly scales and positions the coordinates
        xCoordinate = (screenSize/2) * (width/(vectorMagnitude*camera.getFOV()));
        yCoordinate = (screenSize/2) * (height/(vectorMagnitude*camera.getFOV()));
       
        return new Point((int)xCoordinate, (int)yCoordinate);
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
    
    /**
     * Given an a, b, and c value, return the solution(s) to the quadratic formula
     * pre: none
     * post: solutions have been returned as double[]
     * @param a - the a value
     * @param b - the b value
     * @param c - the c value
     * @return - a double[] of the solutions
     */
    private double[] quadraticFormula(double a, double b, double c){
        double [] x = new double[2];
        
        x[0] = ( (-(b)) + Math.sqrt(( b * b) - (4 * a * c)) )  / (2 * a);
        x[1] = ( (-(b)) - Math.sqrt(( b * b) - (4 * a * c)) )  / (2 * a);
               
        return x;
    }
    
 
    // Return true if c is between a and b.
    private boolean isBetween(double a, double b, double c) {
        return b >= a ? c >= a && c <= b : c >= b && c <= a;
    }
    
    /**
     * Given a line, the following function and returns a line that has every
     *      single point in the field of view (if any). The field of view is 
     *      defined by an infinite cone, whose splay is equal to the angle of 
     *      the field of view. Thus, by finding the intersection(s) of the 
     *      given line and that infinite cone, a line entirely inside of the
     *      field of view can be returned. 
     *      For more information about infinite cones, go to:
     *      http://yayamoose.homelinux.com/~ripper/mirrors/www.magic-software.com/Documentation/IntersectLineCone.pdf
     * pre: none
     * post: A line with all points in the FOV is returned
     * @param p1 - the first point defining the given line
     * @param p2 - the second point defining the given line
     * @return - a line contained by the FOV
     */
    private Vector3[] makeAllPointsInFOV(Vector3 p1, Vector3 p2){      
        
        double xo = p1.getMagnitude_componentX();
        double yo = p1.getMagnitude_componentY();
        double zo = p1.getMagnitude_componentZ();
        
        double xd = p1.getMagnitude_componentX() - p2.getMagnitude_componentX();
        double yd = p1.getMagnitude_componentY() - p2.getMagnitude_componentY();
        double zd = p1.getMagnitude_componentZ() - p2.getMagnitude_componentZ();
        
        double xa = camera.getOrientation()[0].getMagnitude_componentX();
        double ya = camera.getOrientation()[0].getMagnitude_componentY();
        double za = camera.getOrientation()[0].getMagnitude_componentZ();
        
        double xv = camera.getPositionVector().getMagnitude_componentX();
        double yv = camera.getPositionVector().getMagnitude_componentY();
        double zv = camera.getPositionVector().getMagnitude_componentZ();
        
        double FOV = camera.getFOV();                
        
        double a, b, c;
        a = -2 * Math.pow(xa,2) * Math.pow(xd,2) + Math.cos(2*FOV) 
                * Math.pow(xd,2) + Math.pow(xd,2) - 4*xa*ya*yd*xd 
                - 4*xa*za*zd*xd - 2*Math.pow(ya,2) * Math.pow(yd,2) 
                + Math.cos(2*FOV) * Math.pow(yd,2) + Math.pow(yd,2) 
                - 2*Math.pow(za,2) * Math.pow(zd,2) + Math.cos(2*FOV) 
                * Math.pow(zd,2) + Math.pow(zd,2) - 4*ya*yd*za*zd;

        
        b = 4*xv*xd*Math.pow(xa,2) - 4*xo*xd*Math.pow(xa,2) + 4*xd*ya*yv*xa
                - 4*xd*ya*yo*xa + 4*xv*ya*yd*xa - 4*xo*ya*yd*xa + 4*xd*za*zv*xa
                - 4*xd*za*zo*xa + 4*xv*za*zd*xa - 4*xo*za*zd*xa 
                - 2*Math.cos(2*FOV)*xv*xd - 2*xv*xd + 2*Math.cos(2*FOV)*xo*xd
                + 2*xo*xd + 4*Math.pow(ya,2)*yv*yd - 2*Math.cos(2*FOV)*yv*yd
                - 2*yv*yd - 4*Math.pow(ya,2)*yo*yd + 2*Math.cos(2*FOV)*yo*yd
                + 2*yo*yd + 4*ya*yd*za*zv - 4*ya*yd*za*zo + 4*ya*yv*za*zd
                - 4*ya*yo*za*zd + 4*Math.pow(za,2)*zv*zd - 2*Math.cos(2*FOV)*zv*zd
                - 2*zv*zd - 4*Math.pow(za,2)*zo*zd + 2*Math.cos(2*FOV)*zo*zd 
                + 2*zo*zd;

                
        c = -2*Math.pow(xv,2)*Math.pow(xa,2) - 2*Math.pow(xo,2)*Math.pow(xa,2)
                + 4*xv*xo*Math.pow(xa,2) - 4*xv*ya*yv*xa + 4*xo*ya*yv*xa 
                + 4*xv*ya*yo*xa - 4*xo*ya*yo*xa - 4*xv*za*zv*xa + 4*xo*za*zv*xa
                + 4*xv*za*zo*xa - 4*xo*za*zo*xa + Math.cos(2*FOV)*Math.pow(xv,2)
                + Math.pow(xv,2) + Math.cos(2*FOV)*Math.pow(xo,2) 
                + Math.pow(xo,2) - 2*Math.pow(ya,2)*Math.pow(yv,2) 
                + Math.cos(2*FOV)*Math.pow(yv,2) + Math.pow(yv,2) 
                - 2*Math.pow(ya,2)*Math.pow(yo,2) + Math.cos(2*FOV)*Math.pow(yo,2)
                + Math.pow(yo,2) - 2*Math.pow(za,2)*Math.pow(zv,2) 
                + Math.cos(2*FOV)*Math.pow(zv,2) + Math.pow(zv,2) 
                - 2*Math.pow(za,2)*Math.pow(zo,2) + Math.cos(2*FOV)*Math.pow(zo,2)
                + Math.pow(zo,2) - 2*Math.cos(2*FOV)*xv*xo - 2*xv*xo 
                + 4*Math.pow(ya,2)*yv*yo - 2*Math.cos(2*FOV)*yv*yo - 2*yv*yo
                - 4*ya*yv*za*zv + 4*ya*yo*za*zv + 4*ya*yv*za*zo - 4*ya*yo*za*zo
                + 4*Math.pow(za,2)*zv*zo - 2*Math.cos(2*FOV)*zv*zo - 2*zv*zo;

        
        double[] t = new double[2];
        if (a != 0){ //Full line has two intersections
            t = quadraticFormula(a, b, c);
        } else { //Full line only has one intersection
            t[0] = (-(c))/(b);
            t[1] = t[0];
        }
                
        double x1, y1, z1, x2, y2, z2;
        Vector3 v1, v2;
        v1 = null;
        v2 = null;
        
        if (xa*(xo+t[0]*xd-xv) + ya*(yo+t[0]*yd-yv) + za*(zo+t[0]*zd-zv) >= 0){
            x1 = xo + t[0]*xd;
            y1 = yo + t[0]*yd;
            z1 = zo + t[0]*zd;
            
            if ((isBetween(p1.getMagnitude_componentX(), p2.getMagnitude_componentX(), x1)) &&
                    (isBetween(p1.getMagnitude_componentY(), p2.getMagnitude_componentY(), y1)) &&
                    (isBetween(p1.getMagnitude_componentZ(), p2.getMagnitude_componentZ(), z1))){
                v1 = new Vector3(x1, y1, z1);
            }
        } 
        
        if (xa*(xo+t[1]*xd-xv) + ya*(yo+t[1]*yd-yv) + za*(zo+t[1]*zd-zv) >= 0){
            x2 = xo + t[1]*xd;
            y2 = yo + t[1]*yd;
            z2 = zo + t[1]*zd;
            if ((isBetween(p1.getMagnitude_componentX(), p2.getMagnitude_componentX(), x2)) &&
                    (isBetween(p1.getMagnitude_componentY(), p2.getMagnitude_componentY(), y2)) &&
                    (isBetween(p1.getMagnitude_componentZ(), p2.getMagnitude_componentZ(), z2))){
                v2 = new Vector3(x2, y2, z2);
            }
        }
        
        
        Vector3 rV1, rV2;
        if ((pointInFOV(p1)) && (pointInFOV(p2))){ //Both points in FOV
            rV1 = p1;
            rV2 = p2;
        } else if ((pointInFOV(p1))^(pointInFOV(p2))){ //one point in FOV 
            if (pointInFOV(p1)){
                rV1 = p1;
            }else{
                rV1 = p2;
            }
            if (v1 != null){
                rV2 = v1;
            } else {
                rV2 = v2;
            }
        } else if ((v1 != null) && (v2 != null)){//no point in FOV, 2 intersections
            rV1 = v1;
            rV2 = v2;
        } else {//no point in FOV, no intersection
            rV1 = null;
            rV2 = null;
        }
        
        Vector3[] returnPoints = new Vector3[2];
        returnPoints[0] = rV1;
        returnPoints[1] = rV2;
        
        return returnPoints;
        
    }
    
    
    /**
     * Given a line in R3, uses makeAllPointsInFOV to and V3toV2 to return a 2D
     *      line object.
     * pre: none
     * post: a line object (array of points, 2 deep) has been returned
     * @param p1 - the first point defining the given line
     * @param p2 - the second point defining the given line
     * @return - a line object (array of points, 2 deep)
     */
    private Point[] lineTo2D(Vector3 p1, Vector3 p2){
        Vector3[] line3D;
        Point[] line2D = new Point[2];
        
            line3D = makeAllPointsInFOV(p1,p2);
            
            if ((line3D[0] != null)&&(line3D[1] != null)){
                line2D[0] = V3toV2(line3D[0]);
                line2D[1] = V3toV2(line3D[1]);
            }else{
                line2D = null;
            }
        
        return line2D;
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
                
        wire2D.setColor(Color.BLACK);
        
        for (int gameObject = 0; gameObject < gameObjects.size(); gameObject++){
            for (int face = 0; face < gameObjects.get(gameObject).getShape().length;face++){
                
                Vector3 p1 = new Vector3(gameObjects.get(gameObject).getFace(face).getPoint(0));
                Vector3 p2 = new Vector3(gameObjects.get(gameObject).getFace(face).getPoint(1));
                Vector3 p3 = new Vector3(gameObjects.get(gameObject).getFace(face).getPoint(2));
                
                Point[] line_p1_p2;
                Point[] line_p2_p3;
                Point[] line_p3_p1;
                       
                line_p1_p2 = lineTo2D(p1, p2);
                line_p2_p3 = lineTo2D(p2, p3);
                line_p3_p1 = lineTo2D(p3, p1);
                
                if (line_p1_p2 != null){
                    line_p1_p2[0].x += screenSize/2;
                    line_p1_p2[0].y += screenSize /2;
                    line_p1_p2[1].x += screenSize/2;
                    line_p1_p2[1].y += screenSize /2;
                }
                if (line_p2_p3 != null){
                    line_p2_p3[0].x += screenSize/2;
                    line_p2_p3[0].y += screenSize /2;
                    line_p2_p3[1].x += screenSize/2;
                    line_p2_p3[1].y += screenSize /2;
                }
                if (line_p3_p1 != null){
                    line_p3_p1[0].x += screenSize/2;
                    line_p3_p1[0].y += screenSize /2;
                    line_p3_p1[1].x += screenSize/2;
                    line_p3_p1[1].y += screenSize /2;
                }
                        
                if (line_p1_p2 != null){
                    wire2D.drawLine(line_p1_p2[0].x, line_p1_p2[0].y, 
                            line_p1_p2[1].x, line_p1_p2[1].y);
                }
                if (line_p2_p3 != null){
                    wire2D.drawLine(line_p2_p3[0].x, line_p2_p3[0].y, 
                            line_p2_p3[1].x, line_p2_p3[1].y);
                }
                if (line_p3_p1 != null){
                    wire2D.drawLine(line_p3_p1[0].x, line_p3_p1[0].y, 
                            line_p3_p1[1].x, line_p3_p1[1].y);
                }
            
            }
        }        
        wire2D.dispose();
        
        output = output.getSubimage((screenSize/2) - (outWidth/2), 
                                    (screenSize/2) - (outHeight/2), 
                                    outWidth, outHeight);        
        
        return flipVertically(output);
    }
}
    
