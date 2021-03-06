
package tempo_tutorial;

import tempo_tutorial.sprite.Background;
import tempo_tutorial.sprite.PickUp;

public class GameEngine implements Runnable{
    
    GamePanel gp;
    Collision coll = new Collision();
    
    private int prevX = 0;
    private boolean running = false;
    
    public GameEngine(GamePanel gp) {
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
            
            while (delta >= 1) {
                ticks++;
                tick();
                //if(ticks % 2 == 0){
                    render();
                //}
                delta -= 1;
                shouldRender = true;
            }
            
            if(shouldRender) {
                frames++;
                
            }
            
            if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                //System.out.println(ticks + "ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
            
        }
    }
    
    public void tick() {
        moveBackground();
        movement();
        checkCollision();
    }
    
    public void movement() {
        gp.getPlayer().tick();
        gp.getPlayer().getAnimation().tick();
        
        for(PickUp p :  gp.getLevel().getPickup()){
            p.tick();
        }
    }
    public void checkCollision(){
        for(int i = 0;i<gp.getLevel().getBlocks().size();i++){
            if(gp.getLevel().getBlocks().get(i).getCollision()){
                
                if(coll.getTopCollision(gp.getPlayer().getRectangle(),gp.getLevel().getBlocks().get(i).getRectangle()) && gp.getPlayer().getVelocityY() > 0){
                    gp.getPlayer().setGravity(0);
                    gp.getPlayer().setVelocityY(0);
                    gp.getPlayer().setJumps(1);
                    gp.getPlayer().setFalling(false);
                    
                    break;
                }else if(coll.getBottomCollision(gp.getPlayer().getRectangle(),gp.getLevel().getBlocks().get(i).getRectangle()) && gp.getPlayer().getVelocityY() < 0){
                    gp.getPlayer().setGravity(0);
                    gp.getPlayer().setVelocityY(0);
                } else { 
                    gp.getPlayer().setGravity(2f);
                }
                gp.getPlayer().setRectangle(coll.getCollision(gp.getPlayer().getRectangle(), gp.getLevel().getBlocks().get(i).getRectangle()));
            }
        }
        
        for(int i = 0; i<gp.getLevel().getPickup().size();i++){
            if(gp.getPlayer().getRectangle().intersects(gp.getLevel().getPickup().get(i).getRectangle())){
                gp.getLevel().getPickup().remove(i);
            }
        }
    } 
    public void moveBackground(){
        
        if(gp.getPlayer().getRectangle().x < prevX ){
            for(Background b : gp.getBack()){
                b.moveLeft();
            }
        }else if(gp.getPlayer().getRectangle().x > prevX){
            for(Background b : gp.getBack()){
                b.moveRight();
            }
        }
        prevX = gp.getPlayer().getRectangle().x;
    
        
    }
    public void render() {
        gp.repaint();
    }
}
