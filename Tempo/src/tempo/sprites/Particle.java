/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

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
    int size = 10;
    float speed = 0.5f;
    static Random random = new Random();
    float opacity = 1f;
    public boolean killParticle = false;
    
    public Particle(int x, int y,int width, int height, BufferedImage image){
        super(new Rectangle(x,y,width,height),image, (float)((random.nextDouble()*(20)) + 1), -(float)((random.nextDouble()*(20)) + 1), 4f);
        super.setVelocityX(Math.random() > 0.5 ? (super.getVelocityX() * -1) : super.getVelocityX());
    }
    public void Update(){
        rect.y += getVelocityY()*speed;
        addVelocityY(getGravity()*speed);
        
        
        rect.x += getVelocityX()*speed;
        addVelocityX(-getVelocityX()*(getGravity()*0.01f));
        
        size *= (1.01f*getGravity());
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
