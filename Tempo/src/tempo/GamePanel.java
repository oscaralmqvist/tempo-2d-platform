/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import javax.swing.*;
import tempo.sprites.*;

/**
 *
 * @author Elev
 */
public class GamePanel extends JPanel{
    Spritesheet ss = new Spritesheet();
    Player player = new Player(100, 100, 100, 200, ss.getSprite(0, 0, 32, 64));
    Player enemy = new Player(100, 220, 100, 200, ss.getSprite(32, 0, 32, 64));
    
    public GamePanel(){
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        enemy.paint(g);
        player.paint(g);
    }   
}
