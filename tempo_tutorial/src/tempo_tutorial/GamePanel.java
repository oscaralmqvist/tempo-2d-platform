
package tempo_tutorial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tempo_tutorial.sprite.Player;

public class GamePanel extends JPanel {
    
    private Texture texture;
    private Player player;
    
    public GamePanel() {
        texture = new Texture();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
    }

}
