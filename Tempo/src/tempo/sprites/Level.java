/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import tempo.Spritesheet;

/**
 *
 * @author Elev
 */
public class Level{
    public ArrayList<Block> blocks = new ArrayList<Block>();
    public ArrayList<Block> checkpoints  = new ArrayList<Block>();
    public Block spawn;
    FileReader fr;
    BufferedReader out;
    Spritesheet ss;
    
    public Level(Spritesheet ss, String level){
        this.ss = ss;
        int row = 0;
        int collumn = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(level+".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String tempStr = line;
                System.out.println(tempStr);
                for(int j = 0;j<tempStr.length();j++){
                    String tempSubStr = tempStr.substring(j, j+1);
                    if(tempSubStr.equals("-")){

                    }else if(tempSubStr.equals("l")){
                        addBlock(true,collumn,row,0,0,1,1);
                    }else if (tempSubStr.equals("d")) {
                        addBlock(true,collumn,row,1,0,1,1);
                    }else if(tempSubStr.equals("i")){
                        if (Math.random() > 0.5) {
                            addBlock(false,collumn,row,0,4,1,1);
                        } else {
                            addBlock(false,collumn,row,1,4,1,1);
                        }
                    }else if(tempSubStr.equals("t")){
                        addBlock(false,collumn,row,0,5,1,2);
                    }
                    else if(tempSubStr.equals("c")){
                        addCheckpoint(collumn,row,7,0,1,2);
                    }
                    else if(tempSubStr.equals("s")){
                        spawn = new Block(collumn * 75, row * 75,75, 75,ss.getSprite(0, 5, 1, 1), false);
                    }

                    collumn++;
                    if(j == tempStr.length()-1){
                        collumn = 0;
                        row++;
                    }
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addBlock(boolean coll, int x, int y, int x2, int y2, int width, int height){  
        blocks.add(new Block(x * 75,y * 75,75 * width,75 * height,ss.getSprite(x2, y2, width, height), coll));
    }
    public void addCheckpoint(int x, int y, int x2, int y2, int width, int height){
        checkpoints.add(new Block(x*75, y*75, 75*width, 75*height,ss.getSprite(x2,y2,width,height),false));
    }
    
    public void paint(Graphics g) {
        for(Block b : blocks){
            b.paint(g);
        }
    }
    public Block getSpawn(){
        return spawn;
    }
    
}
