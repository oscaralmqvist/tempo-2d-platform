/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import tempo.sprites.*;

/**
 *
 * @author Elev
 */
public class GamePanel extends JPanel{
    // FLYTTA TILL PLAYER?
    public boolean isPaused, movingLeft, movingRight, movingUp;

    // Längden på sidan av ett block på kartan
    int blockSize = 75;
    Spritesheet ss = new Spritesheet();
    ArrayList<Npc> units = new ArrayList<Npc>();
    // Karaktärer skapas och placeras
    //public Player player = new Player((Tempo.width/2), 420, blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
    
    Level level = new Level(ss,"level1");
    ArrayList<Block> sky = new ArrayList<Block>();
    ArrayList<Block> clouds = new ArrayList<Block>();
    ArrayList<SetBackground> set = new ArrayList<SetBackground>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Particle> particle = new ArrayList<Particle>();
    ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>(); 
    ArrayList<Sprites> sprites;
    
    public Player player = new Player(level.spawn.x,level.spawn.y,blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
    public Npc enemy = new Npc(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(5, 0, 1, 2), 5, 1, false);
    public Npc enemy_test = new Npc(blockSize*19, blockSize*7, blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), 5, 1, true);
    

    public Collision coll = new Collision();
    final Font font = new Font("TimesRoman", Font.PLAIN, 78);

    public GamePanel(){
        player.setCheckpoint(level.spawn);
        setFont(font);
        int row = 0;
        int collumn = 0;
        sky.add(new Block(0, 0, Tempo.width, Tempo.height, ss.getSprite(0, 3, 1, 1), false));
        units.add(enemy);
        units.add(enemy_test);
        for(int i = 0; i < 4; i++){        
            clouds.add(new Block(64+blockSize+(int)(Math.random()*20*i*64), blockSize+(int)(Math.random()*5*i*32), blockSize*2, blockSize, ss.cloudRandomizer(), false)); 
            clouds.get(i).SpeedX = 1;
        }
        for(int i = 0; i < 2; i++){
            units.add(new Npc(blockSize*i*3+250, blockSize*7, blockSize, blockSize*2, ss.getSprite(6, 0, 1, 2), 5, 1, true));
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
       // Graphics g2d = (Graphics2D) g;
        paintAllSprites(g);
        paintPlayerData(g);
        if(isPaused){
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", Tempo.width/2-105, Tempo.height/2);
        }
        /*
        
        for(int i = 0;i<sky.size();i++){
            sky.get(i).paint(g);
        }
        for(int i = 0;i<clouds.size();i++){
            clouds.get(i).paint(g);
        }   
        for(int i = 0;i<blocks.size();i++){
            blocks.get(i).paint(g);
        }
        player.paint(g);
        player.paint(g);
        for(Bullet bullet : bullets) {
            bullet.paint(g);
        }
        for(int i = 0; i < particle.size() ; i++){
            particle.get(i).paint(g);
        }
      
        for(int i = 0; i < player.health.size(); i++){
            player.health.get(i).y = player.y;
            player.health.get(i).x = player.x + i * 10;
        }
        for(int i = 0; i < player.nuts ; i++){
            g.drawImage(ss.getSprite(9, 0, 1, 1), 100+38*i, 680, this);
        }
        if(player.nuts > 0){
           g.drawImage(ss.getSprite(9, 0, 1, 1),player.x + player.currentHand,player.y + player.height/2,this);
        }
        
        if(isPaused){
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", Tempo.width/2-105, Tempo.height/2);
        }
        
        
        //Behöver byta till hp och hit() i Player;
        if(enemy.getHealth().isEmpty()){
            enemy.die();
        }
        //Behöver byta till hp och hit() i Player;
        if (player.health.isEmpty()) {
            player.die();
        }
        */
    }
    
    public void paintAllSprites(Graphics g){
        sprites = new ArrayList<Sprites>();
        sprites.addAll(sky);
        sprites.addAll(set);
        sprites.addAll(clouds);
        sprites.addAll(level.blocks);
        sprites.addAll(level.checkpoints);
        sprites.add(player);
        for(int i = 0; i < units.size(); i++){
            sprites.add(units.get(i));
            sprites.addAll(units.get(i).getHealth());
        }
        sprites.add(enemy);
        sprites.add(enemy_test);
        sprites.addAll(bullets);
        sprites.addAll(particle);
        sprites.addAll(enemy.getHealth());
        sprites.addAll(player.health);
        sprites.addAll(dialogue);
        sprites.addAll(player.reload);
        sprites.addAll(player.charge);
        for(Sprites sprite : sprites) {sprite.paint(g);}
    }
    
    public void paintPlayerData(Graphics g){
        //Behöver byta till hp och hit() i Player;
        if(enemy.getHealth().isEmpty()){
            enemy.die();
        }
        //Behöver byta till hp och hit() i Player;
        if (player.health.isEmpty()) {
            player.die();
        }    
        for(int i = 0; i < player.health.size(); i++){
            player.health.get(i).y = player.y;
            player.health.get(i).x = player.x + i * 10;
        }
        for(int i = 0; i < player.nuts ; i++){
            g.drawImage(ss.getSprite(9, 0, 1, 1), 100+38*i, 680, this);
        }
        if(player.nuts > 0){
           g.drawImage(ss.getSprite(9, 0, 1, 1),player.x + player.currentHand,player.y + player.height/2,this);
        }
    }
    
}
