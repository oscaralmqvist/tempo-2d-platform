/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Elev
 */
public class Window extends JFrame{
    public GamePanel panel = new GamePanel();
    public GameEngine ge = new GameEngine(panel);
    private  boolean MOVE_UP = false;
    private boolean MOVE_LEFT = false;
    private boolean MOVE_RIGHT = false;
    
    
    public Window(){
        add(panel);
        setTitle("Tempo");
        setVisible(true);
        setSize(1200,740);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new Keyboard(panel));


        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), MOVE_UP);  
        panel.getActionMap().put(MOVE_UP, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if("w".equals(e.getActionCommand())){
                    System.out.println("w");
                    panel.enemy.y += -10;
                    MOVE_UP = true;
                }      
            }
        });
    }
}
