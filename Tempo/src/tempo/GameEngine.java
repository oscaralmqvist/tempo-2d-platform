
package tempo;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.*;
import tempo.sprites.Block;
import tempo.sprites.Bullet;
import tempo.sprites.Npc;
import tempo.sprites.Particle;
/**
*
* @author Oscar Almqvist
*/
public class GameEngine implements Runnable {
    GamePanel gp;
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
            ticks++;
            if(!gp.isPaused){         
                if(ticks % 4 == 2){
                    gp.set.get(1).rect.x += 1;            
                }
                checkMovement();
                checkCollision();
                checkWeapon();
                checkDialogue();
                checkPlayer();
                resetScreen();
                gp.player.getAnimation().tick();
            }
            gp.repaint();
            if(ticks == 60){
                ticks = 0;
            }
        }

        public void resetScreen(){
            if(gp.player.resetScreen){
                gp.player.resetScreen = false;
                
                for(int l = 0;l<gp.particle.size();l++){
                    gp.particle.get(l).rect.x += gp.player.spawnDiff;
                }
                for(int i = 0;i<gp.level.blocks.size();i++){
                    gp.level.blocks.get(i).rect.x += gp.player.spawnDiff;
                }
                for(int i = 0;i<gp.clouds.size();i++){
                    gp.clouds.get(i).rect.x += gp.player.spawnDiff;
                        gp.clouds.get(i).rect.x += gp.player.spawnDiff;
                }
                for(int i = 0;i<gp.bullets.size();i++){
                    gp.bullets.get(i).rect.x +=gp.player.spawnDiff;
                }
                for(int i = 0; i < gp.units.size(); i++){
                    for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                        gp.units.get(i).getHealth().get(j).rect.x += gp.player.spawnDiff + 8;
                    }
                }
                for(int i = 0; i < gp.units.size(); i++){
                    gp.units.get(i).rect.x += gp.player.spawnDiff;
                }
                for(int i = 0; i < gp.level.checkpoints.size(); i++){
                    gp.level.checkpoints.get(i).rect.x +=gp.player.spawnDiff;
                }
                
                gp.level.spawn.rect.x +=gp.player.spawnDiff;
                gp.level.goal.rect.x  += gp.player.spawnDiff;
                gp.player.rect.x+=gp.player.spawnDiff;

            }
        }
        public void checkCollision(){
            for(int i = 0;i<gp.level.blocks.size();i++){
                if(gp.level.blocks.get(i).collision){
                    for(int j = 0; j < gp.units.size(); j++){
                        if(((gp.units.get(j).image != null && gp.coll.getTopCollision(gp.player.rect ,gp.units.get(j).rect)) || gp.coll.getTopCollision(gp.player.rect,gp.level.blocks.get(i).rect)) && gp.player.ySpeed > 0){
                            gp.player.gravity = 0;
                            gp.player.ySpeed = 0;
                            gp.player.jumps = 0;
                            break;
                        } else if(((gp.units.get(j).image != null && gp.coll.getTopCollision(gp.player.rect ,gp.units.get(j).rect)) || gp.coll.getTopCollision(gp.player.rect,gp.level.blocks.get(i).rect)) && gp.player.ySpeed < 0){
                            gp.player.gravity = 0;
                            gp.player.ySpeed = 0;
                        } else { 
                            gp.player.gravity = 2f;
                        }
                    }
                }
            }
            for(int i = 0; i < gp.level.blocks.size();i++){
                if(gp.level.blocks.get(i).collision){
                    for(int u = 0; u < gp.units.size(); u++){
                        Rectangle u_temp = gp.coll.getCollision(gp.units.get(u).rect, gp.level.blocks.get(i).rect);
                        if(gp.coll.isIntersect(gp.units.get(u).rect, gp.level.blocks.get(i).rect)){
                            gp.units.get(u).xSpeed = -gp.units.get(u).xSpeed;
                        }
                        gp.units.get(u).rect.x = u_temp.x;
                    }
                }
            }

            for(int u = 0; u < gp.units.size(); u++){
                Rectangle u_temp = gp.coll.getCollision(gp.units.get(u).rect, gp.player.rect);
                if(gp.coll.isIntersect(gp.units.get(u).rect, gp.player.rect)){
                    gp.units.get(u).xSpeed = -gp.units.get(u).xSpeed;
                }
            }

            for(int i = 0;i<gp.level.blocks.size();i++){
                gp.level.blocks.get(i).rect.x += gp.level.blocks.get(i).SpeedX;
                gp.level.blocks.get(i).rect.y += gp.level.blocks.get(i).SpeedY;
                if(gp.level.blocks.get(i).collision){
                    for(int q = 0; q < gp.units.size(); q++){
                    Rectangle temp = gp.coll.getCollision(gp.player.rect, gp.level.blocks.get(i).rect);
                    gp.player.rect.x = temp.x;
                    gp.player.rect.y = temp.y;

                    if(gp.units.get(q).image != null){
                        temp = gp.coll.getCollision(gp.player.rect, gp.units.get(q).rect);                    
                        gp.player.rect.x = temp.x;
                        gp.player.rect.y = temp.y;
                    }

                    for(int j = 0;j<gp.bullets.size();j++){
                        if(((gp.units.get(q).image != null && gp.coll.isIntersect(gp.bullets.get(j).rect,gp.units.get(q).rect))) || gp.coll.isIntersect(gp.bullets.get(j).rect, gp.level.blocks.get(i).rect))
                        {
                            boolean test = gp.coll.isIntersect(gp.bullets.get(j).rect,gp.units.get(q).rect);
                            gp.sound.playSound("splat");
                            for(int l = 0;l<25;l++){
                                gp.particle.add(new Particle(gp.bullets.get(j).rect.x, gp.bullets.get(j).rect.y, 25,25,gp.ss.getSprite(14, 0, 1, 1)));
                            }
                            if(test && gp.units.get(q).getIsHostile()){
                                gp.units.get(q).loseHealth(Math.round(gp.bullets.get(j).xSpeed/5));
                            }
                            gp.bullets.remove(j);
                        }
                    }
                    }
                }
            }

            for(int l=0; l < gp.level.checkpoints.size();l++){
                if(gp.coll.isIntersect(gp.player.rect,gp.level.checkpoints.get(l).rect) && !gp.level.checkpoints.get(l).getChecked()){
                    gp.player.setCheckpoint(gp.level.checkpoints.get(l));
                    gp.level.checkpoints.get(l).checked();
                    gp.level.checkpoints.get(l).image = gp.ss.getSprite(2, 7, 2, 2);
                    while(gp.level.checkpoints.get(l).getTick() < 20){
                        int i = (int)(Math.random() * 3);
                        gp.particle.add(new Particle(gp.level.checkpoints.get(l).rect.x+30, gp.level.checkpoints.get(l).rect.y+30, 80,80,gp.ss.getSprite(13, 1+i, 1, 1)));
                        gp.level.checkpoints.get(l).tick();
                    }
                    gp.sound.playMusic();
                }
            }
            if(gp.coll.isIntersect(gp.player.rect, gp.level.goal.rect)){
                gp.currentLevel++;
                gp.loadLevel();
            }
        }


        public void checkPlayer(){
            if (gp.player.rect.y > Tempo.height) {
                gp.player.die();
                gp.sound.playSound("neeeej");
            }
        }

        public void checkDialogue(){
            for(int i = 0; i < gp.dialogue.size(); i++) {
               if(gp.dialogue.get(i).isDone()) {
                   gp.dialogue.remove(i);
               } else {
                   gp.dialogue.get(i).printDialogue();
               }
            }
        }

        public void checkWeapon(){
            if(gp.player.reloading){
                gp.player.reload();
            }
            if(gp.player.charging){
                gp.player.charge();
            }


            for(int l = 0;l<gp.particle.size();l++){
                gp.particle.get(l).Update();
                if (gp.particle.get(l).killParticle) {
                    gp.particle.remove(l);
                }
            }
            for(int i = 0; i < gp.bullets.size(); i++) {
                gp.bullets.get(i).shoot();
                gp.bullets.get(i).rect.y += gp.bullets.get(i).ySpeed;
                gp.bullets.get(i).ySpeed += gp.bullets.get(i).gravity;
                if(gp.bullets.get(i).killBullet) {
                    gp.bullets.remove(i);
                }
            }


            if (MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gp.player.rect.width/2)) {
                gp.player.currentHand = gp.player.rect.width - 15;
            } else {
                gp.player.currentHand = gp.player.rect.width - 85;
            }
            
            if ((!gp.movingLeft && !gp.movingRight) || (gp.movingLeft && gp.movingRight)) {
                gp.player.animation.setCurrentAnimation(gp.ss.getSprite(4, 0, 1, 2));
            }
        }

        public void checkMovement(){
            gp.player.rect.y += gp.player.ySpeed;  
            for(int i = 0; i < gp.units.size(); i++){
                gp.units.get(i).rect.x += gp.units.get(i).xSpeed;
                if(gp.units.get(i).getIsHostile()){
                    for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                        gp.units.get(i).getHealth().get(j).rect.x = gp.units.get(i).rect.x;
                    }
                }
            }
            gp.player.ySpeed += gp.player.gravity;
            if(gp.movingLeft){
                if(gp.player.rect.x <= (Tempo.width/2)){
                            for(int l = 0;l<gp.set.size();l++){
                                gp.set.get(l).moveLeft();
                            }
                            for(int l = 0;l<gp.particle.size();l++){
                                gp.particle.get(l).rect.x += gp.player.xSpeed;
                            }
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).rect.x += gp.player.xSpeed;
                            }
                            for(int i = 0;i<gp.clouds.size();i++){
                                gp.clouds.get(i).rect.x += 1;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).rect.x += gp.player.xSpeed;
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                                    gp.units.get(i).getHealth().get(j).rect.x += gp.player.xSpeed;
                                }
                            }
                            for(int i = 0; i< gp.level.checkpoints.size(); i++){
                                gp.level.checkpoints.get(i).rect.x+= gp.player.xSpeed;
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                gp.units.get(i).rect.x += gp.player.xSpeed;
                            }
                            gp.level.spawn.rect.x+=gp.player.xSpeed;
                            gp.level.goal.rect.x+=gp.player.xSpeed;
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                            gp.player.rect.x -= gp.player.xSpeed;
}               
        }
        if(gp.movingRight){
                if(gp.player.rect.x >= (Tempo.width/2)){
                            for(int l = 0;l<gp.set.size();l++){
                                gp.set.get(l).moveRight();
                            }
                            for(int l = 0;l<gp.particle.size();l++){
                                gp.particle.get(l).rect.x -= gp.player.xSpeed;
                            }
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).rect.x -= gp.player.xSpeed;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).rect.x -= gp.player.xSpeed;
                            }
                            for(int i = 0; i< gp.level.checkpoints.size(); i++){
                                gp.level.checkpoints.get(i).rect.x -= gp.player.xSpeed;
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                                    gp.units.get(i).getHealth().get(j).rect.x -= gp.player.xSpeed;
                                }
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                gp.units.get(i).rect.x -= gp.player.xSpeed;
                            }
                            gp.level.spawn.rect.x -= gp.player.xSpeed;
                            gp.level.goal.rect.x -= gp.player.xSpeed;
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                        gp.player.rect.x += gp.player.xSpeed;
                        }
}  
        }
}
