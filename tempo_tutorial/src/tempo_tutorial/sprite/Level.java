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
import tempo_tutorial.Spritesheet;

/**
 *
 * @author Elev
 */
public class Level {
    private ArrayList<Block> level = new ArrayList<Block>();
    private Spritesheet ss;
    private int blockSize;
    
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
                        case 'g':addBlock(true,j,row,0,0,1,1); break;
                        case 'd':addBlock(true,j,row,1,0,1,1); break;
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
    public void addBlock(boolean coll, int x, int y, int imgX, int imgY, int width,int height){
        level.add(new Block(x*blockSize,y*blockSize,width*blockSize,height*blockSize,ss.getSprite(imgX, imgY, width, height),coll));
    }
    public ArrayList<Block> getBlocks(){
        return level;
    }
      public void paint(Graphics2D g) {
        for(Block b : level){
            b.paint(g);
        }
    }
}
