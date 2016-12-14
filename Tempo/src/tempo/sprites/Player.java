/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.*;
import java.util.ArrayList;
import tempo.Health;


public class Player extends Sprites {
    public int currentHand;
    
    private int jumps = 0;
    private int nuts = 5;
    private int maxNuts = 5;
    private boolean reloading;
    private boolean charging;
    private boolean player;
    private boolean flipped;
    private int reloadTick;
    private int chargeTick;
    private int chargeSpeed;
    private int lives;
    private BufferedImage icon;
    private ArrayList<Health> health = new ArrayList<Health>();
    private ArrayList<Health> reload = new ArrayList<Health>();
    private ArrayList<Health> charge = new ArrayList<Health>();
    private Block checkpoint;
    private boolean resetScreen = false;
    private int spawnDiff = 0;
    
    public Player(int x , int y, int width, int height, BufferedImage image, int hp, int lives, boolean player, float dx, float dy, float gravity) {
        super(new Rectangle(x,y,width,height), image, dx, dy, gravity);
        
        for(int i = 0; i < hp; i++){
            health.add(new Health(x + 10*i, (y-10), 8, 8, null));
        }
        
        this.lives = lives;
        this.player = player;
        this.icon = image.getSubimage(0, 0, 32, 20);
    }

    @Override
    public void paint(Graphics g) {
        if (isFlipped()) g.drawImage(super.image, super.rect.width + super.rect.x, super.rect.y,-super.rect.width, super.rect.height, null);
        else g.drawImage(super.image, super.rect.x, super.rect.y, super.rect.width, super.rect.height, null);
        
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString("x " + Integer.toString(getLives()), 90, 50);
        g.drawImage(getIcon(), 15, 17, 72, 45, null);
    }

        public void reload(){
            setReloadTick(getReloadTick() + 1);
            if(getNuts() == getMaxNuts()){
                setReloading(false);
            }
            else if(getReloadTick()%2 == 0){
                Health test = new Health(105 + 10*getReload().size(),720, 10, 10, null);
                test.setColor(255-10*getReload().size(), 30+10*getReload().size(), 0);
                getReload().add(test);
                
            }
            if(getReloadTick() > 30){
                    setNuts(getNuts() + 1);
                    getReload().clear();
                    setReloadTick(0);
            }          
        }
        public void charge(){
            setChargeTick(getChargeTick() + 1);
            if(getCharge().size() < 20){
                if(getChargeTick()%2 == 0 || getChargeTick() % 3 == 0){
                    Health test = new Health(500+16*getCharge().size(),700,16,16,null);
                    test.setColor(30+10*getCharge().size(),205-10*getCharge().size(),0);
                    getCharge().add(test);
                }
            }
            else{
                setChargeTick(0);
                setCharging(false);
            }
        
            
            
        }
        
    public boolean respawn() {
        if (--lives > 0) {
            resetScreen();
            setNuts(getMaxNuts());
            return true;
        }
        return false;
    }
    public void setCheckpoint(Block block){
        if(checkpoint != block){
            checkpoint = block;
        }
    }
    public void resetScreen(){
            setSpawnDiff(rect.x - getCheckpoint().rect.x);
            setResetScreen(true);
            rect.x = getCheckpoint().rect.x;
            rect.y = getCheckpoint().rect.y;
        
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getNuts() {
        return nuts;
    }

    public void setNuts(int nuts) {
        this.nuts = nuts;
    }

    public int getMaxNuts() {
        return maxNuts;
    }

    public void setMaxNuts(int maxNuts) {
        this.maxNuts = maxNuts;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    public boolean isCharging() {
        return charging;
    }

    public void setCharging(boolean charging) {
        this.charging = charging;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public int getReloadTick() {
        return reloadTick;
    }

    public void setReloadTick(int reloadTick) {
        this.reloadTick = reloadTick;
    }

    public int getChargeTick() {
        return chargeTick;
    }

    public void setChargeTick(int chargeTick) {
        this.chargeTick = chargeTick;
    }

    public int getChargeSpeed() {
        return chargeSpeed;
    }

    public void setChargeSpeed(int chargeSpeed) {
        this.chargeSpeed = chargeSpeed;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public ArrayList<Health> getHealth() {
        return health;
    }

    public void setHealth(ArrayList<Health> health) {
        this.health = health;
    }

    public ArrayList<Health> getReload() {
        return reload;
    }

    public void setReload(ArrayList<Health> reload) {
        this.reload = reload;
    }

    public ArrayList<Health> getCharge() {
        return charge;
    }

    public void setCharge(ArrayList<Health> charge) {
        this.charge = charge;
    }

    public Block getCheckpoint() {
        return checkpoint;
    }

    public boolean isResetScreen() {
        return resetScreen;
    }

    public void setResetScreen(boolean resetScreen) {
        this.resetScreen = resetScreen;
    }

    public int getSpawnDiff() {
        return spawnDiff;
    }

    public void setSpawnDiff(int spawnDiff) {
        this.spawnDiff = spawnDiff;
    }

}
