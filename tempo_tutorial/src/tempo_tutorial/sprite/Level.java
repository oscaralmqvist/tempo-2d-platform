/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import tempo_tutorial.Texture;

/**
 *
 * @author Elev
 */
public class Level {
    private ArrayList<Block> level = new ArrayList<Block>();
    private Texture texture;
    private String levelName;
    private int blockSize;
    
    public Level(Texture texture, String level, int blockSize){
        this.texture  = texture;
        this.levelName = level;
        int row = 0;
        int collumn = 0;
        this.blockSize = blockSize;
        
        
        try(BufferedReader br = new BufferedReader(new FileReader(level + ".txt"))){
            String line;
            
            while ((line = br.readLine()) != null) {
                String tempStr = line;
                for(int j= 0; j < tempStr.length(); j++){
                    char symbol = tempStr.charAt(j);
                    switch(symbol){
                        case '-':break;
                        case 'g':addBlock(true,collumn,row,0,0,1,1); break;
                        case 'd':addBlock(true,collumn,row,1,0,1,1); break;
                    }
                    collumn++;
                        if(j == tempStr.length()-1){
                            collumn = 0;
                            row++;
                        }
                    
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
    public void addBlock(boolean coll, int x, int y, int imgX, int imgY, int width,int height){
        level.add(new Block(x*blockSize,y*blockSize,width*blockSize,height*blockSize,texture.getSprite(imgX, imgY, width, height),coll));
    }
      public void paint(Graphics2D g) {
        for(Block b : level){
            b.paint(g);
        }
    }
}
