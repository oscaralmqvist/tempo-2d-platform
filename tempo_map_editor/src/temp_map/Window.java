/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.BorderLayout;
import java.awt.TextField;
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
        BufferedImage buttonIcon = ss.getSprite(2, 0, 1, 1);
        JButton button = new JButton(new ImageIcon(buttonIcon));
        toolbar.add(button);
        add(toolbar, BorderLayout.NORTH);
        add(mp, BorderLayout.CENTER);
        revalidate();  
    }
   

    
}
