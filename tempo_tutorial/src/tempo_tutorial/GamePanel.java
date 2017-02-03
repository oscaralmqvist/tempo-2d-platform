
package tempo_tutorial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tempo_tutorial.sprite.Level;
import tempo_tutorial.sprite.Player;

public class GamePanel extends JPanel {
    
    private final int BLOCK_SIZE = 75;
    private Texture texture;
    private Player player;
    private Level level;
    
    public GamePanel() {
        texture = new Texture();
        player = new Player(Tempo_tutorial.WIDTH/2 - 200, Tempo_tutorial.HEIGHT/2, BLOCK_SIZE, BLOCK_SIZE * 2, texture.getSprite(4, 0, 1, 2), 10, 3, 10);
        level = new Level(texture,"level",BLOCK_SIZE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(-player.getRectangle().x + (640-32), -player.getRectangle().y+(360-75));
        player.paint(g2);
        level.paint(g2);
        
    }
    
    public Player getPlayer() {
        return player;
    }
    public Level getLevel() {
        return level;
    }
}
