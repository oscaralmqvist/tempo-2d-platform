/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elev
 */
public class SetBackground extends Sprites{
    float speed;
    Player p;
    
    public SetBackground(float speed, int x, int y, int width, int height, BufferedImage image, Player p){
        super(x, y, width, height, image);
        this.speed = speed;
        this.p = p;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
        g.drawImage(super.image, super.x + super.width, super.y,super.width, super.height, null);
        if(x < -1280){
            x = 0;
        }
        if(x > 0){
            x = -1280;
        }
    }
    public void moveLeft() {
        x += p.xSpeed * speed;
    }
    public void moveRight() {
        x -= p.xSpeed * speed;
    }
    
}
