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
    
    
    public Window(){
        add(panel);
        setTitle("Tempo");
        setVisible(true);
        setSize(Tempo.width, Tempo.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new Keyboard(panel));
        addMouseListener(new Mouse(panel));
    //    setLocationRelativeTo(null);
    
        this.addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent e) {
      }

      public void windowClosing(WindowEvent e) {
      }

      public void windowClosed(WindowEvent e) {
      }

      public void windowIconified(WindowEvent e) {
      }

      public void windowDeiconified(WindowEvent e) {
      }

      public void windowActivated(WindowEvent e) {
        panel.isPaused = false;

      }

      public void windowDeactivated(WindowEvent e) {
        panel.isPaused = true;

      }

      public void windowStateChanged(WindowEvent e) {
      }

      public void windowGainedFocus(WindowEvent e) {
      }

      public void windowLostFocus(WindowEvent e) {
      }
    });
        
    }
}
                