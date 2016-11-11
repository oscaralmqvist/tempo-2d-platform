/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JPanel;

/**
 *
 * @author Elev
 */
public class MapEditor extends JPanel {
    
    public int blockSize = 10;
    public int mapX = 35;
    public int mapY = 10;
    public Spritesheet ss;
    BufferedImage dirt;
    
    public ArrayList<Block> blocks = new ArrayList<Block>();
    
    public MapEditor(Spritesheet ss) {
        this.ss = ss;
        dirt = ss.getSprite(2, 0, 1, 1);
        for(int i = 0; i < mapY; i++) {
            for(int j = 0; j < mapX; j++) {
                blocks.add(new Block(j * blockSize, i * blockSize));
            }
        }
    }

    
    public boolean isHitRect(int mouseX, int mouseY, Block b) {
        boolean hit = false;
        if(mouseX > b.x && mouseX < (b.x+blockSize) && mouseY > b.y && mouseY < (b.y+blockSize)) {
            hit = true;
        }
        return hit;    
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for(Block b : blocks) {
            g.drawRect(b.x, b.y, blockSize, blockSize);
            if(b.id == 1) {
                g.drawImage(dirt, b.x, b.y, blockSize, blockSize, null);
            }
        }
       
    }
    
    
    
    
}
