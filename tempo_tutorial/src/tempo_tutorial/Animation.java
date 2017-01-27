
package tempo_tutorial;

import tempo_tutorial.sprite.Sprite;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    
    private Sprite sprite;
    private ArrayList<BufferedImage> images;
    private int ticks = 0;
    private int currentImage;
    
    /**
     * Deklarerar sprite
     * @param sprite Föremål/karaktär som ska animeras
     */
    
    public Animation(Sprite sprite) {
        this.sprite = sprite;
    }
    
    /**
     * Skapar animation
     * @param images bilder som skapar animation
     */
    
    public void setCurrentAnimation(BufferedImage ... images) {
        //Nollställer värden
        currentImage = 0;
        this.images.clear();
        
        //Lägger in alla parameter-bilder i class-variablen
        for(BufferedImage image : images) {
            this.images.add(image);
        }
        
        /**
         * TA BORT MELLANSTEG???
         */
        
        //lägger till class-variabeln på sprite
        sprite.setImage(this.images.get(currentImage));
    }
    
    public void tick() {
        ticks++;
        
        //Var sjätte tick ska animationen gå till nästa bild i arrayen
        if(ticks == 6)  {
            //Om vi är på sista bilden, börja om
            if (currentImage == images.size()) {
                currentImage = 0;
            }
            //Byt till nästa bild på sprite
            sprite.setImage(this.images.get(currentImage));
            currentImage++;
            
            //Nollställ ticks
            ticks = 0;
        }
    }
    
}
