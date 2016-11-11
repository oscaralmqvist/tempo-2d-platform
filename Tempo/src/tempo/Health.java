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
    private int red = 255;
    private int green = 0;
    private int blue = 0;
    
    public Health(int x, int y, int width, int height, BufferedImage image){
        super(x, y, width, height, image);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(super.x, super.y,super.width, super.height);
    }
    public void setColor(int r,int g, int b){
        red = r;
        green = g;
        blue = b;
    }
}
