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
    int currentLevel = 1;
    // Längden på sidan av ett block på kartan
    int blockSize = 75;
    Spritesheet ss = new Spritesheet();
    ArrayList<Npc> units = new ArrayList<Npc>();
    // Karaktärer skapas och placeras
    //public Player player = new Player((Tempo.width/2), 420, blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
    
    Level level;
    ArrayList<Block> sky = new ArrayList<Block>();
    ArrayList<Block> clouds = new ArrayList<Block>();
    ArrayList<SetBackground> set = new ArrayList<SetBackground>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Particle> particle = new ArrayList<Particle>();
    ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>(); 
    ArrayList<Sprites> sprites;
    
    public Sound sound = new Sound();
    
    public Player player;
    public Npc enemy = new Npc(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(4, 2, 1, 2), 5, 1, false);
    public Npc enemy_test = new Npc(blockSize*19, blockSize*7, blockSize, blockSize*2, ss.getSprite(4, 4, 1, 2), 5, 1, true);
    

    public Collision coll = new Collision();
    final Font font = new Font("TimesRoman", Font.PLAIN, 78);

    public GamePanel(){
        loadLevel();
        setFont(font);
        int row = 0;
        int collumn = 0;
       // sky.add(new Block(0, 0, Tempo.width, Tempo.height, ss.getSprite(0, 3, 1, 1), false));     
        set.add(new SetBackground(0,0,-200,Tempo.width, Tempo.height,ss.getImage("src/resources/pics/sky.png"), player));  
        //speed proBlEm
        set.add(new SetBackground(0.01f,0,100,Tempo.width, Tempo.height+100,ss.getImage("src/resources/pics/clouds.png"), player));   
        set.add(new SetBackground(0.08f,0,200,Tempo.width, Tempo.height-100,ss.getImage("src/resources/pics/mountains_final1.png"), player));
        set.add(new SetBackground(0.5f,0,300,Tempo.width, Tempo.height-300,ss.getImage("src/resources/pics/trees.png"), player));
        set.add(new SetBackground(0.7f,0,200,Tempo.width, Tempo.height-200,ss.getImage("src/resources/pics/trees.png"), player));
        set.add(new SetBackground(0.8f,0,-20,Tempo.width, Tempo.height,ss.getImage("src/resources/pics/bushes.png"), player));
        set.add(new SetBackground(1.2f,0,-20,Tempo.width, Tempo.height,ss.getImage("src/resources/pics/bushes.png"), player));
        units.add(enemy);
        units.add(enemy_test);
        for(int i = 0; i < 2; i++){
            units.add(new Npc(blockSize*i*3+250, blockSize*7, blockSize, blockSize*2, ss.getSprite(4, 4, 1, 2), 5, 1, true));
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintAllSprites(g);
        paintPlayerData(g);
        if(isPaused){
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", Tempo.width/2-105, Tempo.height/2);
        }
    }
    
    public void paintAllSprites(Graphics g){
        sprites = new ArrayList<Sprites>();
        sprites.add(set.get(0));
        sprites.add(set.get(1));
        sprites.add(set.get(2));
        sprites.add(set.get(3));
        sprites.add(set.get(4));
        sprites.add(set.get(5));
        sprites.addAll(level.blocks);
        sprites.addAll(level.checkpoints);
        sprites.add(level.goal);
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
        sprites.add(set.get(6));
        sprites.addAll(player.reload);
        sprites.addAll(player.charge);
        for(Sprites sprite : sprites) {sprite.paint(g);}
    }
    
    public void paintPlayerData(Graphics g){
        //Behöver byta till hp och hit() i Player;
        if(enemy.getHealth().isEmpty()){
            sound.playSound("die");
            enemy.die();
        }
        //Behöver byta till hp och hit() i Player;
        if (player.health.isEmpty()) {
            player.die();
        }    
        for(int i = 0; i < player.health.size(); i++){
            player.health.get(i).rect.y = player.rect.y;
            player.health.get(i).rect.x = player.rect.x + i * 10;
        }
        for(int i = 0; i < player.nuts ; i++){
            g.drawImage(ss.getSprite(13, 0, 1, 1), 100+38*i, 680, this);
        }
        if(player.nuts > 0){
            g.drawImage(ss.getSprite(13, 0, 1, 1),player.rect.x + player.currentHand,player.rect.y + player.rect.height/2,this);
        }
    }
     public void loadLevel(){
         
        if(sprites !=null){
            sprites.clear();
        }
        try{
            level = new Level(ss,"level"+currentLevel);
            player = new Player(Tempo.width/2,Tempo.height/2,blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
            player.createAnimation();
            player.getAnimation().setCurrentAnimation(ss.getSprite(4, 0, 1, 2));
            player.setCheckpoint(level.spawn);
            player.resetScreen();
           

        }catch(Exception e){
            currentLevel = 1;
            loadLevel();
        }
        
    }
    
}
