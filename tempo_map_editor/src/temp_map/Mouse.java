
package temp_map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Mouse implements MouseListener {

    private MapEditor mp;
    private boolean pressing;
    
    public Mouse(MapEditor mp) {
        this.mp = mp;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
