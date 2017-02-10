
package tempo_tutorial;

import tempo_tutorial.sprite.Player;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    
    private Player player;
    private ArrayList<BufferedImage> images;
    private int ticks = 0;
    private int currentImage;
    
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
    
    public void setCurrentAnimation(BufferedImage ... images) {
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
        
        //Var sjätte tick ska animationen gå till nästa bild i arrayen
        if(++ticks == 6)  {
            //Om vi är på sista bilden på spring-animationen, börja om
            if (currentImage == images.size() - 3) {
                currentImage = 0;
            }
            
            //Byt till nästa bild på player
            player.setImage(this.images.get(currentImage));
            currentImage++;

            //Nollställ ticks-variabel
            ticks = 0;
        }
        
        //Om player står still på marken, sätts inaktiv bild istället för spring-animation
        if (!player.getMovingLeft() && !player.getMovingRight()) {
            if (ticks == 5) {
                currentImage = 6;
                player.setImage(this.images.get(currentImage));
                currentImage = 0;
                ticks = 0;
            }
        }
        
        //Om player är i luften, sätts hopp- eller fall-bild efter villkor istället för spring-animation
        if (player.getJumps() < 2) {
            if (player.getJumping()) currentImage = 7;
            if (player.getFalling()) currentImage = 8;
            
            //Applicera image
            player.setImage(this.images.get(currentImage));
            
            //Ställ om animation ifall spring-animation ska köras härnäst
            currentImage = 0;
        }
    }
    
}
