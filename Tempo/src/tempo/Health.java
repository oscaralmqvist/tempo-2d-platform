/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tempo.sprites.Sprites;

/**
 *
 * @author Oscar
 */
public class Health extends Sprites{
   // GamePanel gp;
    
    public Health(int x, int y, int width, int height, BufferedImage image){
        super(x, y, width, height, image);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(super.x, super.y,super.width, super.height);
    }
}
