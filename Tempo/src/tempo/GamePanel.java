/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.Graphics;
import javax.swing.*;
import tempo.sprites.*;

/**
 *
 * @author Elev
 */
public class GamePanel extends JPanel{
    
    Player player = new Player(100, 100, 200, 200);
    
    public GamePanel(){
        
    }
    public void paintComponent(Graphics g){
        player.paint(g);
    }   
}
