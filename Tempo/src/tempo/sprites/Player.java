/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.*;


public class Player extends Sprites {

    public Player(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height, image);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(super.x, super.y, super.width, super.height);
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
    
    }
    
    
    
}
