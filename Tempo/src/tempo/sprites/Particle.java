/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import tempo.Tempo;

/**
 *
 * @author Elev
 */
public class Particle extends Sprites{
    float velocityX;
    float velocityY;
    int size = 10;
    float speed = 0.5f;
    float gravity = 4f;
    static Random random = new Random();
    float opacity = 1f;
    public boolean killParticle = false;
    
    public Particle(int x, int y,int width, int height, BufferedImage image){
        super(new Rectangle(x,y,width,height),image);
        velocityX = (float)((random.nextDouble()*(20)) + 1);
        velocityY = -(float)((random.nextDouble()*(20)) + 1);
        
        velocityX = Math.random() > 0.5 ? (velocityX * -1) : velocityX;
        //velocityY = Math.random() > 0.5 ? (velocityY * -1) : velocityY;
        
        //gravity = (float)Math.random() + 0.5f;
    }
    public void Update(){
        rect.y += velocityY*speed;
        velocityY += (gravity*speed);
        
        rect.x += velocityX*speed;
        velocityX -= velocityX*(gravity*0.01f);
        size *= (1.01f*gravity);
        if(opacity > 0.01f){
            opacity -= 0.01f;
        }
        
        if(rect.y < 0 || rect.y > Tempo.height) {
            killParticle = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.rect.x, super.rect.y,super.rect.width, super.rect.height, null);
    }
    
}
