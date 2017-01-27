
package tempo_tutorial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tempo_tutorial.sprite.Player;

public class GamePanel extends JPanel {
    
    private final int BLOCK_SIZE = 75;
    private Texture texture;
    private Player player;
    
    public GamePanel() {
        texture = new Texture();
        player = new Player(Tempo_tutorial.WIDTH/2, Tempo_tutorial.HEIGHT/2, BLOCK_SIZE, BLOCK_SIZE * 2, texture.getSprite(4, 0, 1, 2), 10, 3, 10);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        player.paint(g2);
        
    }
    
    public Player getPlayer() {
        return player;
    }

}
