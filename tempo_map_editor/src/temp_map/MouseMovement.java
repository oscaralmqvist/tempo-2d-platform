/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Elev
 */
    public class MouseMovement implements MouseMotionListener {

    private MapEditor mp;
    
    public MouseMovement(MapEditor mp) {
        this.mp = mp;
    }
        
    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = mp.getMousePosition().y;
        System.out.println("Mouse position: " + mouseX + ", " + mouseY);
        for(Block b : mp.blocks) {
            if(mp.isHitRect(mouseX, mouseY, b)) {
                b.setID(1);
                System.out.println("Block position: " + b.x + ", " + b.y);
                mp.repaint();
            }
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
