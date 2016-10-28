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
    
    Spritesheet ss = new Spritesheet();
    Player player = new Player(100, 100, 100, 200, ss.getSprite(0, 0, 32, 64));
    Player enemy = new Player(100, 420, 100, 200, ss.getSprite(32, 0, 32, 64));
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> sky = new ArrayList<Block>();
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
        collumn = 0;
        for(int i = 0;i<12;i++){
            blocks.add(new Block(100 * collumn, 620, 100, 100, ss.getSprite(64, 0, 32, 32), true));
            blocks.add(new Block(100 * collumn++, 0, 100, 100, ss.getSprite(64, 0, 32, 32), true));
        }
        blocks.add(new Block(100 * 4, 520, 100, 100, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(100 * 6, 320, 100, 100, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(100 * 8, 520, 100, 100, ss.getSprite(64, 0, 32, 32), true));
        blocks.add(new Block(100 * 8, 420, 100, 100, ss.getSprite(64, 0, 32, 32), true));
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
        
        if(isPaused){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", 400, 350);
        }
    }   
}
