
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
    
    public Sprites(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    
    public abstract void paint(Graphics g);
    
}
