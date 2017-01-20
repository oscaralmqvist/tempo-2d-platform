/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import states.GameState;
import states.MenuState;
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
       
            if(gp.getStateManager().getStates().peek().getClass() == MenuState.class) {
                gp.getStateManager().startGame();
            }
        
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
                            if (gs.getPlayer().getJumps() < 2) {
                                gs.getSound().playSound("jump");
                                for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                                    gs.getPlayer().setVelocityY(-22.5f);
                                    break;
                                }
                                gs.getPlayer().setJumps(gs.getPlayer().getJumps() + 1);
                            }
                            break;
                        case KeyEvent.VK_A:
                            if(!gs.isMovingLeft())
                                gs.getPlayer().setFlipped(false);
                                gs.getPlayer().getAnimation().setCurrentAnimation(gs.getTexture().getSprite(5, 0, 1, 2), gs.getTexture().getSprite(6, 0, 1, 2), gs.getTexture().getSprite(7, 0, 1, 2), gs.getTexture().getSprite(8, 0, 1, 2), gs.getTexture().getSprite(9, 0, 1, 2), gs.getTexture().getSprite(10, 0, 1, 2));
                             gs.setMovingLeft(true);
                            break;
                        case KeyEvent.VK_D:
                            if(!gs.isMovingRight())
                                gs.getPlayer().setFlipped(true);
                                gs.getPlayer().getAnimation().setCurrentAnimation(gs.getTexture().getSprite(5, 0, 1, 2), gs.getTexture().getSprite(6, 0, 1, 2), gs.getTexture().getSprite(7, 0, 1, 2), gs.getTexture().getSprite(8, 0, 1, 2), gs.getTexture().getSprite(9, 0, 1, 2), gs.getTexture().getSprite(10, 0, 1, 2));
                            gs.setMovingRight(true);
                            break;
                        case KeyEvent.VK_ESCAPE:
                            if(gp.getStateManager().getStates().peek().getClass() == GameState.class) {
                                gp.getStateManager().pauseGame();
                                gp.repaint();
                            }
                            
                            break;
                        case KeyEvent.VK_Z:
                            gs.getDialogue().add(new Dialogue(gs.getTexture().getSprite(7, 0, 1, 2).getSubimage(0, 0, 32, 32), gs.getTexture().getSprite(5, 0, 1, 2).getSubimage(0, 0, 32, 32),
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

        
   


    }

    @Override
    public void keyReleased(KeyEvent e) {

            if(gp.getStateManager().getStates().peek().getClass() == GameState.class) {
                switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                     gs.setMovingLeft(false);
                    break;
                case KeyEvent.VK_D:
                    gs.setMovingRight(false);
                    break;
                case KeyEvent.VK_R:
                        gs.getPlayer().setReloading(true);
                    break;
                }
            } 
    
    }
}
