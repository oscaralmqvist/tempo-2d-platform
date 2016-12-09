package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import tempo.Tempo;


public class MenuState extends State {
    
    private BufferedImage background, mountains, clouds, trees, bushes, logo;
    
    public MenuState() {
        try{
            background = ImageIO.read(new File("src/resources/pics/sky.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }
        
        try{
            mountains = ImageIO.read(new File("src/resources/pics/mountains_final1.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }
        
        try{
            clouds = ImageIO.read(new File("src/resources/pics/clouds.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }
        
        try{
            trees = ImageIO.read(new File("src/resources/pics/trees.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }
        
        try{
            bushes = ImageIO.read(new File("src/resources/pics/bushes.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }
        
        try{
            logo = ImageIO.read(new File("src/resources/pics/Dawn copy.png"));
        }catch(Exception e){
            System.out.println("Could not find background_image");
        }

    }
    
    @Override
    public void paint(Graphics g) {
       g.drawImage(background, 0, 0, Tempo.width, Tempo.height, null);
       g.drawImage(clouds, 0, 0, Tempo.width, Tempo.height, null);
       g.drawImage(mountains, 0, 0, Tempo.width, Tempo.height, null);
       g.drawImage(trees, 0, 0, Tempo.width, Tempo.height, null);
       g.drawImage(bushes, 0, 0, Tempo.width, Tempo.height, null);
       g.drawImage(logo, 400, 200, 400, 200, null);
    }
    
    
    
}
