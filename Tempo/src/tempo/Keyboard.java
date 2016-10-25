/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Elev
 */
public class Keyboard implements KeyListener {
    GamePanel gp;
    public boolean movingRight, movingLeft, movingUp, movingDown;
    
    public Keyboard(GamePanel gp){
        this.gp = gp;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_S:
                    if(!movingDown)
                        gp.enemy.ySpeed += 10;
                        movingDown = true;            
                    break;
                case KeyEvent.VK_SPACE:
                    //KOLLISIONDOWN
                    if(gp.enemy.y >= 220)
                        gp.enemy.ySpeed -= 10;
                    break;
                case KeyEvent.VK_A:
                    if(!movingLeft)
                        gp.enemy.xSpeed -= 10;
                        movingLeft = true;
                    break;
                case KeyEvent.VK_D:
                    if(!movingRight)
                        gp.enemy.xSpeed += 10;
                        movingRight = true;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() != KeyEvent.VK_SPACE){
            gp.enemy.xSpeed = 0;
            movingRight = false;
            movingLeft = false;
        }
    }
    
}
