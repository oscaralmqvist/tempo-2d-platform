/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo_tutorial.sprite;

import java.awt.image.BufferedImage;

/**
 *
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
