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
        if(gp.player.nuts > 0){
            gp.player.reload.clear();
            gp.player.reloading = false;   
            gp.player.charging = true;

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(gp.player.nuts > 0){
            gp.sound.playSound("swoosh");
            int speed = gp.player.charge.size();

            if(speed < 5){
                speed = 5;
            }
            gp.player.charging = false;
            
                float angle = (float) Math.toDegrees(Math.atan2(e.getY() - (gp.player.rect.y + gp.player.rect.height/2), e.getX() - (gp.player.rect.x + gp.player.currentHand)));
                int i = (int)(Math.random() * 4 + 30);
                gp.bullets.add(new Bullet(gp.player.rect.x + gp.player.currentHand, gp.player.rect.y + gp.player.rect.height/2, i, i, angle, gp.ss.getSprite(13, 0, 1, 1), gp, e.getX(),speed));
                
                gp.player.nuts--;
                if(gp.player.nuts == 0){
                    gp.player.reloading = true;
                }
        gp.player.charge.clear();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    
    
    
}
