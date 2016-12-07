
package tempo.sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import states.GameState;
import tempo.Tempo;

public class Bullet extends Sprites {
   
    public double rads;
    public double gravity = 0.5;
    public boolean killBullet = false;
    
    
    public Bullet(int x, int y, int width, int height, float angle, BufferedImage image, GameState gs, int xmouse,float speed) {
        super(new Rectangle(x,y,width,height), image, speed, speed, 0.5f);
        this.rads = angle*(Math.PI/180);
        
        super.setVelocityY( (float)(Math.sin(rads) * super.getVelocityY()));
        
        if(gs.movingRight && xmouse > Tempo.width/2)
            super.setVelocityX(speed*2+10);
        else if(gs.movingLeft && xmouse < Tempo.width/2)
            super.setVelocityX(speed*2+10);
  
    }
    
    public void shoot() {
        super.rect.x += Math.cos(rads) * getVelocityX();
        super.rect.y += Math.sin(rads) * 10;

        if(rect.y > Tempo.height) {
            killBullet = true;
        }
        
    }
    
    @Override
    public void paint(Graphics g) {
        //g.fillRect(x, y, width, height);
        g.drawImage(super.image, rect.x, rect.y, rect.width, rect.height, null);
    }
    
    
}
