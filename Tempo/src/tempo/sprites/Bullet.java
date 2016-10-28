
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprites {
    
    public double rads;
    public float speed = 10;
    public float ySpeed = 0;
    public double gravity = 0.5;
    public boolean killBullet = false;
    
    
    public Bullet(int x, int y, int width, int height, float angle, BufferedImage image) {
        super(x, y, width, height, image);
        this.rads = angle*(Math.PI/180);
        this.ySpeed = (float)(Math.sin(rads) * speed);
    }
    
    public void shoot() {
        super.x += Math.cos(rads) * speed;
        super.y += Math.sin(rads) * speed;
        if(x < 0 || x > 1200) {
            killBullet = true;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        //g.fillRect(x, y, width, height);
        g.drawImage(super.image, x, y, width, height, null);
    }
    
    
}
