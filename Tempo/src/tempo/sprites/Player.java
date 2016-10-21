/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;


public class Player extends Sprites {

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(super.x, super.y, super.width, super.height);
    
    }
    
    
    
}
