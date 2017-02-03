
package tempo_tutorial;

public class GameEngine implements Runnable{
    
    GamePanel gp;
    Collision coll = new Collision();
    
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
            
            
            try {
                Thread.sleep(16);
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
                render();
            }
            
            if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + "ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
            
        }
    }
    
    public void tick() {
        movement();
        checkCollision();
    }
    
    public void movement() {
        gp.getPlayer().tick();
    }
    public void checkCollision(){
        for(int i = 0;i<gp.getLevel().getBlocks().size();i++){
                if(coll.getTopCollision(gp.getPlayer().getRectangle(),gp.getLevel().getBlocks().get(i).getRectangle()) && gp.getPlayer().getVelocityY() > 0){
                    gp.getPlayer().setGravity(0);
                    gp.getPlayer().setVelocityY(0);
                    break;
                } else if(coll.getTopCollision(gp.getPlayer().getRectangle(),gp.getLevel().getBlocks().get(i).getRectangle()) && gp.getPlayer().getVelocityY() < 0){
                    gp.getPlayer().setGravity(0);
                    gp.getPlayer().setVelocityY(0);
                } else { 
                    gp.getPlayer().setGravity(2f);
                }
        }
    } 
    public void render() {
        gp.repaint();
    }
}
