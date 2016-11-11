/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Elev
 */
public class Window extends JFrame {
    
    Spritesheet ss = new Spritesheet();
    MapEditor mp = new MapEditor(ss);
    int id = 2;
    
    public Window() {
        setTitle("Tempo Map Editor");
        setSize(1000, 300);
        setVisible(true);
        addMouseMotionListener(new MouseMovement(mp, this));
        addMouseListener(new Mouse(mp, this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel toolbar = new JPanel();
        //toolbar.setLayout(new BorderLayout());
        
        toolbar.add(new JTextField("600"));
        toolbar.add(new JLabel("x"));
        toolbar.add(new JTextField("100"));
        
        JButton grass = new JButton(new ImageIcon(ss.getSprite(2, 0, 1, 1))); // Grass
        JButton dirt = new JButton(new ImageIcon(ss.getSprite(1, 2, 1, 1))); // Dirt
       
        dirt.setText("Dirt");
        grass.setText("Grass");
        
        toolbar.add(grass);
        toolbar.add(dirt);
        
        Al al = new Al(this);
        grass.addActionListener(al);
        dirt.addActionListener(al);
        
        add(toolbar, BorderLayout.NORTH);
        add(mp, BorderLayout.CENTER);
        revalidate();  
    }
   
    private class Al implements ActionListener {
        
        Window w;
        
        public Al(Window w) {
            this.w = w;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "Dirt") {
                w.id = 2;
            }
            else if(e.getActionCommand() == "Grass") {
                w.id = 1;
            }
        }
    
}
    
}
