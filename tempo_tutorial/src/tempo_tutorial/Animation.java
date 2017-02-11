
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
        
        //Var sjätte tick ska animationen gå till nästa bild i arrayen
        if(++ticks >= interval)  {
            //Om vi är på sista bilden på spring-animationen, börja om
            if (currentImage > endImage || currentImage <= startImage) {
                currentImage = startImage;
            }
            
            //Byt till nästa bild på player
            player.setImage(this.images.get(currentImage));
            currentImage++;

            //Nollställ ticks-variabel
            ticks = 0;
        }
        
        startImage = 0;
        endImage = 5;
        interval = 6;
        
        //Om player står still på marken, sätts inaktiv bild istället för spring-animation
        if (!player.getMovingLeft() && !player.getMovingRight()) {
            startImage = 6;
            endImage = 8;
            interval = 10;
        }
        
        //Om player är i luften, sätts hopp- eller fall-bild efter villkor istället för spring-animation
        if (player.getJumps() < 1) {
            if (player.getJumping()) {
                startImage = 9;
                endImage = 9;
            } 
            if (player.getFalling()) {
                startImage = 10;
                endImage = 10;
            }
        }
    }
    
    public void setInterval(int i) {
        interval = i;
        ticks = 0;
    }
}
