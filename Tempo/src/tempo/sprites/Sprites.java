
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tempo.Animation;

/**
 *
 * @author Oscar Almqvist
 */
public abstract class Sprites {
    
    public int x;
    public int y;
    public int width;
    public int height;
    public BufferedImage image;
    public Animation animation;
    
    public Sprites(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
