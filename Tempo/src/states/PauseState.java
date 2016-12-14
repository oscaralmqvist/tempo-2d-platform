
package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import tempo.Tempo;

public class PauseState extends State {
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Tempo.width, Tempo.height);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 78));
        g.drawString("PAUSED", Tempo.width/2-150, Tempo.height/2);
    }
    
}
