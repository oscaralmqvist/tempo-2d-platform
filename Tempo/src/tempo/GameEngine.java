
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
   
    public boolean running = false;
    
    public GameEngine(GamePanel gp){
        this.gp = gp;
        start();
    }
    
    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }
    
    
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        
        int ticks = 0;
        int frames = 0;
        
        //long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            //boolean shouldRender = true;
            
            
            /*try {
                Thread.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }*/
            
            while (delta >= 1) {
                //ticks++;
                tick();
                delta -= 1;
                //shouldRender = true;
            }
            
            /*if(shouldRender) {
                frames++;
               // render();
            }*/
            
            /*if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                //System.out.println(ticks + "ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }*/
            
        }
    }
    
        public synchronized void tick() {
        if(!gp.isPaused){
            checkMovement();
            checkCollision();
            checkClouds();
            checkWeapon();
            checkDialogue();
            checkPlayer();
        }
        gp.repaint();
        }
        
        public void checkCollision(){
            for(int i = 0;i<gp.level.blocks.size();i++){
                if(gp.level.blocks.get(i).collision){
                    for(int j = 0; j < gp.units.size(); j++){
                        if(((gp.units.get(j).image != null && gp.coll.getTopCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.units.get(j).x,gp.units.get(j).y,gp.units.get(j).width,gp.units.get(j).height))) || gp.coll.getTopCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height))) && gp.player.ySpeed > 0){
                            gp.player.gravity = 0;
                            gp.player.ySpeed = 0;
                            gp.player.jumps = 0;
                            break;
                        } else if ((((gp.units.get(j).image != null && gp.coll.getBottomCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.units.get(j).x,gp.units.get(j).y,gp.units.get(j).width,gp.units.get(j).height)))) || gp.coll.getBottomCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height))) && gp.player.ySpeed < 0) {
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
                        Rectangle u_temp = gp.coll.getCollision(new Rectangle(gp.units.get(u).x,gp.units.get(u).y,gp.units.get(u).width,gp.units.get(u).height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height));
                        if(gp.coll.isIntersect(new Rectangle(gp.units.get(u).x,gp.units.get(u).y,gp.units.get(u).width,gp.units.get(u).height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height))){
                            gp.units.get(u).xSpeed = -gp.units.get(u).xSpeed;
                        }
                        gp.units.get(u).x = u_temp.x;
                    }
                }
            }

            for(int u = 0; u < gp.units.size(); u++){
                Rectangle u_temp = gp.coll.getCollision(new Rectangle(gp.units.get(u).x,gp.units.get(u).y,gp.units.get(u).width,gp.units.get(u).height), new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height));
                if(gp.coll.isIntersect(new Rectangle(gp.units.get(u).x,gp.units.get(u).y,gp.units.get(u).width,gp.units.get(u).height), new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height))){
                    gp.units.get(u).xSpeed = -gp.units.get(u).xSpeed;
                }
            }
            
            for(int i = 0;i<gp.level.blocks.size();i++){
                gp.level.blocks.get(i).x += gp.level.blocks.get(i).SpeedX;
                gp.level.blocks.get(i).y += gp.level.blocks.get(i).SpeedY;
                if(gp.level.blocks.get(i).collision){
                    for(int q = 0; q < gp.units.size(); q++){
                    Rectangle temp = gp.coll.getCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height));
                    gp.player.x = temp.x;
                    gp.player.y = temp.y;
                    
                    if(gp.units.get(q).image != null){
                        temp = gp.coll.getCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height), new Rectangle(gp.units.get(q).x,gp.units.get(q).y,gp.units.get(q).width,gp.units.get(q).height));                    
                        gp.player.x = temp.x;
                        gp.player.y = temp.y;
                    }
                    
                    
                    for(int j = 0;j<gp.bullets.size();j++){
                        if(((gp.units.get(q).image != null && gp.coll.isIntersect(new Rectangle(gp.bullets.get(j).x,gp.bullets.get(j).y,gp.bullets.get(j).width,gp.bullets.get(j).height),new Rectangle(gp.units.get(q).x,gp.units.get(q).y,gp.units.get(q).width,gp.units.get(q).height)))) || gp.coll.isIntersect(new Rectangle(gp.bullets.get(j).x,gp.bullets.get(j).y,gp.bullets.get(j).width,gp.bullets.get(j).height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height)))
                        {
                            boolean test = gp.coll.isIntersect(new Rectangle(gp.bullets.get(j).x,gp.bullets.get(j).y,gp.bullets.get(j).width,gp.bullets.get(j).height),new Rectangle(gp.units.get(q).x,gp.units.get(q).y,gp.units.get(q).width,gp.units.get(q).height));
                            for(int l = 0;l<25;l++){
                                gp.particle.add(new Particle(gp.bullets.get(j).x, gp.bullets.get(j).y, 25,25,gp.ss.getSprite(10, 0, 1, 1)));
                            }
                               gp.bullets.remove(j);
                            if(test && gp.units.get(q).getIsHostile()){
                                gp.units.get(q).loseHealth(1);
                            }
                        }
                    }
                    }
                }
            }
        }
        public void checkPlayer(){
            if (gp.player.y > Tempo.height) {
                gp.player.die();
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
            }
            for(int i = 0; i < gp.bullets.size(); i++) {
                gp.bullets.get(i).shoot();
                gp.bullets.get(i).y += gp.bullets.get(i).ySpeed;
                gp.bullets.get(i).ySpeed += gp.bullets.get(i).gravity;
                if(gp.bullets.get(i).killBullet) {
                    gp.bullets.remove(i);
                }
            }
            
            
            if(MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gp.player.width/2)){
            gp.player.currentHand = gp.player.width - 15;
            }
            else{
                gp.player.currentHand = gp.player.width - 85;
            }
        }
        
        public void checkClouds(){
            for(int i = 0; i < gp.clouds.size(); i++){
                gp.clouds.get(i).x += gp.clouds.get(i).SpeedX;
                if(gp.clouds.get(i).x > Tempo.width + 100){
                    gp.clouds.remove(i);
                    gp.clouds.add(new Block(-100, gp.blockSize+(int)(Math.random()*5*(gp.clouds.size()-i)*21), gp.blockSize*2, gp.blockSize, gp.ss.cloudRandomizer(), false)); 
                    gp.clouds.get(gp.clouds.size()-1).SpeedX = 1;
                }
                if(gp.clouds.get(i).x < -100){
                    gp.clouds.remove(i);
                    gp.clouds.add(new Block(Tempo.width +100, gp.blockSize+(int)(Math.random()*5*(gp.clouds.size()-i)*21), gp.blockSize*2, gp.blockSize, gp.ss.cloudRandomizer(), false)); 
                    gp.clouds.get(gp.clouds.size()-1).SpeedX = 1;
                }
            }
        }
        public void checkMovement(){
            gp.player.x += gp.player.xSpeed;
            gp.player.y += gp.player.ySpeed;  
            for(int i = 0; i < gp.units.size(); i++){
                gp.units.get(i).x += gp.units.get(i).xSpeed;
                if(gp.units.get(i).getIsHostile()){
                        for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                            gp.units.get(i).getHealth().get(j).x = gp.units.get(i).x;
                        }
                    }
            }
            gp.player.ySpeed += gp.player.gravity;
            if(gp.movingLeft){
                if(gp.player.x <= (Tempo.width/2)){
                            for(int l = 0;l<gp.set.size();l++){
                                gp.set.get(l).moveLeft();
                            }
                            for(int l = 0;l<gp.particle.size();l++){
                                gp.particle.get(l).x += 10;
                            }
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).x += 10;
                            }
                            for(int i = 0;i<gp.clouds.size();i++){
                                gp.clouds.get(i).x += 10;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).x += 10;
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                                    gp.units.get(i).getHealth().get(j).x += 10;
                                }
                            }
                           // gp.enemy.x += 10;
                            for(int i = 0; i < gp.units.size(); i++){
                                gp.units.get(i).x += 10;
                            }
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                            gp.player.x -= 10.0;
                        }               
        }
        if(gp.movingRight){
                if(gp.player.x >= (Tempo.width/2)){
                            for(int l = 0;l<gp.set.size();l++){
                                gp.set.get(l).moveRight();
                            }
                            for(int l = 0;l<gp.particle.size();l++){
                                gp.particle.get(l).x += -10;
                            }
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).x += -10;
                            }
                            for(int i = 0;i<gp.clouds.size();i++){
                                gp.clouds.get(i).x += -10;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).x += -10;
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                for(int j = 0;j<gp.units.get(i).getHealth().size();j++){
                                    gp.units.get(i).getHealth().get(j).x += -10;
                                }
                            }
                            for(int i = 0; i < gp.units.size(); i++){
                                gp.units.get(i).x -= 10;
                            }
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                        gp.player.x += 10.0;
                        }
                }  
        }
}
