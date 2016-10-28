
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprites {
    
    public float angle;
    public float speed = 10;
    
    
    public Bullet(int x, int y, int width, int height, float angle, BufferedImage image) {
        super(x, y, width, height, image);
        this.angle = angle;
    }
    
    public void shoot() {
        double rads = angle*(Math.PI/180);
        super.x += Math.cos(rads) * speed;
        super.y += Math.sin(rads) * speed;
    }
    
    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, width, height);
    }
    
    
}
