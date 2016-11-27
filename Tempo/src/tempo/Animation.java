
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
        currentImage = 0;
        this.images.clear();
        for(BufferedImage image : images) {
            this.images.add(image);
        }
        sprite.setImage(this.images.get(currentImage));
    }
    
    public void tick() {
        ticks++;
        if(ticks == 6)  {
            /*if(currentImage == 0) {
                changeImage = 1;
            } else if (currentImage == images.size()-1) {
                changeImage = -1;
            }*/
            if (currentImage == images.size()) {
                currentImage = 0;
            }
            sprite.setImage(this.images.get(currentImage));
            //currentImage += changeImage;
            currentImage++;
            ticks = 0;
        }
    }
    
    
    
}
