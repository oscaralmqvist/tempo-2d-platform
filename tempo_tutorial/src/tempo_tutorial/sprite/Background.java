/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo_tutorial.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elev
 */
public class Background extends Sprite{
    Player p;
    
    public Background(float speed, int x, int y, int width, int height, BufferedImage image, Player p){
        super(x,y,width,height, image, speed, 0f, 0f);
        this.p = p;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(super.getImage(), super.getRectangle().x, super.getRectangle().y,super.getRectangle().width, super.getRectangle().height, null);
        g.drawImage(super.getImage(), super.getRectangle().x + super.getRectangle().width, super.getRectangle().y,super.getRectangle().width, super.getRectangle().height, null);
        if(getRectangle().x < -1280){
            getRectangle().x = 0;
        }
        if(getRectangle().x > 0){
            getRectangle().x = -1280;
        }
    }
    public void moveLeft() {
        getRectangle().x += p.getVelocityX() * getVelocityX();
    }
    public void moveRight() {
        getRectangle().x -= p.getVelocityX() * getVelocityX();
    }
    
}