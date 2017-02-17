
package tempo_tutorial;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import tempo_tutorial.sprite.Background;
import tempo_tutorial.sprite.Level;
import tempo_tutorial.sprite.Player;

public class GamePanel extends JPanel {
    
    private final int BLOCK_SIZE = 75;
    private Spritesheet ss;
    private Player player;
    private Level level;
    private ArrayList<Background> background = new ArrayList();
    
    public GamePanel() {
        setFont(new Font("Serif", Font.BOLD, 32));
        ss = new Spritesheet();
        player = new Player(Tempo_tutorial.WIDTH/2, Tempo_tutorial.HEIGHT/2, BLOCK_SIZE, BLOCK_SIZE * 2, ss.getSprite(4, 0, 1, 2), 10, 3, 10);
        player.createAnimation();
        player.getAnimation().setAnimation(/*Running (0-5)*/ getPS(7,0), getPS(8,0), getPS(9,0), getPS(10,0), getPS(11,0), getPS(12,0), /*Idle (6-8)*/ getPS(4,0), getPS(5,0), getPS(6,0), /*Jumping (9)*/ getPS(4,2), /*Falling (10)*/ getPS(5,2));
        level = new Level(ss,"level",BLOCK_SIZE);
        background.add(new Background(0f,0,0,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/sky.png"),player));
        background.add(new Background(0.1f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/mountains_final1.png"),player));
        background.add(new Background(0.2f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/clouds.png"),player));
        background.add(new Background(0.6f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/bushes.png"),player));
        background.add(new Background(0.8f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/trees.png"),player));
        background.add(new Background(0.9f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/trees.png"),player));
        background.add(new Background(1f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/bushes.png"),player));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Background b : background){
            g2.translate(0, b.getVelocityX() > 0 ? -(float)player.getRectangle().y+(460-75) * b.getVelocityX() - (float)b.getRectangle().height*b.getVelocityX()*0.5 : 0);
            b.paint(g2);
            resetTranslate(g2);
        }
        g2.translate(-player.getRectangle().x + (640-32), -player.getRectangle().y+(460-75));
        
        player.paint(g2);
        g2.shear(0, 0);
        level.paint(g2);
        resetTranslate(g2);
        
        //MINIMAP
        g2.drawImage(ss.getImage("src/resources/pics/sky.png"), 0, 0, 1280, 150, this);
        g2.scale(0.29, 0.2);
        
        player.paint(g2);
        level.paint(g2);
        resetTranslate(g2);
        g2.copyArea(0, 0, 20000, 1000, 0, 0);
        
        
        g2.scale(1, 1);
        if(level.getPickup().size()>0){
            for(int i = 0; i < level.getPickup().size(); i++) {
                g2.drawImage(ss.getSprite(13, 1, 1, 1), i  * BLOCK_SIZE / 2, 0, 32, 32, this);
            }
            
        }
        else{
            g2.drawString("Winner winner chicken dinner",player.getRectangle().x-180,player.getRectangle().y-50);
        }
}
    
    public Player getPlayer() {
        return player;
    }
    
    public Spritesheet getSpritesheet() {
        return ss;
    }
    
    public Level getLevel(){
        return level;
    }
    
    public ArrayList<Background> getBack(){
        return background;
    }
    private void resetTranslate(Graphics2D g2){  
        Rectangle r = g2.getClipBounds();
        g2.translate(r.x, r.y);
    }
    
    //PS = Player-Sprite
    private BufferedImage getPS(int x, int y) {
        return ss.getSprite(x, y, 1, 2);
    }

}
