
package tempo;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import states.GameState;
import states.State;
import tempo.sprites.Particle;
/**
*
* @author Oscar Almqvist
*/
public class GameEngine implements Runnable {
    
    private GameState gs;
    private GamePanel gp;
    private double gravity;
    private int ticks;
    private boolean running = false;
    

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
            if(getGp().getStateManager().getStates().peek().getClass() == GameState.class) {

                    gs = getGp().getStateManager().getGameState();
                    ticks++;
                    if(!gs.isIsPaused()){         
                        if(ticks % 4 == 2){
                            gs.getSet().get(1).rect.x += 1;            
                        }
                        checkMovement();
                        checkCollision();
                        checkWeapon();
                        checkDialogue();
                        checkPlayer();
                        resetScreen();
                        gs.getPlayer().getAnimation().tick();
                    }
                    getGp().repaint();
                    if(ticks == 60){
                        ticks = 0;
                    }
                } else {
                    gs = null;
                }
            
            
        }

        public void resetScreen(){
            if(gs.getPlayer().isResetScreen()){
                gs.getPlayer().setResetScreen(false);
                
                for(int l = 0;l<gs.getParticle().size();l++){
                    gs.getParticle().get(l).rect.x += gs.getPlayer().getSpawnDiff();
                }
                for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                    gs.getLevel().getBlocks().get(i).rect.x += gs.getPlayer().getSpawnDiff();
                }
                for(int i = 0;i<gs.getClouds().size();i++){
                    gs.getClouds().get(i).rect.x += gs.getPlayer().getSpawnDiff();
                        gs.getClouds().get(i).rect.x += gs.getPlayer().getSpawnDiff();
                }
                for(int i = 0;i<gs.getBullets().size();i++){
                    gs.getBullets().get(i).rect.x +=gs.getPlayer().getSpawnDiff();
                }
                for(int i = 0; i < gs.getUnits().size(); i++){
                    for(int j = 0;j<gs.getUnits().get(i).getHealth().size();j++){
                        gs.getUnits().get(i).getHealth().get(j).rect.x += gs.getPlayer().getSpawnDiff() + 8;
                    }
                }
                for(int i = 0; i < gs.getUnits().size(); i++){
                    gs.getUnits().get(i).rect.x += gs.getPlayer().getSpawnDiff();
                }
                for(int i = 0; i < gs.getLevel().getCheckpoints().size(); i++){
                    gs.getLevel().getCheckpoints().get(i).rect.x +=gs.getPlayer().getSpawnDiff();
                }
                
                gs.getLevel().getSpawn().rect.x +=gs.getPlayer().getSpawnDiff();
                gs.getLevel().getGoal().rect.x  += gs.getPlayer().getSpawnDiff();
                gs.getPlayer().rect.x+=gs.getPlayer().getSpawnDiff();

            }
        }
        public void checkCollision(){
            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                if(gs.getLevel().getBlocks().get(i).getCollision()){
                    for(int j = 0; j < gs.getUnits().size(); j++){
                        if(((gs.getUnits().get(j).image != null && gs.getColl().getTopCollision(gs.getPlayer().rect ,gs.getUnits().get(j).rect)) || gs.getColl().getTopCollision(gs.getPlayer().rect,gs.getLevel().getBlocks().get(i).rect)) && gs.getPlayer().getVelocityY() > 0){
                          //  gs.player.gravity = 0;
                            gs.getPlayer().setGravity(0);
                            gs.getPlayer().setVelocityY(0);
                            gs.getPlayer().setJumps(0);
                            break;
                        } else if(((gs.getUnits().get(j).image != null && gs.getColl().getTopCollision(gs.getPlayer().rect ,gs.getUnits().get(j).rect)) || gs.getColl().getTopCollision(gs.getPlayer().rect,gs.getLevel().getBlocks().get(i).rect)) && gs.getPlayer().getVelocityY() < 0){
                          //  gs.player.gravity = 0;
                            gs.getPlayer().setGravity(0);
                            gs.getPlayer().setVelocityY(0);
                        } else { 
                          //  gs.player.gravity = 2f;
                            gs.getPlayer().setGravity(2f);
                        }
                    }
                }
            }
            for(int i = 0; i < gs.getLevel().getBlocks().size();i++){
                if(gs.getLevel().getBlocks().get(i).getCollision()){
                    for(int u = 0; u < gs.getUnits().size(); u++){
                        if(gs.getColl().isIntersect( gs.getLevel().getBlocks().get(i).rect,gs.getUnits().get(u).rect)){
                            //gs.units.get(u).xSpeed = -gs.units.get(u).xSpeed;
                            Rectangle u_temp = gs.getColl().getCollision(gs.getUnits().get(u).rect, gs.getLevel().getBlocks().get(i).rect);
                            gs.getUnits().get(u).setVelocityX(-gs.getUnits().get(u).getVelocityX());
                            gs.getUnits().get(u).rect.x = u_temp.x;
                        }
                        //gs.units.get(u).rect.x = u_temp.x;
                    }
                }
            }


            for(int u = 0; u < gs.getUnits().size(); u++){
               // Rectangle u_temp = gs.coll.getCollision(gs.units.get(u).rect, gs.player.rect);
                if(gs.getColl().isIntersect(gs.getUnits().get(u).rect, gs.getPlayer().rect)){
                    gs.getUnits().get(u).setVelocityX(-gs.getUnits().get(u).getVelocityX());
                }
            }

            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                gs.getLevel().getBlocks().get(i).rect.x += gs.getLevel().getBlocks().get(i).getVelocityX();
                gs.getLevel().getBlocks().get(i).rect.y += gs.getLevel().getBlocks().get(i).getVelocityY();
                if(gs.getLevel().getBlocks().get(i).getCollision()){
                    for(int q = 0; q < gs.getUnits().size(); q++){
                    Rectangle temp = gs.getColl().getCollision(gs.getPlayer().rect, gs.getLevel().getBlocks().get(i).rect);
                    gs.getPlayer().rect.x = temp.x;
                    gs.getPlayer().rect.y = temp.y;

                    if(gs.getUnits().get(q).image != null){
                        temp = gs.getColl().getCollision(gs.getPlayer().rect, gs.getUnits().get(q).rect);                    
                        gs.getPlayer().rect.x = temp.x;
                        gs.getPlayer().rect.y = temp.y;
                    }

                    for(int j = 0;j<gs.getBullets().size();j++){
                        if(((gs.getUnits().get(q).image != null && gs.getColl().isIntersect(gs.getBullets().get(j).rect,gs.getUnits().get(q).rect))) || gs.getColl().isIntersect(gs.getBullets().get(j).rect, gs.getLevel().getBlocks().get(i).rect))
                        {
                            boolean test = gs.getColl().isIntersect(gs.getBullets().get(j).rect,gs.getUnits().get(q).rect);
                            gs.getSound().playSound("splat");
                            for(int l = 0;l<25;l++){
                                gs.getParticle().add(new Particle(gs.getBullets().get(j).rect.x, gs.getBullets().get(j).rect.y, 25,25,gs.getSs().getSprite(14, 0, 1, 1)));
                            }
                            if(test && gs.getUnits().get(q).getIsHostile()){
                                gs.getUnits().get(q).loseHealth(Math.round(gs.getBullets().get(j).getVelocityX()/5));
                            }
                            gs.getBullets().remove(j);
                        }
                    }
                    }
                }
            }

            for(int l=0; l < gs.getLevel().getCheckpoints().size();l++){
                if(gs.getColl().isIntersect(gs.getPlayer().rect,gs.getLevel().getCheckpoints().get(l).rect) && !gs.getLevel().getCheckpoints().get(l).getChecked()){
                    gs.getPlayer().setCheckpoint(gs.getLevel().getCheckpoints().get(l));
                    gs.getLevel().getCheckpoints().get(l).checked();
                    gs.getLevel().getCheckpoints().get(l).image = gs.getSs().getSprite(2, 7, 2, 2);
                    while(gs.getLevel().getCheckpoints().get(l).getTick() < 20){
                        int i = (int)(Math.random() * 3);
                        gs.getParticle().add(new Particle(gs.getLevel().getCheckpoints().get(l).rect.x+30, gs.getLevel().getCheckpoints().get(l).rect.y+30, 80,80,gs.getSs().getSprite(13, 1+i, 1, 1)));
                        gs.getLevel().getCheckpoints().get(l).tick();
                    }
                    gs.getSound().playMusic();
                }
            }
            if(gs.getColl().isIntersect(gs.getPlayer().rect, gs.getLevel().getGoal().rect)){
                gs.setCurrentLevel(gs.getCurrentLevel() + 1);
                gs.loadLevel();
            }
        }


        public void checkPlayer(){
            if (gs.getPlayer().rect.y > Tempo.height) {
                gs.getPlayer().die();
                gs.getSound().playSound("neeeej");
            }
        }

        public void checkDialogue(){
            for(int i = 0; i < gs.getDialogue().size(); i++) {
               if(gs.getDialogue().get(i).isDone()) {
                   gs.getDialogue().remove(i);
               } else {
                   gs.getDialogue().get(i).printDialogue();
               }
            }
        }

        public void checkWeapon(){
            if(gs.getPlayer().isReloading()){
                gs.getPlayer().reload();
            }
            if(gs.getPlayer().isCharging()){
                gs.getPlayer().charge();
            }


            for(int l = 0;l<gs.getParticle().size();l++){
                gs.getParticle().get(l).Update();
                if (gs.getParticle().get(l).getKillParticle()) {
                    gs.getParticle().remove(l);
                }
            }
            for(int i = 0; i < gs.getBullets().size(); i++) {
                gs.getBullets().get(i).shoot();
                gs.getBullets().get(i).rect.y += gs.getBullets().get(i).getVelocityY();
                gs.getBullets().get(i).addVelocityY((float)gs.getBullets().get(i).gravity);
                if(gs.getBullets().get(i).killBullet) {
                    gs.getBullets().remove(i);
                }
            }


            if (MouseInfo.getPointerInfo().getLocation().x  > (Tempo.width/2 + gs.getPlayer().rect.width/2)) {
                gs.getPlayer().currentHand = gs.getPlayer().rect.width - 15;
            } else {
                gs.getPlayer().currentHand = gs.getPlayer().rect.width - 85;
            }
            
            if ((!gs.isMovingLeft() && !gs.isMovingRight()) || (gs.isMovingLeft() && gs.isMovingRight())) {
                gs.getPlayer().animation.setCurrentAnimation(gs.getSs().getSprite(4, 0, 1, 2));
            }
        }

        public void checkMovement(){
            gs.getPlayer().rect.y += gs.getPlayer().getVelocityY();  
            for(int i = 0; i < gs.getUnits().size(); i++){
                gs.getUnits().get(i).rect.x += gs.getUnits().get(i).getVelocityX();
                if(gs.getUnits().get(i).getIsHostile()){
                    for(int j = 0;j<gs.getUnits().get(i).getHealth().size();j++){
                        gs.getUnits().get(i).getHealth().get(j).rect.x = gs.getUnits().get(i).rect.x;
                    }
                }
            }
            gs.getPlayer().addVelocityY(gs.getPlayer().getGravity());
            if(gs.isMovingLeft()){
                if(gs.getPlayer().rect.x <= (Tempo.width/2)){
                            for(int l = 0;l<gs.getSet().size();l++){
                                gs.getSet().get(l).moveLeft();
                            }
                            for(int l = 0;l<gs.getParticle().size();l++){
                                gs.getParticle().get(l).rect.x += gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                                gs.getLevel().getBlocks().get(i).rect.x += gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0;i<gs.getClouds().size();i++){
                                gs.getClouds().get(i).rect.x += 1;
                            }
                            for(int i = 0;i<gs.getBullets().size();i++){
                                gs.getBullets().get(i).rect.x += gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0; i < gs.getUnits().size(); i++){
                                for(int j = 0;j<gs.getUnits().get(i).getHealth().size();j++){
                                    gs.getUnits().get(i).getHealth().get(j).rect.x += gs.getPlayer().getVelocityX();
                                }
                            }
                            for(int i = 0; i< gs.getLevel().getCheckpoints().size(); i++){
                                gs.getLevel().getCheckpoints().get(i).rect.x+= gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0; i < gs.getUnits().size(); i++){
                                gs.getUnits().get(i).rect.x += gs.getPlayer().getVelocityX();
                            }
                            gs.getLevel().getSpawn().rect.x+=gs.getPlayer().getVelocityX();
                            gs.getLevel().getGoal().rect.x+=gs.getPlayer().getVelocityX();
                        }else{
                            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                                gs.getLevel().getBlocks().get(i).setVelocityX(0);
                            }
                            gs.getPlayer().rect.x -= gs.getPlayer().getVelocityX();
}               
        }
        if( gs.isMovingRight()){
                if(gs.getPlayer().rect.x >= (Tempo.width/2)){
                            for(int l = 0;l<gs.getSet().size();l++){
                                gs.getSet().get(l).moveRight();
                            }
                            for(int l = 0;l<gs.getParticle().size();l++){
                                gs.getParticle().get(l).rect.x -= gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                                gs.getLevel().getBlocks().get(i).rect.x -= gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0;i<gs.getBullets().size();i++){
                                gs.getBullets().get(i).rect.x -= gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0; i< gs.getLevel().getCheckpoints().size(); i++){
                                gs.getLevel().getCheckpoints().get(i).rect.x -= gs.getPlayer().getVelocityX();
                            }
                            for(int i = 0; i < gs.getUnits().size(); i++){
                                for(int j = 0;j<gs.getUnits().get(i).getHealth().size();j++){
                                    gs.getUnits().get(i).getHealth().get(j).rect.x -= gs.getPlayer().getVelocityX();
                                }
                            }
                            for(int i = 0; i < gs.getUnits().size(); i++){
                                gs.getUnits().get(i).rect.x -= gs.getPlayer().getVelocityX();
                            }
                            gs.getLevel().getSpawn().rect.x -= gs.getPlayer().getVelocityX();
                            gs.getLevel().getGoal().rect.x -= gs.getPlayer().getVelocityX();
                        }else{
                            for(int i = 0;i<gs.getLevel().getBlocks().size();i++){
                                gs.getLevel().getBlocks().get(i).setVelocityX(0);
                            }
                        gs.getPlayer().rect.x += gs.getPlayer().getVelocityX();
                        }
}  
        }


    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }


    public double getGravity() {
        return gravity;
    }


    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
