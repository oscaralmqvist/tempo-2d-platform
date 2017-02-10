
package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends Sprite {
    private boolean col;
    
    public Block(int x, int y, int width, int height, BufferedImage image, boolean collision) {
        super(x, y, width, height, image, 0, 0, 0);
        col = collision;
    }

    @Override
    public void paint(Graphics2D g) {
        Rectangle r = super.getRectangle();
        g.drawImage(super.getImage(), r.x, r.y, r.width, r.height, null);
    }

    public boolean  getCollision(){
        return col;
    }
    
}
