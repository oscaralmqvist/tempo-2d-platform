
package tempo_tutorial;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
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
        player.getAnimation().setCurrentAnimation(ss.getSprite(5, 0, 1, 2), ss.getSprite(6, 0, 1, 2), ss.getSprite(7, 0, 1, 2), ss.getSprite(8, 0, 1, 2), ss.getSprite(9, 0, 1, 2), ss.getSprite(10, 0, 1, 2), ss.getSprite(4, 0, 1, 2), ss.getSprite(11, 0, 1, 2), ss.getSprite(12, 0, 1, 2));
        level = new Level(ss,"level",BLOCK_SIZE);
        background.add(new Background(0.1f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/mountains_final1.png"),player));
        background.add(new Background(0.2f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/clouds.png"),player));
        background.add(new Background(0.8f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/trees.png"),player));
        background.add(new Background(1f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/bushes.png"),player));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Background b : background){
            g2.translate(0, -(float)player.getRectangle().y+(360-75) * b.getVelocityX() - (float)b.getRectangle().height*b.getVelocityX()*0.5);
            b.paint(g2);
            resetTranslate(g2);
        }
        g2.translate(-player.getRectangle().x + (640-32), -player.getRectangle().y+(360-75));
        
        player.paint(g2);
        g2.shear(0, 0);
        level.paint(g2);
        
        if(level.getPickup().size()>0){
            g2.drawString(level.getPickup().size()+" Tacos kvar.", player.getRectangle().x-600, player.getRectangle().y-200);
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

}
