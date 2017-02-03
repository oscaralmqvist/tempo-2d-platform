
package tempo_tutorial;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Spritesheet {
    
    //Variabeln "sheet" kommer vara bilden vi hämtar alla sprites ifrån
    BufferedImage sheet = null;
    
    /**
     * Läser in spritesheet
     */
    
    public Spritesheet(){
        try {
            sheet = ImageIO.read(new File("src/resources/pics/sheet_new.png"));
        } catch(Exception e) {}
    }
    
    /**
     * Hämtar del utav spritesheet utifrån givna värden
     * @param row antal rader
     * @param column antal kolumner
     * @param height höjd i kolumner
     * @param width höjd i rader
     * @return Sprite (BufferedImage)
     */
    
    public BufferedImage getSprite(int row, int column, int height, int width){
        // Längden på en rad/kolumn i spritesheet i pixlar
        int a = 32;
        try {
            //Hämtar sprite
            return sheet.getSubimage(row*a, column*a, height*a, width*a);
        } catch(Exception e) {
            //Hämtar error-sprite om värden går utanför ss storlek
            return sheet.getSubimage(3*a, 0, a, a);
        }
    }
    
    /**
     * Läser in en bild baserat på sökväg
     * @param content sökväg
     * @return Bild (BufferedImage)
     */
    
    public BufferedImage getImage(String content){
        //Deklarerar variabel
        BufferedImage tempImage = null;
        try {
            //Letar efter efterfrågad fil
            tempImage = ImageIO.read(new File(content));
        } catch(Exception e) {
            //Om filen inte hittas, skrivs error-meddelande
            System.out.println(content + "-filen kunde inte hittas");
        }
        return tempImage;
    }
    
}
