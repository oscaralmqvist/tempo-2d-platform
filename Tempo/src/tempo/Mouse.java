/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import states.*;
import tempo.sprites.Bullet;

/**
 *
 * @author Elev
 */
public class Mouse implements MouseListener {
    
    private GamePanel gp;
    private GameState gs;
    
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
                if(gs.getPlayer().getNuts() > 0){
                    gs.getPlayer().getReload().clear();
                    gs.getPlayer().setReloading(false);   
                    gs.getPlayer().setCharging(true);

                }
            }
            if(s.getClass() == PauseState.class){
            
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(State s : gp.getStateManager().getStates()) {
            if(s.getClass() == GameState.class) {
                gs = (GameState) s;
                if(gs.getPlayer().getNuts() > 0){
                    gs.getSound().playSound("swoosh");
                    int speed = gs.getPlayer().getCharge().size();

                    if(speed < 5){
                        speed = 5;
                    }
                    gs.getPlayer().setCharging(false);

                        float angle = (float) Math.toDegrees(Math.atan2(e.getY() - (gs.getPlayer().rect.y + gs.getPlayer().rect.height/2), e.getX() - (gs.getPlayer().rect.x + gs.getPlayer().currentHand)));
                        int i = (int)(Math.random() * 4 + 30);
                        gs.getBullets().add(new Bullet(gs.getPlayer().rect.x + gs.getPlayer().currentHand, gs.getPlayer().rect.y + gs.getPlayer().rect.height/2, i, i, angle, gs.getSs().getSprite(13, 0, 1, 1), gs, e.getX(),speed));

                        gs.getPlayer().setNuts(gs.getPlayer().getNuts() - 1);
                        if(gs.getPlayer().getNuts() == 0){
                            gs.getPlayer().setReloading(true);
                        }
                gs.getPlayer().getCharge().clear();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


    
    
    
}
