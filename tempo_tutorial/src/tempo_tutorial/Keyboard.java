
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
                }
            break;
                
            case KeyEvent.VK_A:
               //gp.getPlayer().getRectangle().x = (int)(gp.getPlayer().getRectangle().x-gp.getPlayer().getVelocityX());
                if(!gp.getPlayer().getMovingRight()){
                    gp.getPlayer().setMovingRight(true);
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
