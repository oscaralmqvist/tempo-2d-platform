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
    public int jumps = 0;
    public int nuts = 5;
    public int maxNuts = 5;
    public boolean reloading, charging, player, flipped;
    public int currentHand, reloadTick, chargeTick, chargeSpeed, lives;
    public BufferedImage icon;
    public ArrayList<Health> health = new ArrayList<Health>();
    public ArrayList<Health> reload = new ArrayList<Health>();
    public ArrayList<Health> charge = new ArrayList<Health>();
    
    public Block checkpoint;
    public boolean resetScreen = false;
    public int spawnDiff =0;
    
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
        if (flipped) g.drawImage(super.image, super.rect.width + super.rect.x, super.rect.y,-super.rect.width, super.rect.height, null);
        else g.drawImage(super.image, super.rect.x, super.rect.y, super.rect.width, super.rect.height, null);
        
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString("x " + Integer.toString(lives), 90, 50);
        g.drawImage(icon, 15, 17, 72, 45, null);
    }

        public void reload(){
            reloadTick++;
            if(nuts == maxNuts){
                reloading = false;
            }
            else if(reloadTick%2 == 0){
                Health test = new Health(105 + 10*reload.size(),720, 10, 10, null);
                test.setColor(255-10*reload.size(), 30+10*reload.size(), 0);
                reload.add(test);
                
            }
            if(reloadTick > 30){
                    nuts++;
                    reload.clear();
                    reloadTick = 0;
            }          
        }
        public void charge(){
            chargeTick++;
            if(charge.size() < 20){
                if(chargeTick%2 == 0 || chargeTick % 3 == 0){
                    Health test = new Health(500+16*charge.size(),700,16,16,null);
                    test.setColor(30+10*charge.size(),205-10*charge.size(),0);
                    charge.add(test);
                }
            }
            else{
                chargeTick = 0;
                charging = false;
            }
        
            
            
        }
        
    public void die() {
        if (--lives > 0) {
            resetScreen();
            nuts = maxNuts;
        } else {
            super.image = null;
        }
    }
    public void setCheckpoint(Block block){
        if(checkpoint != block){
            checkpoint = block;
        }
    }
    public void resetScreen(){
            spawnDiff = rect.x- checkpoint.rect.x;
            resetScreen = true;
            rect.x = checkpoint.rect.x;
            rect.y = checkpoint.rect.y;
        
    }

}
