
package tempo;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.*;
import states.GameState;
import states.State;
import tempo.sprites.Block;
import tempo.sprites.Bullet;
import tempo.sprites.Npc;
import tempo.sprites.Particle;
/**
*
* @author Oscar Almqvist
*/
public class GameEngine implements Runnable {
    
    private GameState gs;
    public GamePanel gp;
    public double gravity;
    int ticks;
    public boolean running = false;

    public GameEngine(GamePanel gp){
        this.gp = gp;
        start();
    }

    public synchronized void start() {
        new Thread(this).start();
    }
    
    public void run() {
        try {
             while (true) {
                 tick();
                 Thread.sleep(16);
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
    }

        public synchronized void tick() {
            if(gp.getStateManager().getStates().peek().getClass() == GameState.class) {
            for(State s : gp.getStateManager().getStates()) {
                if(s.getClass() == GameState.class) {
                    gs = (GameState) s;
                    ticks++;
                    if(!gs.isPaused){         
                        if(ticks % 4 == 2){
                            gs.set.get(1).rect.x += 1;            
                        }
                        checkMovement();
                        checkCollision();
                        checkWeapon();
                        checkDialogue();
                        checkPlayer();
                        resetScreen();
                        gs.player.getAnimation().tick();
                    }
                    gp.repaint();
                    if(ticks == 60){
                        ticks = 0;
                    }
                } else {
                    gs = null;
                }
            }
            }
        }

        public void resetScreen(){
            if(gs.player.resetScreen){
                gs.player.resetScreen = false;
                
                for(int l = 0;l<gs.particle.size();l++){
                    gs.particle.get(l).rect.x += gs.player.spawnDiff;
                }
                for(int i = 0;i<gs.level.blocks.size();i++){
                    gs.level.blocks.get(i).rect.x += gs.player.spawnDiff;
                }
                for(int i = 0;i<gs.clouds.size();i++){
                    gs.clouds.get(i).rect.x += gs.player.spawnDiff;
                        gs.clouds.get(i).rect.x += gs.player.spawnDiff;
                }
                for(int i = 0;i<gs.bullets.size();i++){
                    gs.bullets.get(i).rect.x +=gs.player.spawnDiff;
                }
                for(int i = 0; i < gs.units.size(); i++){
                    for(int j = 0;j<gs.units.get(i).getHealth().size();j++){
                        gs.units.get(i).getHealth().get(j).rect.x += gs.player.spawnDiff + 8;
                    }
                }
                for(int i = 0; i < gs.units.size(); i++){
                    gs.units.get(i).rect.x += gs.player.spawnDiff;
                }
                for(int i = 0; i < gs.level.checkpoints.size(); i++){
                    gs.level.checkpoints.get(i).rect.x +=gs.player.spawnDiff;
                }
                
                gs.level.spawn.rect.x +=gs.player.spawnDiff;
                gs.level.goal.rect.x  += gs.player.spawnDiff;
                gs.player.rect.x+=gs.player.spawnDiff;

            }
        }
        public void checkCollision(){
            for(int i = 0;i<gs.level.blocks.size();i++){
                if(gs.level.blocks.get(i).collision){
                    for(int j = 0; j < gs.units.size(); j++){
                        if(((gs.units.get(j).image != null && gs.coll.getTopCollision(gs.player.rect ,gs.units.get(j).rect)) || gs.coll.getTopCollision(gs.player.rect,gs.level.blocks.get(i).rect)) && gs.player.ySpeed > 0){
                            gs.player.gravity = 0;
                            gs.player.ySpeed = 0;
                            gs.player.jumps = 0;
                            break;
                        } else if(((gs.units.get(j).image != null && gs.coll.getTopCollision(gs.player.rect ,gs.units.get(j).rect)) || gs.coll.getTopCollision(gs.player.rect,gs.level.blocks.get(i).rect)) && gs.player.ySpeed < 0){
                            gs.player.gravity = 0;
                            gs.player.ySpeed = 0;
                        } else { 
                            gs.player.gravity = 2f;
                        }
                    }
                }
            }
            for(int i = 0; i < gs.level.blocks.size();i++){
                if(gs.level.blocks.get(i).collision){
                    for(int u = 0; u < gs.units.size(); u++){
                        Rectangle u_temp = gs.coll.getCollision(gs.units.get(u).rect, gs.level.blocks.get(i).rect);
                        if(gs.coll.isIntersect(gs.units.get(u).rect, gs.level.blocks.get(i).rect)){
                            gs.units.get(u).xSpeed = -gs.units.get(u).xSpeed;
                        }
                        gs.units.get(u).rect.x = u_temp.x;
                    }
                }
            }

            for(int u = 0; u < gs.units.size(); u++){
                Rectangle u_temp = gs.coll.getCollision(gs.units.get(u).rect, gs.player.rect);
                if(gs.coll.isIntersect(gs.units.get(u).rect, gs.player.rect)){
                    gs.units.get(u).xSpeed = -gs.units.get(u).xSpeed;
                }
            }

            for(int i = 0;i<gs.level.blocks.size();i++){
                gs.level.blocks.get(i).rect.x += gs.level.blocks.get(i).SpeedX;
                gs.level.blocks.get(i).rect.y += gs.level.blocks.get(i).SpeedY;
                if(gs.level.blocks.get(i).collision){
                    for(int q = 0; q < gs.units.size(); q++){
                    Rectangle temp = gs.coll.getCollision(gs.player.rect, gs.level.blocks.get(i).rect);
                    gs.player.rect.x = temp.x;
                    gs.player.rect.y = temp.y;

                    if(gs.units.get(q).image != null){
                        temp = gs.coll.getCollision(gs.player.rect, gs.units.get(q).rect);                    
                        gs.player.rect.x = temp.x;
                        gs.player.rect.y = temp.y;
                    }

                    for(int j = 0;j<gs.bullets.size();j++){
                        if(((gs.units.get(q).image != null && gs.coll.isIntersect(gs.bullets.get(j).rect,gs.units.get(q).rect))) || gs.coll.isIntersect(gs.bullets.get(j).rect, gs.level.blocks.get(i).rect))
                        {
                            boolean test = gs.coll.isIntersect(gs.bullets.get(j).rect,gs.units.get(q).rect);
                            gs.sound.playSound("splat");
                            for(int l = 0;l<25;l++){
                                gs.particle.add(new Particle(gs.bullets.get(j).rect.x, gs.bullets.get(j).rect.y, 25,25,gs.ss.getSprite(14, 0, 1, 1)));
                            }
                            if(test && gs.units.get(q).getIsHostile()){
                                gs.units.get(q).loseHealth(Math.round(gs.bullets.get(j).xSpeed/5));
                            }
                            gs.bullets.remove(j);
                        }
                    }
                    }
                }
            }

            for(int l=0; l < gs.level.checkpoints.size();l++){
                if(gs.coll.isIntersect(gs.player.rect,gs.level.checkpoints.get(l).rect) && !gs.level.checkpoints.get(l).getChecked()){
                    gs.player.setCheckpoint(gs.level.checkpoints.get(l));
                    gs.level.checkpoints.get(l).checked();
                    gs.level.checkpoints.get(l).image = gs.ss.getSprite(2, 7, 2, 2);
                    while(gs.level.checkpoints.get(l).getTick() < 20){
                        int i = (int)(Math.random() * 3);
                        gs.particle.add(new Particle(gs.level.checkpoints.get(l).rect.x+30, gs.level.checkpoints.get(l).rect.y+30, 80,80,gs.ss.getSprite(13, 1+i, 1, 1)));
                        gs.level.checkpoints.get(l).tick();
                    }
                    gs.sound.playMusic();
                }
            }
            if(gs.coll.isIntersect(gs.player.rect, gs.level.goal.rect)){
                gs.currentLevel++;
                gs.loadLevel();
            }
        }


        public void checkPlayer(){
            if (gs.player.rect.y > Tempo.height) {
                gs.player.die();
                gs.sound.playSound("neeeej");
            }
        }

        public void checkDialogue(){
            for(int i = 0; i < gs.dialogue.size(); i++) {
               if(gs.dialogue.get(i).isDone()) {
                   gs.dialogue.remove(i);
               } else {
                   gs.dialogue.get(i).printDialogue();
               }
            }
        }

        public void checkWeapon(){
            if(gs.player.reloading){
                gs.player.reload();
            }
            if(gs.player.charging){
                gs.player.charge();
            }


            for(int l = 0;l<gs.particle.size();l++){
                gs.particle.get(l).Update();
                if (gs.particle.get(l).killParticle) {
                    gs.particle.remove(l);
                }
            }
            for(int i = 0; i < gs.bullets.size(); i++) {
                gs.bullets.get(i).shoot();
                gs.bullets.get(i).rect.y += gs.bullets.get(i).ySpeed;
                gs.bullets.get(i).ySpeed += gs.bullets.get(i).gravity;
                if(gs.bullets.get(i).killBullet) {
                    gs.bullets.remove(i);
                }
            }


            if (MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gs.player.rect.width/2)) {
                gs.player.currentHand = gs.player.rect.width - 15;
            } else {
                gs.player.currentHand = gs.player.rect.width - 85;
            }
            
            if ((!gs.movingLeft && !gs.movingRight) || (gs.movingLeft && gs.movingRight)) {
                gs.player.animation.setCurrentAnimation(gs.ss.getSprite(4, 0, 1, 2));
            }
        }

        public void checkMovement(){
            gs.player.rect.y += gs.player.ySpeed;  
            for(int i = 0; i < gs.units.size(); i++){
                gs.units.get(i).rect.x += gs.units.get(i).xSpeed;
                if(gs.units.get(i).getIsHostile()){
                    for(int j = 0;j<gs.units.get(i).getHealth().size();j++){
                        gs.units.get(i).getHealth().get(j).rect.x = gs.units.get(i).rect.x;
                    }
                }
            }
            gs.player.ySpeed += gs.player.gravity;
            if(gs.movingLeft){
                if(gs.player.rect.x <= (Tempo.width/2)){
                            for(int l = 0;l<gs.set.size();l++){
                                gs.set.get(l).moveLeft();
                            }
                            for(int l = 0;l<gs.particle.size();l++){
                                gs.particle.get(l).rect.x += gs.player.xSpeed;
                            }
                            for(int i = 0;i<gs.level.blocks.size();i++){
                                gs.level.blocks.get(i).rect.x += gs.player.xSpeed;
                            }
                            for(int i = 0;i<gs.clouds.size();i++){
                                gs.clouds.get(i).rect.x += 1;
                            }
                            for(int i = 0;i<gs.bullets.size();i++){
                                gs.bullets.get(i).rect.x += gs.player.xSpeed;
                            }
                            for(int i = 0; i < gs.units.size(); i++){
                                for(int j = 0;j<gs.units.get(i).getHealth().size();j++){
                                    gs.units.get(i).getHealth().get(j).rect.x += gs.player.xSpeed;
                                }
                            }
                            for(int i = 0; i< gs.level.checkpoints.size(); i++){
                                gs.level.checkpoints.get(i).rect.x+= gs.player.xSpeed;
                            }
                            for(int i = 0; i < gs.units.size(); i++){
                                gs.units.get(i).rect.x += gs.player.xSpeed;
                            }
                            gs.level.spawn.rect.x+=gs.player.xSpeed;
                            gs.level.goal.rect.x+=gs.player.xSpeed;
                        }else{
                            for(int i = 0;i<gs.level.blocks.size();i++){
                                gs.level.blocks.get(i).SpeedX = 0;
                            }
                            gs.player.rect.x -= gs.player.xSpeed;
}               
        }
        if(gs.movingRight){
                if(gs.player.rect.x >= (Tempo.width/2)){
                            for(int l = 0;l<gs.set.size();l++){
                                gs.set.get(l).moveRight();
                            }
                            for(int l = 0;l<gs.particle.size();l++){
                                gs.particle.get(l).rect.x -= gs.player.xSpeed;
                            }
                            for(int i = 0;i<gs.level.blocks.size();i++){
                                gs.level.blocks.get(i).rect.x -= gs.player.xSpeed;
                            }
                            for(int i = 0;i<gs.bullets.size();i++){
                                gs.bullets.get(i).rect.x -= gs.player.xSpeed;
                            }
                            for(int i = 0; i< gs.level.checkpoints.size(); i++){
                                gs.level.checkpoints.get(i).rect.x -= gs.player.xSpeed;
                            }
                            for(int i = 0; i < gs.units.size(); i++){
                                for(int j = 0;j<gs.units.get(i).getHealth().size();j++){
                                    gs.units.get(i).getHealth().get(j).rect.x -= gs.player.xSpeed;
                                }
                            }
                            for(int i = 0; i < gs.units.size(); i++){
                                gs.units.get(i).rect.x -= gs.player.xSpeed;
                            }
                            gs.level.spawn.rect.x -= gs.player.xSpeed;
                            gs.level.goal.rect.x -= gs.player.xSpeed;
                        }else{
                            for(int i = 0;i<gs.level.blocks.size();i++){
                                gs.level.blocks.get(i).SpeedX = 0;
                            }
                        gs.player.rect.x += gs.player.xSpeed;
                        }
}  
        }
}
