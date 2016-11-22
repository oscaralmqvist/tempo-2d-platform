
package tempo.sprites;

import java.awt.image.BufferedImage;


public class Checkpoint extends Block {
    private boolean checked;
    private int tick;
    
    
    public Checkpoint(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height, image, false);
        checked = false;
    }
    
    public void tick(){
    tick++;
    }
    
    public void checked(){
        checked = false;
    }
    
    public int getTick(){
        return tick;
    }
    
    public boolean getChecked(){
        return checked;
    }
}
