/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Elev
 */
public class Spritesheet {
    BufferedImage sheet = null;
    
    public Spritesheet(){
        try{
            BufferedImage sheet = ImageIO.read(new File("src/resources/temp.png"));
        }catch(Exception e){

        }
    }
    public BufferedImage getSprite(int x1, int y1, int x2, int y2){
        return sheet.getSubimage(x1, y1, x2, y2);
    }

    
}
