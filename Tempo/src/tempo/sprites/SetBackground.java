/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elev
 */
public class SetBackground extends Sprites{
    Player p;
    
    public SetBackground(float speed, int x, int y, int width, int height, BufferedImage image, Player p){
        super(new Rectangle(x,y,width,height), image, speed, 0, 0);
        this.p = p;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.rect.x, super.rect.y,super.rect.width, super.rect.height, null);
        g.drawImage(super.image, super.rect.x + super.rect.width, super.rect.y,super.rect.width, super.rect.height, null);
        if(rect.x < -1280){
            rect.x = 0;
        }
        if(rect.x > 0){
            rect.x = -1280;
        }
    }
    public void moveLeft() {
        rect.x += p.getVelocityX() * getVelocityX();
    }
    public void moveRight() {
        rect.x -= p.getVelocityX() * getVelocityX();
    }
    
}
