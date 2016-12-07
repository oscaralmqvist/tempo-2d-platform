
package tempo.sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import tempo.Animation;

/**
 *
 * @author Oscar Almqvist
 */
public abstract class Sprites {
    
    public Rectangle rect;
    public BufferedImage image;
    public Animation animation;
    private float dx, dy, gravity;
    
    public Sprites(Rectangle rect, BufferedImage image, float dx, float dy, float gravity) {
        this.rect = rect;
        this.image = image;
        this.dx = dx;
        this.dy = dy;
        this.gravity = gravity;
    }
    
    public float getVelocityX(){
        return dx;
    }
    
    public float getVelocityY(){
        return dy;
    }
    
    public float getGravity(){
        return gravity;
    }
    
    public void setVelocityX(float dx){
        this.dx = dx;
    }
    
    public void setVelocityY(float dy){
        this.dy = dy;
    }
    
    public void setGravity(float gravity){
       this. gravity = gravity;
    }
    
    public void addVelocityX(float dx){
        this.dx += dx;
    }
    
    public void addVelocityY(float dy){
        this.dy += dy;
    }
        
    public abstract void paint(Graphics g);
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void createAnimation() {
        animation = new Animation(this);
    }
    
    public Animation getAnimation() {
        return animation;
    }
    
}
