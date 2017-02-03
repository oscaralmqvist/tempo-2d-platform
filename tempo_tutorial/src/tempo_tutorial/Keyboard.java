
package tempo_tutorial;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    
    private GamePanel gp;
    
    public Keyboard(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_D:
                //gp.getPlayer().getRectangle().x = (int)(gp.getPlayer().getRectangle().x+gp.getPlayer().getVelocityX());
                if(!gp.getPlayer().getMovingLeft()){
                    gp.getPlayer().setMovingLeft(true);
                    gp.getPlayer().setFlipped(true);
                    gp.getPlayer().getAnimation().setCurrentAnimation(gp.getSpritesheet().getSprite(5, 0, 1, 2), gp.getSpritesheet().getSprite(6, 0, 1, 2), gp.getSpritesheet().getSprite(7, 0, 1, 2), gp.getSpritesheet().getSprite(8, 0, 1, 2), gp.getSpritesheet().getSprite(9, 0, 1, 2), gp.getSpritesheet().getSprite(10, 0, 1, 2));
                }
            break;
                
            case KeyEvent.VK_A:
               //gp.getPlayer().getRectangle().x = (int)(gp.getPlayer().getRectangle().x-gp.getPlayer().getVelocityX());
                if(!gp.getPlayer().getMovingRight()){
                    gp.getPlayer().setMovingRight(true);
                    gp.getPlayer().setFlipped(false);
                    gp.getPlayer().getAnimation().setCurrentAnimation(gp.getSpritesheet().getSprite(5, 0, 1, 2), gp.getSpritesheet().getSprite(6, 0, 1, 2), gp.getSpritesheet().getSprite(7, 0, 1, 2), gp.getSpritesheet().getSprite(8, 0, 1, 2), gp.getSpritesheet().getSprite(9, 0, 1, 2), gp.getSpritesheet().getSprite(10, 0, 1, 2));
                }
            break;
     
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    gp.getPlayer().setMovingRight(false);
                break;
                
                case KeyEvent.VK_D:
                    gp.getPlayer().setMovingLeft(false);
                break;
        }
    }
    
}
