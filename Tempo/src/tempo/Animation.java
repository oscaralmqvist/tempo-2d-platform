
package tempo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tempo.sprites.Sprites;

public class Animation {
    
    private Sprites sprite;
    private ArrayList<BufferedImage> images;
    private int ticks = 0;
    private int currentImage;
    
    public Animation(Sprites sprite) {
        this.sprite = sprite;
        images = new ArrayList<BufferedImage>();
    }
    
    public void setCurrentAnimation(BufferedImage ... images) {
        this.images.clear();
        for(BufferedImage image : images) {
            this.images.add(image);
        }
        currentImage = 0;
        ticks = 0;
        sprite.setImage(this.images.get(currentImage));
    } 
    
    public void tick() {
        ticks++;
        if(ticks == 30)  {
            if(currentImage < images.size()-1) {
                ticks = 0;
                currentImage++;
                sprite.setImage(this.images.get(currentImage));
            } else {
                currentImage = 0;
                ticks = 0;
                sprite.setImage(this.images.get(currentImage));
            }
        }
    }
    
    
    
}
