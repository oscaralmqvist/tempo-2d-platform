/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import states.State;
import states.StateManager;
import tempo.sprites.*;

/**
 *
 * @author Elev
 */
public class GamePanel extends JPanel {
    
    private StateManager sm;
    
    public GamePanel() {
        sm = new StateManager();  
        sm.startGame();
    }
    
    
    public StateManager getStateManager() {
        return sm;
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(State s : sm.getStates()) {
            s.paint(g);
        }
    }
    
}
