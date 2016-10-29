/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tempo.Collision;

/**
 *
 * @author Elev
 */
public class Block extends Sprites{
    
    public boolean collision;
    public int SpeedX = 0;
    public int SpeedY = 0;
    
    
    public Block(int x, int y, int width, int height, BufferedImage image, boolean collision){
        super(x, y, width, height, image);
        this.collision = collision;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
    }
    
}
