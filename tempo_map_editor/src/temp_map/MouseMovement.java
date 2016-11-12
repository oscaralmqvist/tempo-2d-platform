/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Elev
 */
    public class MouseMovement implements MouseMotionListener {

    private MapEditor mp;
    private Window w;
    
    public MouseMovement(MapEditor mp, Window w) {
        this.mp = mp;
        this.w = w;
    }
        
    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = mp.getMousePosition().x;
        int mouseY = mp.getMousePosition().y;
        System.out.println("Mouse position: " + mouseX + ", " + mouseY);
        for(Block b : mp.blocks) {
            if(mp.isHitRect(mouseX, mouseY, b)) {    
                if(SwingUtilities.isRightMouseButton(e)){
                    b.setID(0);
                } else {
                    b.setID(w.id);
                }
                System.out.println("Block position: " + b.x + ", " + b.y);
                w.repaint();
            }
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
