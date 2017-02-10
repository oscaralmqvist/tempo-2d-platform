
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
                if(gp.getPlayer().getMovingLeft()){
                    gp.getPlayer().setMovingLeft(false);
                }
                gp.getPlayer().setMovingRight(true);
                gp.getPlayer().setFlipped(true);
            break;
                
            case KeyEvent.VK_A:
                if(gp.getPlayer().getMovingRight()){
                    gp.getPlayer().setMovingRight(false);
                }
                gp.getPlayer().setMovingLeft(true);
                gp.getPlayer().setFlipped(false);
            break;
            
            case KeyEvent.VK_W:
            case KeyEvent.VK_SPACE:
                if(gp.getPlayer().getJumps() > 0 ){
                    gp.getPlayer().setVelocityY(-22f);
                    gp.getPlayer().setJumps(gp.getPlayer().getJumps()-1);
                }
     
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    gp.getPlayer().setMovingLeft(false);
                break;
                
                case KeyEvent.VK_D:
                    gp.getPlayer().setMovingRight(false);
                break;
        }
    }
    
}
