/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine;

/**
 *
 * @author tristan
 */
public class Vector3 {
    
    private double x;
    private double y;
    private double z;
    
    /**
     * Create a Vector3 object that stretches from the origin to point (i, j, k). This is a position vector. <br>
     * pre: none <br>
     * post: A vector object is created
     * @param i - the x position
     * @param j - the y position
     * @param k - the z position
     */
    public Vector3(double i, double j, double k){
        x= i;
        y = j;
        z = k;
    }
    
    /**
     * Create a Vector3 object that is equivalent to the vector s. <br>
     * pre: none <br>
     * post: A Vector3 object is created
     * @param s - a Vector3 object representing a vector.
     */
    public Vector3(Vector3 s){
        x = s.getMagnitude_componentX();
        y = s.getMagnitude_componentY();
        z = s.getMagnitude_componentZ();
    }
    
    //<editor-fold desc="Getters and setters">
    
    /**
     * Sets the vector to be equal to the vector defined by the parameter s <br>
     * pre: none <br>
     * post: The vector's definition is changed to that of the vector s
     * @param s - the vector to set this vector equal to
     */
    public void setVector(Vector3 s){
        double[] aS = s.getComponents();
        x = aS[0];
        y = aS[1];
        z = aS[2];
    }
    
    /**
     * Sets the vector to be equal to the vector defined by the vector (i,j,k) <br>
     * pre: none <br>
     * post: The vector's definition is changed to that of the vector (i,j,k)
     * @param i - the x component of the vector 
     * @param j - the y component of the vector
     * @param k - the z component of the vector
     */
    public void setVector(double i, double j, double k){
        x = i;
        y = j;
        z = k;
    }
    
    /**
     * Returns the definition of the vector to the client as an array of doubles (of length 3) <br>
     * pre: none <br>
     * post: The three components of the vector are returned
     * @return double[] l = {x,y,z}
     */
    public double[] getComponents(){
        double[] l = {x,y,z};
        return (l);
    }
    
    /**
     * Returns the x component of the vector. <br>
     * pre: none <br>
     * post: the vector that represents the x component is returned.
     * @return Vector3(x,0,0)
     */
    public Vector3 getComponent_X(){
        return(new Vector3(x,0,0));
    }
    
    /**
     * Returns the y component of the vector. <br>
     * pre: none <br>
     * post: the vector that represents the y component is returned.
     * @return Vector3(0,y,0)
     */
    public Vector3 getComponent_Y(){
        return(new Vector3(0,y,0));
    }
    
    /**
     * Returns the z component of the vector. <br>
     * pre: none <br>
     * post: the vector that represents the z component is returned.
     * @return Vector3(0,0,z)
     */
    public Vector3 getComponent_Z(){
        return(new Vector3(0,0,z));
    }
    
    /**
     * Returns the magnitude of the x component of the vector. <br>
     * pre: none <br>
     * post: The magnitude of the x component is returned as a double.
     * @return double x
     */
    public double getMagnitude_componentX(){
        return(x);
    }
    
    /**
     * Returns the magnitude of the y component of the vector. <br>
     * pre: none <br>
     * post: The magnitude of the y component is returned as a double.
     * @return double y
     */
    public double getMagnitude_componentY(){
        return(y);
    }
    
    /**
     * Returns the magnitude of the z component of the vector. <br>
     * pre: none <br>
     * post: The magnitude of the z component is returned as a double.
     * @return double z
     */
    public double getMagnitude_componentZ(){
        return(z);
    }
    
    //</editor-fold>
    
    //<editor-fold desc="calculated manipulations and returns">
    
    /**
     * Add a vector to this vector. <br>
     * pre: none <br>
     * post: The vector is altered to be the vector + the vector s.
     * @param s 
     */
    public void addVector(Vector3 s){
        double[] aS = s.getComponents();
        x += aS[0];
        y += aS[1];
        z += aS[2];
    }
    
    /**
     * Add a vector to this vector. <br>
     * pre: none <br>
     * post: The vector is altered to be the vector + the vector defined by (i, j, k)
     * @param i - the x component of the addition vector
     * @param j - the y component of the addition vector
     * @param k - the z component of the addition vector
     */
    public void addVector(double i, double j, double k){
        x += i;
        y += j;
        z += k;
    }
    
    /**
     * Subtract a vector from this vector. <br>
     * pre: none <br>
     * post: The vector is altered to be the vector - the subtraction vector s.
     * @param s - the vector to subtract
     */
    public void subtractVector(Vector3 s){
        double[] aS = s.getComponents();
        x -= aS[0];
        y -= aS[1];
        z -= aS[2];
    }
    
    /**
     * Subtract a vector from this vector. <br>
     * pre: none <br>
     * post: The vector is altered to be the vector - the vector defined by (i, j, k)
     * @param i - the x component of the subtraction vector
     * @param j - the y component of the subtraction vector
     * @param k - the z component of the subtraction vector
     */
    public void subtractVector(double i, double j, double k){
        x -= i;
        y -= j;
        z -= k;
    }
    
    /**
     * Return the magnitude of the vector as a double. <br>
     * pre: none <br>
     * post: the vector's magnitude is returned.
     * @return magVector
     */
    public double getMagnitude(){
        return(Math.sqrt((x*x + y*y + z*z)));
    }
    
    public double getAngle(Vector3 s){
        return(Math.acos(this.dotMultiply(s) / (this.getMagnitude() * s.getMagnitude() ) ) );
    }
    
    public void rotate(double a, double b, double c){
        //double xa = x;
        //double ya = Math.cos(a)*y + Math.sin(a) * z;
        //double za = (-1)*Math.sin(a) * y + Math.cos(a) * z;
        //double xb = (Math.cos(b) * x - Math.sin(b) * ((-1)*Math.sin(a) * y + Math.cos(a) * z));
        //double yb = (Math.cos(a)*y + Math.sin(a) * z);
        //double zb = (Math.sin(b) * x + Math.cos(b) * ((-1)*Math.sin(a) * y + Math.cos(a) * z));
        double xc = Math.cos(c) * (Math.cos(b) * x - Math.sin(b) * ((-1)*Math.sin(a) * y + Math.cos(a) * z)) + Math.sin(c) * (Math.cos(a)*y + Math.sin(a) * z);
        double yc = (-1) * Math.sin(c) * (Math.cos(b) * x - Math.sin(b) * ((-1)*Math.sin(a) * y + Math.cos(a) * z))  + Math.cos(c)*(Math.cos(a)*y + Math.sin(a) * z);
        double zc = (Math.sin(b) * x + Math.cos(b) * ((-1)*Math.sin(a) * y + Math.cos(a) * z));
        
        this.setVector(xc, yc, zc);
    }
    
    /**
     * Return the dot product of the vector by another vector as a double <br>
     * pre: none <br>
     * post: The dot product is returned <br>
     * @param s - the vector to be dotted with this
     * @return this (dot) s
     */
    public double dotMultiply(Vector3 s){
        double x2 = s.getComponents()[0];
        double y2 = s.getComponents()[1];
        double z2 = s.getComponents()[2];
        return(x*x2 + y*y2 + z*z2);
    }
    
    /**
     * Return the dot product of the vector by another vector as a double <br>
     * pre: none <br>
     * post: The dot product is returned
     * @param i - the magnitude of the x component to be dotted.
     * @param j - the magnitude of the y component to be dotted.
     * @param k - the magnitude of the z component to be dotted.
     * @return this (dot) s
     */
    public double dotMultiply(double i, double j, double k){
        return(x*i + y*j + z*k);
    }
    
    /**
     * Return the vector that results when the vector is crossed with another vector as a Vector3. <br>
     * pre: none <br>
     * post: returns the vector object that results when the vector is crossed with the vector s
     * @param s - the vector to be crossed
     * @return the vector object that results from this x s
     */
    public void crossMultiply(Vector3 s){
        double x2 = s.getComponents()[0];
        double y2 = s.getComponents()[1];
        double z2 = s.getComponents()[2];
        
        this.setVector(new Vector3(
                y*z2 - z*y2,
                z*x2 - x*z2, 
                x*y2 - y*x2));
    }
    
    /**
     * Return the vector that results when the vector is crossed with another vector as a Vector3. <br>
     * pre: none <br>
     * post: returns the vector object that results when the vector is crossed with the vector defined by (i,j,k)
     * @param i - the magnitude of the x component of the vector
     * @param j - the magnitude of the y component of the vector
     * @param k - the magnitude of the z component of the vector
     * @return the vector object that results from the crossing of this vector with (i,j,k)
     */
    public void crossMultiply(double i, double j, double k){
        this.setVector(new Vector3(y*k - z*j,
                z*i - x*k, 
                x*j - y*i));
    }
    
    /**
     * The scalar multiple of the vector is returned. k*Vector <br>
     * pre: none <br>
     * post: the vector defined by the scalar multiple is returned.
     * @param k - the multiple that the vector is multiplied by
     * @return the vector object that results from k*v;
     */
    public void scalarMultiply(double k){
        x = x * k;
        y = y * k;
        z = z * k;
    }
    
    /**
     * Returns the vector that results from projecting this vector on the parameter vector.
     * pre: none
     * post: Returns the vector in the direction of s with a magnitude of the s component of the vector.
     * @param s
     * @return the projection vector
     */
    public Vector3 projectionOn(Vector3 s){
        double k = (this.dotMultiply(s)/(s.getMagnitude() * s.getMagnitude()));
        Vector3 v = new Vector3(Vector3.scalarMultiply(k,s));
        return(v);
    }
    
    /**
     * Returns the vector that results from projecting this vector on the parameter vector. <br>
     * pre: none <br>
     * post: Returns the vector in the direction of (i,j,k) with a magnitude of the (i,j,k) component of this vector.
     * @param i - the magnitude of the x component of the vector being projected on.
     * @param j - the magnitude of the y component of the vector being projected on.
     * @param k - the magnitude of the z component of the vector being projected on.
     * @return the projection vector
     */
    public Vector3 projectionOn(double i, double j, double k){
        double c = (this.dotMultiply(i,j,k)/(i*i + j*j + k*k));
        Vector3 v = new Vector3(c*i, c*j, c*k);
        return(v);
    }
            
    //</editor-fold>
    
    //<editor-fold desc="Static methods">

    
    public static double getMagnitude(double i, double j, double k){
        return(Math.sqrt(i*i + j*j + k*k));
    }
    public static double getMagnitude(Vector3 s){
        double i = s.getComponents()[0];
        double j = s.getComponents()[1];
        double k = s.getComponents()[2];
        return(Math.sqrt(i*i + j*j + k*k));
    }
    public static double dotMultiply(Vector3 s, Vector3 d){
        double x1 = s.getComponents()[0];
        double y1 = s.getComponents()[1];
        double z1 = s.getComponents()[2];
        
        double x2 = d.getComponents()[0];
        double y2 = d.getComponents()[1];
        double z2 = d.getComponents()[2];

        return(x1*x2 + y1*y2 + z1*z2);
    }
    public static double dotMultiply(double x1, double y1, double z1, double x2, double y2, double z2){
        return(x1*x2 + y1*y2 + z1*z2);
    }
    
    /**
     * Return the vector that results when the vector u is crossed with the vector v as a Vector3. <br>
     * pre: none <br>
     * post: returns the vector object that results when the vector u is crossed with the vector v\
     * @param u - the first vector to the crossed
     * @param v - the vector to cross u with
     * @return the vector object that results from u x v
     */
    public static Vector3 crossMultiply(Vector3 u, Vector3 v){
        double x1 = u.getComponents()[0];
        double y1 = u.getComponents()[1];
        double z1 = u.getComponents()[2];
        double x2 = v.getComponents()[0];
        double y2 = v.getComponents()[1];
        double z2 = v.getComponents()[2];
        
        return(new Vector3(
                y1*z2 - z1*y2,
                z1*x2 - x1*z2, 
                x1*y2 - y1*x2));
    }
    
    /**
     * Return the vector that results when the vector is crossed with another vector as a Vector3. <br>
     * pre: none <br>
     * post: returns the vector object that results when the vector (x,y,z) is crossed with the vector defined by (i,j,k)
     * @param x - the magnitude of the x component of the 1st vector
     * @param y - the magnitude of the y component of the 1st vector
     * @param z - the magnitude of the z component of the 1st vector
     * @param i - the magnitude of the x component of the 2nd vector
     * @param j - the magnitude of the y component of the 2nd vector
     * @param k - the magnitude of the z component of the 2nd vector
     * @return the vector object that results from the crossing of (x,y,z) with (i,j,k)
     */
    public static Vector3 crossMultiply(double x, double y, double z, double i, double j, double k){
        return(new Vector3(y*k - z*j,
                z*i - x*k, 
                x*j - y*i));
    }
    
    /**
     * The scalar multiple of the vector is returned. k*Vector <br>
     * pre: none <br>
     * post: the vector defined by the scalar multiple is returned.
     * @param k - the multiple that the vector is multiplied by
     * @return the vector object that results from k*v;
     */
    public static Vector3 scalarMultiply(double k, Vector3 s){
        double x2 = s.getMagnitude_componentX() * k;
        double y2 = s.getComponents()[1] * k;
        double z2 = s.getComponents()[2] * k;
        return(new Vector3(x2, y2, z2));
    }
    
    /**
     * The vector that occurs when the matrix [a, b, c] is multiplied with the vector v. <br>
     * pre: none <br>
     * post: The vector that is = to (ax, by, cz) is returned. 
     * @param a - the value by which to multiply the x component
     * @param b - the value by which to multiply the y component
     * @param c - the value by which to multiply the z component
     * @return the Vector3 object (ax, by, cz)
     */
    public static Vector3 matrixMultiply(double a, double b, double c, Vector3 v){
        double x = v.getComponents()[0];
        double y = v.getComponents()[1];
        double z = v.getComponents()[2];
        return(new Vector3(a*x,b*y,c*z));
    }
    
    public static Vector3 addVectors(Vector3 s, Vector3 d){
        double x1 = s.getComponents()[0];
        double y1 = s.getComponents()[1];
        double z1 = s.getComponents()[2];
        double x2 = d.getComponents()[0];
        double y2 = d.getComponents()[1];
        double z2 = d.getComponents()[2];
        
        return(new Vector3(x1 + x2, y1+y2, z1+z2));
    }
    
    //</editor-fold>
    
    @Override
    public String toString(){
        return("( " + x + ", " + y + ", " + z + " )");
    }
    
}
