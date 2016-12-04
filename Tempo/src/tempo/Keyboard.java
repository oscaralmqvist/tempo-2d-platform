/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import states.GameState;
import states.PauseState;
import states.State;
import tempo.sprites.Dialogue;

/**
 *
 * @author Elev
 */
public class Keyboard implements KeyListener {
    GamePanel gp;
    GameState gs;
    public Keyboard(GamePanel gp){
        this.gp = gp;
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        /* 
        Kan ej loop:a igenom detta med en foreach, då den uppdaterar
        spelet under körning.
        */
       
                   if(gp.getStateManager().getStates().peek().getClass() == PauseState.class) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gp.getStateManager().startGame();
                    return;
                }
            }

            if(gp.getStateManager().getStates().peek().getClass() == GameState.class) {
                gs =  (GameState) gp.getStateManager().getStates().peek();

                switch(e.getKeyCode()){
                        case KeyEvent.VK_SPACE:
                        case KeyEvent.VK_W:
                            if (gs.player.jumps < 2) {
                                gs.sound.playSound("jump");
                                for(int i = 0;i<gs.level.blocks.size();i++){
                                    gs.player.ySpeed = -22.5f;
                                    break;
                                }
                                gs.player.jumps++;
                            }
                            break;
                        case KeyEvent.VK_A:
                            if(!gs.movingLeft)
                                gs.player.flipped = false;
                                gs.player.getAnimation().setCurrentAnimation(gs.ss.getSprite(5, 0, 1, 2), gs.ss.getSprite(6, 0, 1, 2), gs.ss.getSprite(7, 0, 1, 2), gs.ss.getSprite(8, 0, 1, 2), gs.ss.getSprite(9, 0, 1, 2), gs.ss.getSprite(10, 0, 1, 2));
                             gs.movingLeft = true;
                            break;
                        case KeyEvent.VK_D:
                            if(!gs.movingRight)
                                gs.player.flipped = true;
                                gs.player.getAnimation().setCurrentAnimation(gs.ss.getSprite(5, 0, 1, 2), gs.ss.getSprite(6, 0, 1, 2), gs.ss.getSprite(7, 0, 1, 2), gs.ss.getSprite(8, 0, 1, 2), gs.ss.getSprite(9, 0, 1, 2), gs.ss.getSprite(10, 0, 1, 2));
                            gs.movingRight = true;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            if(gp.getStateManager().getStates().peek().getClass() == GameState.class) {
                                gp.getStateManager().pauseGame();
                                gp.repaint();
                            }
                            
                            break;
                        case KeyEvent.VK_Z:
                            gs.dialogue.add(new Dialogue(gs.ss.getSprite(7, 0, 1, 2).getSubimage(0, 0, 32, 32), gs.ss.getSprite(5, 0, 1, 2).getSubimage(0, 0, 32, 32),
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
            } else {
                System.out.println("asdas");
            }

        
   


    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(State s : gp.getStateManager().getStates()) {
            if(s.getClass() == GameState.class) {
                gs = (GameState) s;
                switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                     gs.movingLeft = false;
                    break;
                case KeyEvent.VK_D:
                    gs.movingRight = false;
                    break;
                case KeyEvent.VK_R:
                        gs.player.reloading = true;
                    break;
                }
            } else {
                System.out.println("as");
            }
    } 
    }
}
