/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo_tutorial;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Elev
 */
public class Texture {
    BufferedImage sheet = null;
    
    public Texture(){
        try {
            sheet = ImageIO.read(new File("src/resources/pics/sheet_new.png"));
        } catch(Exception e) {}
    }
    
    public BufferedImage getSprite(int row, int column, int height, int width){
        // längden på en rad/kolumn i spritesheet i pixlar
        int a = 32;
        try {
            return sheet.getSubimage(row*a, column*a, height*a, width*a);
        } catch(Exception e) {
            return sheet.getSubimage(3*a, 0, a, a);
        }
    }
    
    public BufferedImage getImage(String content){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new File(content));
        } catch(Exception e) {
            System.out.println(content + "-filen kunde inte hittas");
        }
        return tempImage;
    }
    
}
