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
        if(!gp.isPaused){
            if(gp.enemy.nuts >0 && !gp.enemy.reloading){
        float angle = (float) Math.toDegrees(Math.atan2(e.getY() - gp.enemy.y, e.getX() - gp.enemy.x));
        int i = (int)(Math.random() * 4 + 30);
        gp.bullets.add(new Bullet(gp.enemy.x + gp.enemy.width-15, gp.enemy.y + gp.enemy.height/2, i, i, angle, gp.ss.getSprite(2, 2, 1, 1)));
        gp.enemy.nuts--;
        if(gp.enemy.nuts == 0){
            gp.enemy.reload();
        }
        System.out.println("SKJUTTTTT!");
            }
        }   
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    
    
    
}
