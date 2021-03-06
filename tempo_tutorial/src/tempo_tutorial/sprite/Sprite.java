
package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Sprites är alla objekt som ritas ut på skärmen.
 * Här finns alla värden en Sprite kan ha
 * @author Elev
 */
public abstract class Sprite {
    
    private Rectangle rect;
    private BufferedImage image;
    private float dx, dy, gravity;
    
    /**
     * @param x Spritens x-position
     * @param y Spritens y-position
     * @param width Spritens-bredd
     * @param height Spritens-längd
     * @param image Spritens bild
     * @param dx Spritens hastigheten i x-led
     * @param dy Spritens hastigheten i y-led
     * @param gravity Spritens gravitation
     */
    public Sprite(int x, int y, int width, int height, BufferedImage image, float dx, float dy, float gravity) {
        this.rect = new Rectangle(x, y, width, height);
        this.image = image;
        this.dx = dx;
        this.dy = dy;
        this.gravity = gravity;
    }
    
    
    public Rectangle getRectangle() {
        return rect;
    }
    public void setRectangle(Rectangle newRect) {
        rect = newRect;
    }
    
    public BufferedImage getImage() {
        return image;
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
       this.gravity = gravity;
    }
    
    public void addVelocityX(float dx){
        this.dx += dx;
    }
    
    public void addVelocityY(float dy){
        this.dy += dy;
    }
        
    public abstract void paint(Graphics2D g);
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
