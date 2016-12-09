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
public class Block extends Sprites{
    
    private boolean collision;
    private boolean killBlock = false;
    
    
    public Block(int x, int y, int width, int height, BufferedImage image, boolean collision){
        super(new Rectangle(x,y,width,height), image, 0, 0, 0);
        this.collision = collision;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.rect.x, super.rect.y,super.rect.width, super.rect.height, null);
    }
    public boolean getCollision(){
        return collision;   
    }
    public boolean getKillBlock(){
        return killBlock;
    }
    
}
