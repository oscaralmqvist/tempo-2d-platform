/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tempo.Collision;
import tempo.Spritesheet;

/**
 *
 * @author Elev
 */
public class Block extends Sprites{
    
    public boolean collision;
    public double SpeedX;
    public double SpeedY;
    public boolean killBlock = false;
    
    
    public Block(int x, int y, int width, int height, BufferedImage image, boolean collision){
        super(x, y, width, height, image);
        this.collision = collision;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
    }
    
}
