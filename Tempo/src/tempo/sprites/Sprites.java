
package tempo.sprites;

import java.awt.Graphics;

/**
 *
 * @author Oscar Almqvist
 */
public abstract class Sprites {
    
    public int x;
    public int y;
    public int width;
    public int height;
    
    public Sprites(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void paint(Graphics g);
    
}
