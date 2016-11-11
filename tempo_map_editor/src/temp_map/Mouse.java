
package temp_map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Mouse implements MouseListener {

    private MapEditor mp;
    private Window w;
    
    public Mouse(MapEditor mp, Window w) {
        this.mp = mp;
        this.w = w;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = mp.getMousePosition().x;
        int mouseY = mp.getMousePosition().y;
        System.out.println("Mouse position: " + mouseX + ", " + mouseY);
        for(Block b : mp.blocks) {
            if(mp.isHitRect(mouseX, mouseY, b)) {
                b.setID(w.id);
                System.out.println("Block position: " + b.x + ", " + b.y);
                w.repaint();
                new CreateMap(mp.blocks, mp.blockSize);
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
