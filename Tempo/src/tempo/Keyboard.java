/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import tempo.sprites.Dialogue;

/**
 *
 * @author Elev
 */
public class Keyboard implements KeyListener {
    GamePanel gp;
   // public boolean movingRight, movingLeft, movingUp, movingDown, isPaused;
    public Keyboard(GamePanel gp){
        this.gp = gp;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_W:
                    if (gp.player.jumps < 2) {
                        for(int i = 0;i<gp.level.blocks.size();i++){
                            gp.player.ySpeed = -22.5f;
                            break;
                        }
                        gp.player.jumps++;
                    }
                    break;
                case KeyEvent.VK_A:
                     gp.movingLeft = true;
                     gp.player.getAnimation().setCurrentAnimation(gp.ss.getSprite(6, 0, 1, 2), gp.ss.getSprite(7, 0, 1, 2), gp.ss.getSprite(8, 0, 1, 2));
                    break;
                case KeyEvent.VK_D:
                    gp.movingRight = true;
                    gp.player.getAnimation().setCurrentAnimation(gp.ss.getSprite(3, 0, 1, 2), gp.ss.getSprite(4, 0, 1, 2), gp.ss.getSprite(5, 0, 1, 2));
                  //  if(!movingRight)
                //        movingRight = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.isPaused = !gp.isPaused;
                    break;
                case KeyEvent.VK_Z:

                    gp.dialogue.add(new Dialogue(gp.ss.getSprite(7, 0, 1, 2).getSubimage(0, 0, 32, 32), gp.ss.getSprite(5, 0, 1, 2).getSubimage(0, 0, 32, 32),
                            "får MASSVIS med random invites :S",
                            "lyssnar på muSIK",
                            "fryser",
                            "jag pajja glajjorna",
                            "VARFÖR E INGEN INLOGGAD 5.00?!?!?!",
                            "oh mai gawd",
                            "megaultraawesomerandomofrandomness",
                            "i'm BAck! ;D",
                            "bRAIN FREEZE!!!",
                            "chillar , gjort klart läxan . YES!!",
                            "skirver om vad jag gör just nu"
                    ));
                    break;
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                     gp.movingLeft = false;
                    break;
                case KeyEvent.VK_D:
                    gp.movingRight = false;
                  //  if(!movingRight)
                //        movingRight = true;
                    break;
                case KeyEvent.VK_R:
                        gp.player.reloading = true;
                    break;
                }
        /*
        if(e.getKeyCode() == KeyEvent.VK_D){
            movingRight = false;
            gp.enemy.xSpeed = 0;
            for(int i = 0;i<gp.blocks.size();i++){
                gp.blocks.get(i).SpeedX = 0;
            }
            if(movingLeft){
                if(gp.enemy.x < 400){
                    for(int i = 0;i<gp.blocks.size();i++){
                        gp.blocks.get(i).SpeedX = +10;
                    }
                }else{
                    for(int i = 0;i<gp.blocks.size();i++){
                        gp.blocks.get(i).SpeedX = 0;
                    }
                gp.enemy.xSpeed -= 10.0;
                }
                movingLeft = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            movingLeft = false;
            gp.enemy.xSpeed = 0;
            for(int i = 0;i<gp.blocks.size();i++){
                gp.blocks.get(i).SpeedX = 0;
            }
            if(movingRight){
                if(gp.enemy.x > 800){
                    for(int i = 0;i<gp.blocks.size();i++){
                        gp.blocks.get(i).SpeedX = -10;
                    }
                }else{
                    for(int i = 0;i<gp.blocks.size();i++){
                        gp.blocks.get(i).SpeedX = 0;
                    }
                gp.enemy.xSpeed += 10.0;
                }
                movingRight = true;
            }
        }        
        */
    }
}
