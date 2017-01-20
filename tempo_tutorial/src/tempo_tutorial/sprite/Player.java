
package tempo_tutorial.sprite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
    
    public Player(Rectangle rect, BufferedImage image, float dx, float dy, float gravity) {
        super(rect, image, dx, dy, gravity);
    }

    @Override
    public void paint(Graphics2D g) {
        Rectangle r = super.getRectangle();
        g.drawImage(super.getImage(), r.x, r.y, r.width, r.height, null);
    }
    
}
