
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tempo.GamePanel;
import tempo.Tempo;

public class Bullet extends Sprites {
   
   // GamePanel gp;
    public double rads;
    //public float speed = 10;
    public float ySpeed = 10;
    public float xSpeed = 10;
   // public float speed = ySpeed/xSpeed;
    public double gravity = 0.5;
    public boolean killBullet = false;
    
    
    public Bullet(int x, int y, int width, int height, float angle, BufferedImage image, GamePanel gp, int xmouse,int speed) {
        super(x, y, width, height, image);
        this.xSpeed = speed;
        this.ySpeed = speed;
        this.rads = angle*(Math.PI/180);
        this.ySpeed = (float)(Math.sin(rads) * ySpeed);
        
        if(gp.movingRight && xmouse > Tempo.width/2)
            this.xSpeed = speed *2 + 10;
        else if(gp.movingLeft && xmouse < Tempo.width/2)
            this.xSpeed =speed * 2 + 10;
  
    }
    
    public void shoot() {
        super.x += Math.cos(rads) * xSpeed;
        super.y += Math.sin(rads) * 10;

        if(x < 0 || x > Tempo.width) {
            killBullet = true;
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
        //g.fillRect(x, y, width, height);
        g.drawImage(super.image, x, y, width, height, null);
    }
    
    
}
