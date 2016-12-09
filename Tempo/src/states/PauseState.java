
package states;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import tempo.Tempo;

public class PauseState extends State {
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Tempo.width, Tempo.height);
    }
    
}
