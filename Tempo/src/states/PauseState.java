
package states;

import java.awt.Color;
import java.awt.Graphics;

public class PauseState extends State {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(20, 20, 100, 100);
    }
    
}
