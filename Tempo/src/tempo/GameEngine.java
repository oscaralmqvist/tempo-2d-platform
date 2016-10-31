
package tempo;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import javax.swing.*;
import tempo.sprites.Block;
import tempo.sprites.Bullet;
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
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            
            
            try {
                Thread.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            
            if(shouldRender) {
                frames++;
               // render();
            }
            
            if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                //System.out.println(ticks + "ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
            
        }
    }
    
        public synchronized void tick() {
            
        if(!gp.isPaused){
            
            
            gp.enemy.x += gp.enemy.xSpeed;
            gp.enemy.y += gp.enemy.ySpeed;   
            
            gp.enemy.ySpeed += gp.enemy.gravity;
            
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
            
            
            
            
            
            for(int i = 0;i<gp.blocks.size();i++){
                if(gp.blocks.get(i).collision){
                    if(gp.coll.getTopCollision(new Rectangle(gp.enemy.x,gp.enemy.y,gp.enemy.width,gp.enemy.height),new Rectangle(gp.blocks.get(i).x,gp.blocks.get(i).y,gp.blocks.get(i).width,gp.blocks.get(i).height)) && gp.enemy.ySpeed > 0 
                            || gp.coll.getBottomCollision(new Rectangle(gp.enemy.x,gp.enemy.y,gp.enemy.width,gp.enemy.height),new Rectangle(gp.blocks.get(i).x,gp.blocks.get(i).y,gp.blocks.get(i).width,gp.blocks.get(i).height)) && gp.enemy.ySpeed < 0 ){
                        gp.enemy.gravity = 0;
                        gp.enemy.ySpeed = 0;
                        gp.enemy.jumps = 0;
                        break;
                    }
                    else{ 
                        
                        gp.enemy.gravity = 2f;
                    }
                }
            }
            for(int i = 0;i<gp.blocks.size();i++){
                gp.blocks.get(i).x += gp.blocks.get(i).SpeedX;
                gp.blocks.get(i).y += gp.blocks.get(i).SpeedY;
                if(gp.blocks.get(i).collision){
                    Rectangle temp = gp.coll.getCollision(new Rectangle(gp.enemy.x,gp.enemy.y,gp.enemy.width,gp.enemy.height), new Rectangle(gp.blocks.get(i).x,gp.blocks.get(i).y,gp.blocks.get(i).width,gp.blocks.get(i).height));
                    gp.enemy.x = temp.x;
                    gp.enemy.y = temp.y;

                    for(int j = 0;j<gp.bullets.size();j++){
                        if(gp.coll.isIntersect(new Rectangle(gp.bullets.get(j).x,gp.bullets.get(j).y,gp.bullets.get(j).width,gp.bullets.get(j).height), new Rectangle(gp.blocks.get(i).x,gp.blocks.get(i).y,gp.blocks.get(i).width,gp.blocks.get(i).height)))
                        {
                               gp.bullets.remove(j);

                        }
                    }
                }
            }
            for(int i = 0; i < gp.bullets.size(); i++) {
                gp.bullets.get(i).shoot();
                gp.bullets.get(i).y += gp.bullets.get(i).ySpeed;
                gp.bullets.get(i).ySpeed += gp.bullets.get(i).gravity;
                if(gp.bullets.get(i).killBullet) {
                    gp.bullets.remove(i);
                    System.out.println("bullet removed");
                }
            }
            
            
        if(gp.movingLeft){
             if(gp.enemy.x <= (Tempo.width/2)){
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).x += 10;
                            }
                            for(int i = 0;i<gp.clouds.size();i++){
                                gp.clouds.get(i).x += 10;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).x += 10;
                            }
                            gp.player.x += 10;
                        }else{
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).SpeedX = 0;
                            }
                            gp.enemy.x -= 10.0;
                        }  
             
             
        }
        if(gp.movingRight){
                if(gp.enemy.x >= (Tempo.width/2)){
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).x += -10;
                            }
                            for(int i = 0;i<gp.clouds.size();i++){
                                gp.clouds.get(i).x += -10;
                            }
                            for(int i = 0;i<gp.bullets.size();i++){
                                gp.bullets.get(i).x += -10;
                            }
                            gp.player.x += -10;
                        }else{
                            for(int i = 0;i<gp.blocks.size();i++){
                                gp.blocks.get(i).SpeedX = 0;
                            }
                        gp.enemy.x += 10.0;
                        }
                }
        }
        if(MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gp.enemy.width/2)){
            gp.enemy.currentHand = gp.enemy.width - 15;
        }
        else{
            gp.enemy.currentHand = gp.enemy.width - 85;
        }

        gp.repaint();
        }
        /*
        public void render() {
            gp.repaint();
        }
    */
}
