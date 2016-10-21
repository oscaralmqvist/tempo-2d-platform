/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import javax.swing.*;

/**
 *
 * @author Elev
 */
public class Window extends JFrame{
    public GamePanel panel = new GamePanel();
    public GameEngine ge = new GameEngine(panel);
    
    public Window(){
        add(panel);
        setTitle("Tempo");
        setVisible(true);
        setSize(1200,740);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new Keyboard(panel));
    }
}
