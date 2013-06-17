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
    
    boolean movingRight, movingLeft, movingForward, movingBackward, movingDown, movingUp;
    boolean rotatingPositiveZ, rotatingNegativeZ, rotatingPositiveY, rotatingNegativeY;
    
    double speed; //meters per second  
    double rotSpeed; //rotation speed in degrees per second
    
    Camera cam;
    
    public Player (GameObject g, Camera c){
        super(g);
        speed = 0.5;
        rotSpeed =0.3;
                
        cam = c;
    }
    
    public void move(){
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
        
        cam.setOrigin(this.getOrigin());
        cam.setOrientation(this.getOrientation()[0], this.getOrientation()[1], this.getOrientation()[2]);
//        
//        System.out.println(this.getOrientation()[0] + " = " + cam.getOrientation()[0]);
    }
    
    
    public boolean getMovingForward(){
        return movingForward;
    }
    
    public boolean getMovingBackward(){
        return movingBackward;
    }
    
    public boolean getMovingLeft(){
        return movingLeft;
    }
    
    public boolean getMovingRight(){
        return movingRight;
    }
    
    public boolean getMovingUp(){
        return movingUp;
    }
    
    public boolean getMovingDown(){
        return movingDown;
    }
    
    public void setMovingForward(boolean b){
        movingForward = b;
    }
    
    public void setMovingBackward(boolean b){
        movingBackward = b;
    }
    
    public void setMovingLeft(boolean b){
        movingLeft = b;
    }
    
    public void setMovingRight(boolean b){
        movingRight = b;
    }
    
    public void setMovingUp(boolean b){
        movingUp = b;
    }
    
    public void setMovingDown(boolean b){
        movingDown = b;
    }
    
    public boolean getRotatingPositiveZ(){
        return rotatingPositiveZ;
    }
    
    public boolean getRotatingNegativeZ(){
        return rotatingNegativeZ;
    }
    
    public boolean getRotatingPositiveY(){
        return rotatingPositiveY;
    }
    
    public boolean getRotatingNegativeY(){
        return rotatingPositiveY;
    }
    
    public void setRotatingPositiveZ(boolean b){
        rotatingPositiveZ = b;
    }
    
    public void setRotatingNegativeZ(boolean b){
        rotatingNegativeZ = b;
    }
    
    public void setRotatingPositiveY(boolean b){
        rotatingPositiveY = b;
    }
    
    public void setRotatingNegativeY(boolean b){
        rotatingNegativeY = b;
    }
    
    public void setSpeed(double s){
        speed = s;
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public Camera getCamera(){
        return cam;
    }
}
