package temp_map;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Spritesheet {
    
    BufferedImage sheet = null;
    
    public Spritesheet(){
        try{
            sheet = ImageIO.read(new File("src/resources/sheet.png"));
        }catch(Exception e){

        }
    }
    public BufferedImage getSprite(int row, int column, int height, int width){
        // längden på en rad/kolumn i spritesheet
        int a = 32;
        return sheet.getSubimage(row*a, column*a, height*a, width*a);
    }
    
    
    
}
