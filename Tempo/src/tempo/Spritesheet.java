/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Elev
 */
public class Spritesheet {
    BufferedImage sheet = null;
    
    public Spritesheet(){
        try{
            sheet = ImageIO.read(new File("src/resources/sheet_new.png"));
        }catch(Exception e){

        }
    }
    public BufferedImage getSprite(int row, int column, int height, int width){
        // längden på en rad/kolumn i spritesheet
        int a = 32;
        try {
            return sheet.getSubimage(row*a, column*a, height*a, width*a);
        } catch(Exception e) {
            return sheet.getSubimage(3*a, a, a, a);
        }
    }

    
}
