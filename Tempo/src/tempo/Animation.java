
package tempo;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tempo.sprites.Sprites;

public class Animation {
    
    private Sprites sprite;
    private ArrayList<BufferedImage> images;
    private int ticks = 0;
    private int currentImage = 0;
    private int changeImage = 1;
    
    public Animation(Sprites sprite) {
        this.sprite = sprite;
        images = new ArrayList<BufferedImage>();
    }
    
    public void setCurrentAnimation(BufferedImage ... images) {
        this.images.clear();
        for(BufferedImage image : images) {
            this.images.add(image);
        }
        sprite.setImage(this.images.get(currentImage));
    } 
    
    public void tick() {
        ticks++;
        if(ticks == 8)  {
            if(currentImage == 0/*currentImage < images.size()-1*/) {
                changeImage = 1;
            } else if (currentImage == images.size()-1) {
                changeImage = -1;
            }
            sprite.setImage(this.images.get(currentImage));
            currentImage += changeImage;
            ticks = 0;
        }
    }
    
    
    
}
