/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.*;
import java.util.ArrayList;
import tempo.Health;


public class Player extends Sprites {
    
    public float xSpeed = 20; 
    public float ySpeed = 0;
    public float gravity = 0;
    public int jumps = 0;
    public int nuts = 5;
    public int maxNuts = 5;
    public int lives;
    public boolean reloading = false;
    public boolean charging = false;
    public boolean player;
    public int currentHand;
    public int reloadTick;
    public int chargeTick;
    public int chargeSpeed;
    public ArrayList<Health> health = new ArrayList<Health>();
    public ArrayList<Health> reload = new ArrayList<Health>();
    public ArrayList<Health> charge = new ArrayList<Health>();
    
    public Block checkpoint;
    public boolean resetScreen = false;
    public int spawnDiff =0;
    
    public Player(int x , int y, int width, int height, BufferedImage image, int hp, int lives, boolean player) {
        super(x, y, width, height, image);
        
        for(int i = 0; i < hp; i++){
            health.add(new Health(x + 10*i, (y-10), 8, 8, null));
        }
        
        this.lives = lives;
        this.player = player;
    }

    @Override
    public void paint(Graphics g) {
       // g.drawRect(super.x, super.y, super.width, super.height);
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
        
        if (player) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.setColor(Color.BLACK);
            g.drawString("x " + Integer.toString(lives), 75, 50);
            g.drawImage((super.image.getSubimage(6, 0, 20, 20)), 20, 20, 45, 45, null);
        }
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
            spawnDiff = x- checkpoint.x;
            resetScreen = true;
            x = checkpoint.x;
            y = checkpoint.y;
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

}
