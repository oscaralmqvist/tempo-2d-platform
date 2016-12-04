package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import tempo.Collision;
import tempo.Sound;
import tempo.Spritesheet;
import tempo.Tempo;
import tempo.sprites.Block;
import tempo.sprites.Bullet;
import tempo.sprites.Dialogue;
import tempo.sprites.Level;
import tempo.sprites.Npc;
import tempo.sprites.Particle;
import tempo.sprites.Player;
import tempo.sprites.SetBackground;
import tempo.sprites.Sprites;

public class GameState extends State {

    // FLYTTA TILL PLAYER?
    public boolean isPaused, movingLeft, movingRight, movingUp;
    public int currentLevel = 1;
    // Längden på sidan av ett block på kartan
    int blockSize = 75;
    public Spritesheet ss = new Spritesheet();
    public ArrayList<Npc> units = new ArrayList<Npc>();
    // Karaktärer skapas och placeras
    //public Player player = new Player((Tempo.width/2), 420, blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
    
    public Level level;
    public ArrayList<Block> sky = new ArrayList<Block>();
    public ArrayList<Block> clouds = new ArrayList<Block>();
    public ArrayList<SetBackground> set = new ArrayList<SetBackground>();
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public ArrayList<Particle> particle = new ArrayList<Particle>();
    public ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>(); 
    
    public Sound sound = new Sound();
    
    public Player player;
    public Npc enemy = new Npc(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(4, 2, 1, 2), 5, 1, false);
    public Npc enemy_test = new Npc(blockSize*19, blockSize*7, blockSize, blockSize*2, ss.getSprite(4, 4, 1, 2), 5, 1, true);
    

    public Collision coll = new Collision();
    public final Font font = new Font("TimesRoman", Font.PLAIN, 78);

    public GameState(){
        loadLevel();
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
    public void paint(Graphics g){
        paintAllSprites(g);
        paintPlayerData(g);
        if(isPaused){
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", Tempo.width/2-105, Tempo.height/2);
        }
    }
    
    public void paintAllSprites(Graphics g){
    
        for(Sprites s : set) { s.paint(g); }
        for(Sprites s : level.blocks) { s.paint(g); }
        for(Sprites s : level.checkpoints) { s.paint(g); }
        level.goal.paint(g);
        player.paint(g);
        for(int i = 0; i < units.size(); i++){
           units.get(i).paint(g);
            for(Sprites s : units.get(i).getHealth()) { s.paint(g); }
        }
        enemy.paint(g);
        enemy_test.paint(g);
        for(Sprites s : bullets) { s.paint(g); }
        for(Sprites s : particle) { s.paint(g); }
        for(Sprites s : enemy.getHealth()) { s.paint(g); }
        for(Sprites s : player.health) { s.paint(g); }
        for(Sprites s : dialogue) { s.paint(g); }
        set.get(6).paint(g);
        for(Sprites s : player.reload) { s.paint(g); }
        for(Sprites s : player.charge) { s.paint(g); }
        
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
            //g.drawImage(ss.getSprite(13, 0, 1, 1), 100+38*i, 680, this);
        }
        if(player.nuts > 0){
            //g.drawImage(ss.getSprite(13, 0, 1, 1),player.rect.x + player.currentHand,player.rect.y + player.rect.height/2,this);
        }
    }
     public void loadLevel(){
         

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
