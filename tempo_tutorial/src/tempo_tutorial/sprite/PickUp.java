package tempo_tutorial.sprite;
import java.awt.image.BufferedImage;

/**
 * Gör att man kan ta upp objekt (t.ex. Taco)
 * @author Elev
 */
public class PickUp extends Block {
    int maxDistance =10;
    int currentDistance;
    int direction =-1;
    
    int tick;
   
    public PickUp(int x, int y, int width, int height, BufferedImage image, boolean collision) {
        super(x, y, width, height, image, collision);
    }
    public void tick(){
        if((++tick %3) == 0){
            tick =0;
            super.getRectangle().y+=direction;
            currentDistance+=Math.abs(direction);
            if(currentDistance > maxDistance){
                     direction=direction*-1;
                     currentDistance = 0;
            }        
        }
    }
}
