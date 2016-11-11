
package tempo;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import javax.swing.*;
import tempo.sprites.Block;
import tempo.sprites.Bullet;
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
            for(int i = 0; i < gp.dialogue.size(); i++) {
               if(gp.dialogue.get(i).isDone()) {
                   gp.dialogue.remove(i);
               } else {
                   gp.dialogue.get(i).printDialogue();
               }
            }
            if(gp.player.reloading){
                gp.player.reload();
            }
            if(gp.player.charging){
                gp.player.charge();
            }
            
            gp.player.x += gp.player.xSpeed;
            gp.player.y += gp.player.ySpeed;   
            
            gp.player.ySpeed += gp.player.gravity;
            
            for(int i = 0; i < gp.clouds.size(); i++){
                gp.clouds.get(i).x += gp.clouds.get(i).SpeedX;
                if(gp.clouds.get(i).x > Tempo.width + 100){
                    gp.clouds.remove(i);
                    gp.clouds.add(new Block(-100, gp.blockSize+(int)(Math.random()*5*(gp.clouds.size()-i)*21), gp.blockSize*2, gp.blockSize, gp.ss.getSprite(3, 2, 2, 1), false)); 
                    gp.clouds.get(gp.clouds.size()-1).SpeedX = 1;
                }
                if(gp.clouds.get(i).x < -100){
                    gp.clouds.remove(i);
                    gp.clouds.add(new Block((Tempo.width + 100), gp.blockSize+(int)(Math.random()*5*(gp.clouds.size()-i)*21), gp.blockSize*2, gp.blockSize, gp.ss.getSprite(3, 2, 2, 1), false));
                    gp.clouds.get(gp.clouds.size()-1).SpeedX = 1;
                }
            }
            
            
            
            
            for(int i = 0;i<gp.level.blocks.size();i++){
                if(gp.level.blocks.get(i).collision){
                    if(gp.coll.getTopCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height)) && gp.player.ySpeed > 0){
                        gp.player.gravity = 0;
                        gp.player.ySpeed = 0;
                        gp.player.jumps = 0;
                        break;
                    } else if (gp.coll.getBottomCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height),new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height)) && gp.player.ySpeed < 0) {
                        gp.player.gravity = 0;
                        gp.player.ySpeed = 0;
                    } else { 
                        
                        gp.player.gravity = 2f;
                    }
                }
            }
            
            for(int i = 0;i<gp.level.blocks.size();i++){
                gp.level.blocks.get(i).x += gp.level.blocks.get(i).SpeedX;
                gp.level.blocks.get(i).y += gp.level.blocks.get(i).SpeedY;
                if(gp.level.blocks.get(i).collision){
                    Rectangle temp = gp.coll.getCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height));
                    gp.player.x = temp.x;
                    gp.player.y = temp.y;
                    Rectangle temp2 = gp.coll.getCollision(new Rectangle(gp.player.x,gp.player.y,gp.player.width,gp.player.height), new Rectangle(gp.enemy.x,gp.enemy.y,gp.enemy.width,gp.enemy.height));                    
                    gp.player.x = temp2.x;
                    gp.player.y = temp2.y;
                  
                    for(int j = 0;j<gp.bullets.size();j++){
                        if(gp.coll.isIntersect(new Rectangle(gp.bullets.get(j).x,gp.bullets.get(j).y,gp.bullets.get(j).width,gp.bullets.get(j).height), new Rectangle(gp.level.blocks.get(i).x,gp.level.blocks.get(i).y,gp.level.blocks.get(i).width,gp.level.blocks.get(i).height)))

                        {
                            for(int l = 0;l<25;l++){
                                gp.particle.add(new Particle(gp.bullets.get(j).x, gp.bullets.get(j).y, 30,30,gp.ss.getSprite(7, 2, 1, 1)));
                            }
                               gp.bullets.remove(j);
                        }
                    }
                }
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
            
            
        if(gp.movingLeft){
             if(gp.player.x <= (Tempo.width/2)){
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
                            for(int i = 0;i<gp.enemy.health.size();i++){
                                gp.enemy.health.get(i).x += 10;
                            }
                            gp.enemy.x += 10;
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                            gp.player.x -= 10.0;
                        }  
             
             
        }
        if(gp.movingRight){
                if(gp.player.x >= (Tempo.width/2)){
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
                            for(int i = 0;i<gp.enemy.health.size();i++){
                                gp.enemy.health.get(i).x += -10;
                            }
                            gp.enemy.x += -10;
                        }else{
                            for(int i = 0;i<gp.level.blocks.size();i++){
                                gp.level.blocks.get(i).SpeedX = 0;
                            }
                        gp.player.x += 10.0;
                        }
                }
        if (gp.player.y > Tempo.height) {
            gp.player.die();
        }
        
        }
        if(MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gp.player.width/2)){
            gp.player.currentHand = gp.player.width - 15;
        }
        else{
            gp.player.currentHand = gp.player.width - 85;
        }

        gp.repaint();
        }
        /*
        public void render() {
            gp.repaint();
        }
    */
}
