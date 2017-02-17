package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import tempo_tutorial.Spritesheet;

/**
 * 
 * @author Elev
 */
public class Level {
    private ArrayList<Block> level = new ArrayList<Block>();
    private ArrayList<PickUp> pickup = new ArrayList<>();
    private Spritesheet ss;
    private int blockSize;
    
    /**
     * Läser in en level från en textfil
     * @param texture Ett Spritesheet för att kunna rita ut block
     * @param level namnet på level filen
     * @param blockSize storleken på blocket
     */
    public Level(Spritesheet texture, String level, int blockSize){
        this.ss  = texture;
        int row = 0;
        this.blockSize = blockSize;
        
        
        try(BufferedReader br = new BufferedReader(new FileReader(level + ".txt"))){
            String line;
            
            while ((line = br.readLine()) != null) {
                String tempStr = line;
                for(int j= 0; j < tempStr.length(); j++){
                    char symbol = tempStr.charAt(j);
                    switch(symbol){
                        case '-':break;
                        case 'g':
                            addBlock(true,j,row,0,0,1,1);
                            if(Math.random() > 0.5){addBlock(false,j,row-1,1,4,1,1);
                            }else{addBlock(false,j,row-1,0,4,1,1);} break;
                        case 'd':addBlock(true,j,row,1,0,1,1); break;
                        case 't':addPickup(false,j,row,13,1,1,1); break;
                    }
                        if(j == tempStr.length()-1){
                            row++;
                        }
                    
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
    /**
     * 
     * @param coll
     * @param x
     * @param y
     * @param imgX
     * @param imgY
     * @param width
     * @param height 
     */
    public void addBlock(boolean coll, int x, int y, int imgX, int imgY, int width,int height){
        level.add(new Block(x*blockSize,y*blockSize,width*blockSize,height*blockSize,ss.getSprite(imgX, imgY, width, height),coll));
    }
    /**
     * 
     * @param coll
     * @param x
     * @param y
     * @param imgX
     * @param imgY
     * @param width
     * @param height 
     */
    public void addPickup(boolean coll, int x, int y, int imgX, int imgY, int width,int height){
        pickup.add(new PickUp(x*blockSize,y*blockSize,width*blockSize,height*blockSize,ss.getSprite(imgX, imgY, width, height),coll));
    }
    /**
     * 
     * @return Skärmen
     */
    public ArrayList<Block> getBlocks(){
        return level;
    }
    
    /**
     * 
     * @param g 
     */
     public void paint(Graphics2D g) {
        for(Block b: level){
            b.paint(g);
     }
        for(PickUp p: pickup){
            p.paint(g);
        }
    }
     
     /**
      * 
      * @return 
      */
    public ArrayList<PickUp> getPickup(){
        return pickup; 
    }
}
