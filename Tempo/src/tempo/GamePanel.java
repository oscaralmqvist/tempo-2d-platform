/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import tempo.sprites.*;

/**
 *
 * @author Elev
 */
public class GamePanel extends JPanel{
    public boolean isPaused;
    
    int blockSize = 75;
    
    Spritesheet ss = new Spritesheet();
    Player player = new Player(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(160, 0, 32, 64));
    Player enemy = new Player(100, 420, blockSize, blockSize*2, ss.getSprite(224, 0, 32, 64));
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> sky = new ArrayList<Block>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public Collision coll = new Collision();
    
    public GamePanel(){
        int row = 0;
        int collumn = 0;
        for(int i = 0;i<90;i++){
            sky.add(new Block(100 * collumn++, 100 * row, 100, 100, ss.getSprite(64, 32, 32, 32), false));
            if(collumn > 11){
                collumn = 0;
                row++;
            }
        }
        blocks.add(new Block(blockSize * 5, blockSize * 7, blockSize, blockSize*2, ss.getSprite(192, 0, 32, 64), false));
        blocks.add(new Block(blockSize * 2, blockSize * 7, blockSize, blockSize*2, ss.getSprite(192, 0, 32, 64), false));
        blocks.add(new Block(blockSize * 7, blockSize * 7, blockSize, blockSize*2, ss.getSprite(192, 0, 32, 64), false));
        blocks.add(new Block(blockSize * 13, blockSize * 7, blockSize, blockSize*2, ss.getSprite(192, 0, 32, 64), false));
        blocks.add(new Block(blockSize * 4, blockSize * 3, blockSize, blockSize*2, ss.getSprite(192, 0, 32, 64), false));
        blocks.add(new Block(blockSize * 10, blockSize * 1, 300, 600, ss.getSprite(192, 0, 32, 64), false));
        collumn = 0;
        for(int i = 0;i<18;i++){
            blocks.add(new Block(blockSize * collumn, blockSize * 8, blockSize, blockSize, ss.getSprite(256, collumn % 2 == 1?0:32, 32, 32), false));
            blocks.add(new Block(blockSize * collumn, blockSize * 9, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
            blocks.add(new Block(blockSize * collumn++, blockSize * 0, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        }
        blocks.add(new Block(blockSize * 4, blockSize * 5, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(blockSize * 6, blockSize * 2, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(blockSize * 8, blockSize * 1, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(blockSize * 8, blockSize * 7, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        
        
        
        
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0;i<sky.size();i++){
            sky.get(i).paint(g);
        }
        for(int i = 0;i<blocks.size();i++){
            blocks.get(i).paint(g);
        }
        enemy.paint(g);
        player.paint(g);
        for(Bullet bullet : bullets) {
            bullet.paint(g);
        }
        
        if(isPaused){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", 400, 350);
        }
    }   
}
