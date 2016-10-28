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
    public boolean movingRight, movingLeft, movingUp, movingDown, isPaused;
    
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
                        gp.enemy.ySpeed += 10.0;
                        movingDown = true;            
                    break;
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_W:
                    //KOLLISIONDOWN
                    for(int i = 0;i<gp.blocks.size();i++){
                        gp.enemy.ySpeed -= 15.0;
                        break;
                    }
                    break;
                case KeyEvent.VK_A:
                    if(!movingLeft)
                        if(gp.enemy.x < 400){
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).SpeedX = 10;
                            }
                        }else{
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).SpeedX = 0;
                            }
                            gp.enemy.xSpeed -= 10.0;
                        }
                        movingLeft = true;
                    break;
                case KeyEvent.VK_D:
                    if(!movingRight)
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
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.isPaused = !gp.isPaused;
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
    }
}
