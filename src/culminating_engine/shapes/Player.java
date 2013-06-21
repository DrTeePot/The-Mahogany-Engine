/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes;

import culminating_engine.Vector3;

/**
 *
 * @author Aidan
 */
public class Player extends GameObject{
    
    //True if the player is doing what the variable describes
    boolean movingRight, movingLeft, movingForward, movingBackward, movingDown, movingUp;
    boolean rotatingPositiveZ, rotatingNegativeZ, rotatingPositiveY, rotatingNegativeY;
    
    double speed; //meters per second  
    double rotSpeed; //rotation speed in degrees per second
    
    Camera cam; //the camera connected to the player
    
    /**
     * Creates a player object
     * pre: none
     * post: a camera object has been created
     * @param g - the player's game object (stores orientation and position)
     * @param c - the player's camera 
     */
    public Player (GameObject g, Camera c){
        super(g);
        speed = 5;
        rotSpeed = 1;
                
        cam = c;
    }
    
    /**
     * Moves the player appropriately, according to movement related variables
     * pre: none
     * post: the player (and related camera) have been moved
     */
    public void move(){
        
        //Movement
        if (movingForward){
            this.translate(Vector3.scalarMultiply(speed, this.getOrientation()[0]));
        }
        if (movingBackward){
            this.translate(Vector3.scalarMultiply(-speed, this.getOrientation()[0]));
        }
        if (movingLeft){
            this.translate(Vector3.scalarMultiply(-speed, this.getOrientation()[1]));
        }
        if (movingRight){
            this.translate(Vector3.scalarMultiply(speed, this.getOrientation()[1]));
        }
        if (movingUp){
            this.translate(Vector3.scalarMultiply(speed, this.getOrientation()[2]));
        }
        if (movingDown){
            this.translate(Vector3.scalarMultiply(-speed, this.getOrientation()[2]));
        }
        
        //Rotation
        if (rotatingPositiveZ){
            this.rotateAroundSelf(0, Math.toRadians(-rotSpeed), 0);
        }
        if (rotatingNegativeZ){
            this.rotateAroundSelf(0, Math.toRadians(rotSpeed), 0);
        }
        if (rotatingPositiveY){
            this.rotateAroundSelf(0, 0, Math.toRadians(rotSpeed));
        }
        if (rotatingNegativeY){
            this.rotateAroundSelf(0, 0, Math.toRadians(-rotSpeed));
        }
        
        //Move and rotate the camera to align with player's gameobject
        cam.setOrigin(this.getOrigin());
        cam.setOrientation(this.getOrientation()[0], this.getOrientation()[1], this.getOrientation()[2]);
//        
//        System.out.println(this.getOrientation()[0] + " = " + cam.getOrientation()[0]);
    }
    
    /**
     * Getter: Returns whether the player is currently moving forward or not
     * pre: none
     * post: movingForward returned
     * @return 
     */
    public boolean getMovingForward(){
        return movingForward;
    }
    
    /**
     * Getter: Returns whether the player is currently moving backward or not
     * pre: none
     * post: movingBackward returned
     * @return 
     */
    public boolean getMovingBackward(){
        return movingBackward;
    }
    
    /**
     * Getter: Returns whether the player is currently moving left or not
     * pre: none
     * post: getMovingLeft returned
     * @return boolean
     */
    public boolean getMovingLeft(){
        return movingLeft;
    }
    
    /**
     * Getter: Returns whether the player is currently moving right or not
     * pre: none
     * post: movingRight returned
     * @return boolean
     */
    public boolean getMovingRight(){
        return movingRight;
    }
    
    /**
     * Getter: Returns whether the player is currently moving up or not
     * pre: none
     * post: movingUp returned
     * @return boolean
     */
    public boolean getMovingUp(){
        return movingUp;
    }
    
    /**
     * Getter: Returns whether the player is currently moving down or not
     * pre: none
     * post: movingDown returned
     * @return boolean
     */
    public boolean getMovingDown(){
        return movingDown;
    }
    
    /**
     * Setter: sets movingForward to b
     * pre: none
     * post movingForward has been set to b
     * @param b 
     */
    public void setMovingForward(boolean b){
        movingForward = b;
    }
    
    /**
     * Setter: sets movingBackward to b
     * pre: none
     * post movingBackward has been set to b
     * @param b 
     */
    public void setMovingBackward(boolean b){
        movingBackward = b;
    }
    
    /**
     * Setter: sets movingLeft to b
     * pre: none
     * post movingLeft has been set to b
     * @param b 
     */
    public void setMovingLeft(boolean b){
        movingLeft = b;
    }
    
    /**
     * Setter: sets movingRight to b
     * pre: none
     * post movingRight has been set to b
     * @param b 
     */
    public void setMovingRight(boolean b){
        movingRight = b;
    }
    
    /**
     * Setter: sets movingUp to b
     * pre: none
     * post movingUp has been set to b
     * @param b 
     */
    public void setMovingUp(boolean b){
        movingUp = b;
    }
    
    /**
     * Setter: sets movingDown to b
     * pre: none
     * post movingDown has been set to b
     * @param b 
     */
    public void setMovingDown(boolean b){
        movingDown = b;
    }
    
    /**
     * Getter: returns rotatingPositiveZ
     * pre: none
     * post: rotatingPositiveZ returned
     * @return boolean
     */
    public boolean getRotatingPositiveZ(){
        return rotatingPositiveZ;
    }
    
    /**
     * Getter: returns rotatingNegativeZ
     * pre: none
     * post: rotatingNegativeZ returned
     * @return boolean
     */
    public boolean getRotatingNegativeZ(){
        return rotatingNegativeZ;
    }
    
    /**
     * Getter: returns rotatingPositiveY
     * pre: none
     * post: rotatingPositiveY returned
     * @return boolean
     */
    public boolean getRotatingPositiveY(){
        return rotatingPositiveY;
    }
    
    /**
     * Getter: returns rotatingNegativeY
     * pre: none
     * post: rotatingNegativeY returned
     * @return boolean
     */
    public boolean getRotatingNegativeY(){
        return rotatingNegativeY;
    }
    
    /**
     * Setter: sets RotatingPositiveZ to b
     * pre: none
     * post: RotatingPositiveZ set to b
     * @param b 
     */
    public void setRotatingPositiveZ(boolean b){
        rotatingPositiveZ = b;
    }
    
    /**
     * Setter: sets rotatingNegativeZ to b
     * pre: none
     * post: rotatingNegativeZ set to b
     * @param b 
     */
    public void setRotatingNegativeZ(boolean b){
        rotatingNegativeZ = b;
    }
    
    /**
     * Setter: sets rotatingPositiveY to b
     * pre: none
     * post: rotatingPositiveY set to b
     * @param b 
     */
    public void setRotatingPositiveY(boolean b){
        rotatingPositiveY = b;
    }
    
    /**
     * Setter: sets rotatingNegativeY to b
     * pre: none
     * post: rotatingNegativeY set to b
     * @param b 
     */
    public void setRotatingNegativeY(boolean b){
        rotatingNegativeY = b;
    }
    
    /**
     * Setter: sets speed to s
     * pre: none
     * post: speed set to s
     * @param s 
     */
    public void setSpeed(double s){
        speed = s;
    }
    
    /**
     * Getter: returns speed
     * pre: none
     * post: speed returned
     * @return boolean
     */
    public double getSpeed(){
        return speed;
    }
    
    /**
     * Getter: returns the player's camera
     * pre: none
     * post: player's camera returned
     * @return Camera
     */
    public Camera getCamera(){
        return cam;
    }
}
