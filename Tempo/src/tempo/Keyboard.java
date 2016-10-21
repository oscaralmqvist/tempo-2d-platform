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
    public Keyboard(GamePanel gp){
        this.gp = gp;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    gp.player.y += 10;
                    break;
                case KeyEvent.VK_UP:
                    gp.player.y -= 10;
                    break;
                case KeyEvent.VK_LEFT:
                    gp.player.x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    gp.player.x += 10;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
