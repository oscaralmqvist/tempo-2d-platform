
package tempo_tutorial;

import tempo_tutorial.sprite.Player;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    
    private Player player;
    private ArrayList<BufferedImage> images;
    private int ticks = 0;
    private int interval = 6;
    private int currentImage, startImage, endImage;
    
    /**
     * Deklarerar player
     * @param player Föremål/karaktär som ska animeras
     */
    
    public Animation(Player player) {
        this.player = player;
        images = new ArrayList<BufferedImage>();
    }
    
    /**
     * Sätter animation
     * @param images bilder som skapar animation
     */
    
    public void setAnimation(BufferedImage ... images) {
        //Nollställer värden
        currentImage = 0;
        this.images.clear();
        
        //Lägger in alla parameter-bilder i class-variablen
        for(BufferedImage image : images) {
            this.images.add(image);
        }
        
        //lägger till class-variabeln på player
        player.setImage(this.images.get(currentImage));
    }
    
    public void tick() {
        
        //SÄTTER NÄSTA BILD
        
            //Var sjätte tick ska animationen gå till nästa bild i arrayen
            if(++ticks >= interval)  {
                //Om vi är på sista bilden på animationen, börja om
                if (currentImage > endImage || currentImage <= startImage) {
                    currentImage = startImage;
                }

                //Byt till nästa bild på player
                player.setImage(images.get(currentImage));
                currentImage++;

                //Nollställ ticks-variabel
                ticks = 0;
            }
        
        
        //BESTÄMMER NÄSTA BILD
        
            //Som standard, ska spring-animationen sättas
            setImages(0,5);

            //Om player står still på marken, sätts inaktiv animation
            if (!player.getMovingLeft() && !player.getMovingRight()) {
                setImages(6,8);
            }

            //Om player är i luften, sätts hopp- eller fall-bild efter villkor
            if (player.getJumps() < 1) {
                if (player.getJumping()) {
                    setImages(9,9);
                } 
                if (player.getFalling()) {
                    setImages(10,10);
                }
            }
    }
    
    public void setImages(int start, int end) {
        startImage = start;
        endImage = end;
    }
}
