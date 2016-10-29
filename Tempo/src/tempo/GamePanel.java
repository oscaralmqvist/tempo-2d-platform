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
    public boolean isPaused, movingLeft, movingRight, movingUp;

    // Längden på sidan av ett block på kartan
    int blockSize = 75;
    
    Spritesheet ss = new Spritesheet();
    
    // Karaktärer skapas och placeras
    Player player = new Player(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(5, 0, 1, 2));
    Player enemy = new Player(600, 420, blockSize, blockSize*2, ss.getSprite(7, 0, 1, 2));
    
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> sky = new ArrayList<Block>();
    ArrayList<Block> clouds = new ArrayList<Block>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    
    public Collision coll = new Collision();
    final Font font = new Font("TimesRoman", Font.PLAIN, 78);

    public GamePanel(){
        setFont(font);
        int row = 0;
        int collumn = 0;
        sky.add(new Block(0, 0, 1200, 740, ss.getSprite(2, 1, 1, 1), false));
        
        //clouds.add(new Block(blockSize*3, blockSize*2, blockSize*2, blockSize, ss.getSprite(3, 2, 2, 1), false));
       // clouds.add(new Block(blockSize*7, blockSize*1, blockSize*2, blockSize, ss.getSprite(5, 2, 2, 1), false));
       // clouds.get(0).SpeedX = 1;
       // clouds.get(1).SpeedX = 2;
        for(int i = 0; i < 4; i++){
            clouds.add(new Block(64+blockSize+(int)(Math.random()*20*i*64), blockSize+(int)(Math.random()*5*i*32), blockSize*2, blockSize, ss.getSprite(3, 2, 2, 1), false)); 
            clouds.get(i).SpeedX = 1;
        }
        
        
        blocks.add(new Block(blockSize * 4, blockSize * 7, blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), false));
        blocks.add(new Block(blockSize * 2, blockSize * 7, blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), false));
        blocks.add(new Block(blockSize * 13, blockSize * 7, blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), false));
        blocks.add(new Block(blockSize * 10, blockSize * 1, 300, 600, ss.getSprite(6, 0, 1, 2), false));
        collumn = 0;
        for(int i = 0;i<64;i++){
            blocks.add(new Block(blockSize * collumn, blockSize * 8, blockSize, blockSize, ss.getSprite(8, collumn % 2 == 1?0:1, 1, 1), false));
            blocks.add(new Block(blockSize * collumn++, blockSize * 9, blockSize, blockSize, ss.getSprite(2, 0, 1, 1), true));
            //blocks.add(new Block(blockSize * collumn++, blockSize * 0, blockSize, blockSize, ss.getSprite(64, 0, 32, 32), true));
        }
        
        addBlock(3,6);
        addBlock(5,5);
        addBlock(7,4);
        addBlock(9,6);
        addBlock(10,3);
        addBlock(11,5);
        
        
        
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i = 0;i<sky.size();i++){
            sky.get(i).paint(g);
        }
        for(int i = 0;i<clouds.size();i++){
            clouds.get(i).paint(g);
        }   
        for(int i = 0;i<blocks.size();i++){
            blocks.get(i).paint(g);
        }
        enemy.paint(g);
        player.paint(g);
        for(Bullet bullet : bullets) {
            bullet.paint(g);
        }
        for(int i = 0; i < enemy.nuts ; i++){
            g.drawImage(ss.getSprite(2, 2, 1, 1), 100+38*i, 680, this);
        }
        if( enemy.nuts > 0){
           g.drawImage(ss.getSprite(2, 2, 1, 1),enemy.x + enemy.currentHand,enemy.y + enemy.height/2,this);
        }
        
        if(isPaused){
            g.drawString("PAUSED", 400, 350);
        }
    }  
    public void addBlock(int x, int y){
        for(int i = 0;i<9-y;i++){
            //blocks.add(new Block(blockSize * x, blockSize * (y + i), blockSize, blockSize, ss.getSprite(32, 64, 32, 32), false));
        }
        
        blocks.add(new Block(blockSize * x, blockSize * (y-1), blockSize, blockSize, ss.getSprite(8, 0, 1, 1), false));
        blocks.add(new Block(blockSize * x, blockSize * y, blockSize, blockSize, ss.getSprite(2, 0, 1, 1), true));
        blocks.add(new Block(blockSize * x, blockSize * (y+1), blockSize, blockSize, ss.getSprite(0, 2, 1, 1), false));
        if(Math.random() > 0.5){
            blocks.add(new Block(blockSize * x, blockSize * (y-2), blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), false));
        }
        
    }
}
