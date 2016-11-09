/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import tempo.sprites.Bullet;

/**
 *
 * @author Elev
 */
public class Mouse implements MouseListener {
    
    GamePanel gp;
    
    public Mouse(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!gp.isPaused){
            if((gp.player.nuts >0) /*&& (e.getX() < gp.player.x || e.getX() > gp.player.x + gp.player.width)*/){
                gp.player.reloading = false;
                float angle = (float) Math.toDegrees(Math.atan2(e.getY() - (gp.player.y + gp.player.height/2), e.getX() - (gp.player.x + gp.player.currentHand)));
                int i = (int)(Math.random() * 4 + 30);
                                
                gp.bullets.add(new Bullet(gp.player.x + gp.player.currentHand, gp.player.y + gp.player.height/2, i, i, angle, gp.ss.getSprite(2, 2, 1, 1), gp, e.getX()));
                
                gp.player.nuts--;
                if(gp.player.nuts == 0){
                    gp.player.reloading = true;
                }
            }
        }   
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    
    
    
}
