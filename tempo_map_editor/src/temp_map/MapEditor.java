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
    
    public int blockSize = 30;
    public int mapX = 10;
    public int mapY = 10;
    public Spritesheet ss;
    BufferedImage dirt;
    BufferedImage grass;
    public ArrayList<BlockData> blocksdata;
    
    public ArrayList<Block> blocks = new ArrayList<Block>();
    
    public MapEditor(Spritesheet ss, ArrayList<BlockData> blocksdata) {
        this.ss = ss;
        this.blocksdata = blocksdata;
        dirt = ss.getSprite(1, 2, 1, 1);
        grass = ss.getSprite(2, 0, 1, 1);
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
            for(BlockData bd : blocksdata) {
                if(b.id == bd.getID())
                    g.drawImage(bd.getImage(), b.x, b.y, blockSize, blockSize, null);
            }
        }
       
    }
    
    
    
    
}
