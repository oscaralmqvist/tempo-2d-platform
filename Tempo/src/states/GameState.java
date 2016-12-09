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

    private boolean isPaused;
    // Längden på sidan av ett block på kartan
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    // Karaktärer skapas och placeras
    //public Player player = new Player((Tempo.width/2), 420, blockSize, blockSize*2, ss.getSprite(4, 0, 1, 2), 10, 3, true);
    
    
    private int currentLevel = 1;
    private int blockSize = 75;
    private Spritesheet ss = new Spritesheet();
    private ArrayList<Npc> units = new ArrayList<Npc>();
    private Level level;
    private ArrayList<Block> sky = new ArrayList<Block>();
    private ArrayList<Block> clouds = new ArrayList<Block>(); 
    
    private ArrayList<SetBackground> set = new ArrayList<SetBackground>();
    
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Particle> particle = new ArrayList<Particle>();
    private ArrayList<Dialogue> dialogue = new ArrayList<Dialogue>();
    

    private Sound sound = new Sound();
    private Player player;
    private Npc enemy = new Npc(blockSize, blockSize * 7, blockSize, blockSize*2, ss.getSprite(4, 2, 1, 2), 5, 1, false, 0, 0);
    private Npc enemy_test = new Npc(blockSize*19, blockSize*7, blockSize, blockSize*2, ss.getSprite(4, 4, 1, 2), 5, 1, true, 0, 0);
    private Collision coll = new Collision();
    private final Font font = new Font("TimesRoman", Font.PLAIN, 78);

    public GameState(){
        loadLevel();
        int row = 0;
        int collumn = 0;
       // sky.add(new Block(0, 0, Tempo.width, Tempo.height, ss.getSprite(0, 3, 1, 1), false));     
        set.add(new SetBackground(0,0,-200,Tempo.width, Tempo.height,getSs().getImage("src/resources/pics/sky.png"), getPlayer()));  
        //speed proBlEm
        set.add(new SetBackground(0.01f,0,100,Tempo.width, Tempo.height+100,getSs().getImage("src/resources/pics/clouds.png"), getPlayer()));   
        set.add(new SetBackground(0.08f,0,200,Tempo.width, Tempo.height-100,getSs().getImage("src/resources/pics/mountains_final1.png"), getPlayer()));
        set.add(new SetBackground(0.5f,0,300,Tempo.width, Tempo.height-300,getSs().getImage("src/resources/pics/trees.png"), getPlayer()));
        set.add(new SetBackground(0.7f,0,200,Tempo.width, Tempo.height-200,getSs().getImage("src/resources/pics/trees.png"), getPlayer()));
        set.add(new SetBackground(0.8f,0,-20,Tempo.width, Tempo.height,getSs().getImage("src/resources/pics/bushes.png"), getPlayer()));
        set.add(new SetBackground(1.2f,0,-20,Tempo.width, Tempo.height,getSs().getImage("src/resources/pics/bushes.png"), getPlayer()));
        units.add(enemy);
        units.add(enemy_test);
        for(int i = 0; i < 2; i++){
            units.add(new Npc(getBlockSize()*i*3+250, getBlockSize()*7, getBlockSize(), getBlockSize()*2, getSs().getSprite(4, 4, 1, 2), 5, 1, true, 1, 1));
        }
    }
    public void paint(Graphics g){
        paintAllSprites(g);
        paintPlayerData(g);
        if(isIsPaused()){
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
            g.drawString("PAUSED", Tempo.width/2-105, Tempo.height/2);
        }
    }
    
    public void paintAllSprites(Graphics g){
    
        for(Sprites s : getSet()) { s.paint(g); }
        for(Sprites s : getLevel().getBlocks()) { s.paint(g); }
        for(Sprites s : getLevel().getCheckpoints()) { s.paint(g); }
        getLevel().getGoal().paint(g);
        getPlayer().paint(g);
        for(int i = 0; i < getUnits().size(); i++){
            getUnits().get(i).paint(g);
            for(Sprites s : getUnits().get(i).getHealth()) { s.paint(g); }
        }
        getEnemy().paint(g);
        getEnemy_test().paint(g);
        for(Sprites s : getBullets()) { s.paint(g); }
        for(Sprites s : getParticle()) { s.paint(g); }
        for(Sprites s : getEnemy().getHealth()) { s.paint(g); }
        for(Sprites s : getPlayer().getHealth()) { s.paint(g); }
        for(Sprites s : getDialogue()) { s.paint(g); }
        getSet().get(6).paint(g);
        for(Sprites s : getPlayer().getReload()) { s.paint(g); }
        for(Sprites s : getPlayer().getCharge()) { s.paint(g); }
        
        
        
    }
    
    public void paintPlayerData(Graphics g){
        //Behöver byta till hp och hit() i Player;
        if(getEnemy().getHealth().isEmpty()){
            getSound().playSound("die");
            getEnemy().die();
        }
        //Behöver byta till hp och hit() i Player;
        if (getPlayer().getHealth().isEmpty()) {
            getPlayer().die();
        }    
        for(int i = 0; i < getPlayer().getHealth().size(); i++){
            player.getHealth().get(i).rect.y = getPlayer().rect.y;
            player.getHealth().get(i).rect.x = getPlayer().rect.x + i * 10;
        }
        for(int i = 0; i < getPlayer().getNuts() ; i++){
            g.drawImage(getSs().getSprite(13, 0, 1, 1), 100+38*i, 680, null);
        }
        if(getPlayer().getNuts() > 0){
            g.drawImage(getSs().getSprite(13, 0, 1, 1),getPlayer().rect.x + getPlayer().currentHand,getPlayer().rect.y + getPlayer().rect.height/2,null);
        }
    }
     
    public void loadLevel(){
         

        try{
            setLevel(new Level(getSs(), "level" + getCurrentLevel()));
            setPlayer(new Player(Tempo.width/2, Tempo.height/2, getBlockSize(), getBlockSize() * 2, getSs().getSprite(4, 0, 1, 2), 10, 3, true, 13, 0, 0));
            getPlayer().createAnimation();
            getPlayer().getAnimation().setCurrentAnimation(getSs().getSprite(4, 0, 1, 2));
            getPlayer().setCheckpoint(getLevel().getSpawn());
            getPlayer().resetScreen();
           

        }catch(Exception e){
            setCurrentLevel(1);
            loadLevel();
        }
        
    }

    /**
     * @return the isPaused
     */
    public boolean isIsPaused() {
        return isPaused;
    }

    /**
     * @param isPaused the isPaused to set
     */
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    /**
     * @return the movingLeft
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * @param movingLeft the movingLeft to set
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * @return the movingRight
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * @param movingRight the movingRight to set
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * @return the movingUp
     */
    public boolean isMovingUp() {
        return movingUp;
    }

    /**
     * @param movingUp the movingUp to set
     */
    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    /**
     * @return the currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @param currentLevel the currentLevel to set
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * @return the blockSize
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * @param blockSize the blockSize to set
     */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * @return the ss
     */
    public Spritesheet getSs() {
        return ss;
    }

    /**
     * @param ss the ss to set
     */
    public void setSs(Spritesheet ss) {
        this.ss = ss;
    }

    /**
     * @return the units
     */
    public ArrayList<Npc> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(ArrayList<Npc> units) {
        this.units = units;
    }

    /**
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * @return the sky
     */
    public ArrayList<Block> getSky() {
        return sky;
    }

    /**
     * @param sky the sky to set
     */
    public void setSky(ArrayList<Block> sky) {
        this.sky = sky;
    }

    /**
     * @return the clouds
     */
    public ArrayList<Block> getClouds() {
        return clouds;
    }

    /**
     * @param clouds the clouds to set
     */
    public void setClouds(ArrayList<Block> clouds) {
        this.clouds = clouds;
    }

    /**
     * @return the set
     */
    public ArrayList<SetBackground> getSet() {
        return set;
    }

    /**
     * @param set the set to set
     */
    public void setSet(ArrayList<SetBackground> set) {
        this.set = set;
    }

    /**
     * @return the bullets
     */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * @param bullets the bullets to set
     */
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     * @return the particle
     */
    public ArrayList<Particle> getParticle() {
        return particle;
    }

    /**
     * @param particle the particle to set
     */
    public void setParticle(ArrayList<Particle> particle) {
        this.particle = particle;
    }

    /**
     * @return the dialogue
     */
    public ArrayList<Dialogue> getDialogue() {
        return dialogue;
    }

    /**
     * @param dialogue the dialogue to set
     */
    public void setDialogue(ArrayList<Dialogue> dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * @return the sound
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * @param sound the sound to set
     */
    public void setSound(Sound sound) {
        this.sound = sound;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the enemy
     */
    public Npc getEnemy() {
        return enemy;
    }

    /**
     * @param enemy the enemy to set
     */
    public void setEnemy(Npc enemy) {
        this.enemy = enemy;
    }

    /**
     * @return the enemy_test
     */
    public Npc getEnemy_test() {
        return enemy_test;
    }

    /**
     * @param enemy_test the enemy_test to set
     */
    public void setEnemy_test(Npc enemy_test) {
        this.enemy_test = enemy_test;
    }

    /**
     * @return the coll
     */
    public Collision getColl() {
        return coll;
    }

    /**
     * @param coll the coll to set
     */
    public void setColl(Collision coll) {
        this.coll = coll;
    }
}
