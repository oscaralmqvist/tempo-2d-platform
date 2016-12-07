/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import states.GameState;
import states.State;
import tempo.sprites.Bullet;

/**
 *
 * @author Elev
 */
public class Mouse implements MouseListener {
    
    GamePanel gp;
    GameState gs;
    
    public Mouse(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(State s : gp.getStateManager().getStates()) {
            if(s.getClass() == GameState.class) {
                gs = (GameState) s;
                if(gs.player.nuts > 0){
                    gs.player.reload.clear();
                    gs.player.reloading = false;   
                    gs.player.charging = true;

                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(State s : gp.getStateManager().getStates()) {
            if(s.getClass() == GameState.class) {
                gs = (GameState) s;
                if(gs.player.nuts > 0){
                    gs.sound.playSound("swoosh");
                    int speed = gs.player.charge.size();

                    if(speed < 5){
                        speed = 5;
                    }
                    gs.player.charging = false;

                        float angle = (float) Math.toDegrees(Math.atan2(e.getY() - (gs.player.rect.y + gs.player.rect.height/2), e.getX() - (gs.player.rect.x + gs.player.currentHand)));
                        int i = (int)(Math.random() * 4 + 30);
                        gs.bullets.add(new Bullet(gs.player.rect.x + gs.player.currentHand, gs.player.rect.y + gs.player.rect.height/2, i, i, angle, gs.ss.getSprite(13, 0, 1, 1), gs, e.getX(),speed));

                        gs.player.nuts--;
                        if(gs.player.nuts == 0){
                            gs.player.reloading = true;
                        }
                gs.player.charge.clear();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    
    
    
}
