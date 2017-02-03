
package tempo_tutorial;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        ss = new Spritesheet();
        player = new Player(Tempo_tutorial.WIDTH/2-200, Tempo_tutorial.HEIGHT/2, BLOCK_SIZE, BLOCK_SIZE * 2, ss.getSprite(4, 0, 1, 2), 10, 3, 10);
        level = new Level(ss,"level",BLOCK_SIZE);
        background.add(new Background(0.1f,200,230,Tempo_tutorial.WIDTH,Tempo_tutorial.HEIGHT,ss.getImage("src/resources/pics/mountains_final1.png"),player));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for(Background b : background){
            b.paint(g2);
        }
        g2.translate(-player.getRectangle().x + (640-32), -player.getRectangle().y+(360-75));
        player.paint(g2);
        level.paint(g2);
        
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

}
