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
import tempo.Texture;

/**
 *
 * @author Elev
 */
public class Level{
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Checkpoint> checkpoints  = new ArrayList<Checkpoint>();
    private Goal goal;
    private Block spawn;
    FileReader fr;
    BufferedReader out;
    Texture ss;
    
    public Level(Texture ss, String level){
        this.ss = ss;
        int row = 0;
        int collumn = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(level+".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String tempStr = line;
                for(int j = 0;j<tempStr.length();j++){
                    String tempSubStr = tempStr.substring(j, j+1);
                    switch(tempSubStr){
                        case "-":break;
                        case "l":addBlock(true,collumn,row,0,0,1,1);break;
                        case "d":addBlock(true,collumn,row,1,0,1,1);break;
                        case "i":if(Math.random() > 0.5){addBlock(false,collumn,row,0,4,1,1);}else{addBlock(false,collumn,row,1,4,1,1);}break;
                        case "t":addBlock(false,collumn,row,0,5,1,2);break;
                        case "c":addCheckpoint(collumn,row,0,7,2,2);break;
                        case "s":spawn = new Block(collumn * 75, row * 75,75, 75,null, false);break;
                        case "g":goal = new Goal(collumn * 75,row * 75,75,75*4,ss.getSprite(5, 0, 1, 4));break;
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
        getBlocks().add(new Block(x * 75,y * 75,75 * width,75 * height,ss.getSprite(x2, y2, width, height), coll));
    }
    public void addCheckpoint(int x, int y, int x2, int y2, int width, int height){
        getCheckpoints().add(new Checkpoint(x*75, y*75, 75*width, 75*height,ss.getSprite(x2,y2,width,height)));
    }
    
    public void paint(Graphics g) {
        for(Block b : getBlocks()){
            b.paint(g);
        }
    }
    public Block getSpawn(){
        return spawn;
    }


    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }


    public Goal getGoal() {
        return goal;
    }
    
}
