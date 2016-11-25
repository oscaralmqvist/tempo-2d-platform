
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
    
    public Sprites(Rectangle rect, BufferedImage image) {
        this.rect = rect;
        this.image = image;
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
