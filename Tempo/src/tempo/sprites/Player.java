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
    
    public float xSpeed = 0; 
    public float ySpeed = 0;
    public float gravity = 0;
    public int jumps = 0;
    public int nuts = 5;
    public int maxNuts = 5;
    public int lives;
    public boolean reloading = false;
    public boolean player;
    public int currentHand;
    public int ticks;
    public ArrayList<Health> health = new ArrayList<Health>();

    
    public Player(int x, int y, int width, int height, BufferedImage image, int hp, int lives, boolean player) {
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
            ticks++;
            System.out.println(reloading);
            System.out.println(ticks);
            if(nuts == maxNuts){
                reloading = false;
            }
            else if((int)(ticks/8) > 3){
                    nuts++;
                    ticks = 0;
                }
            
                    
                    
        }
        
    public void die() {
        if (--lives > 0) {
            x += 200;
            y = 525;
            nuts = maxNuts;
        } else {
            System.exit(0);
        }
    }
        
          
}
