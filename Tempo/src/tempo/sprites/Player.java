/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

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
    public boolean reloading = false;
    public int currentHand;
    public ArrayList<Health> health = new ArrayList<Health>();

    
    public Player(int x, int y, int width, int height, BufferedImage image, int hp) {
        super(x, y, width, height, image);
        
        for(int i = 0; i < hp; i++){
            health.add(new Health(x + 10*i, (y-10), 8, 8, null));
        }
    }

    @Override
    public void paint(Graphics g) {
       // g.drawRect(super.x, super.y, super.width, super.height);
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
    
    }

        public void reload(){
            if(!reloading){
                reloading = true;
                    new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        nuts = maxNuts;
                        reloading = false;
                    }
                }, 
                1000 
        );
                    }
    }
        
       
    
}
