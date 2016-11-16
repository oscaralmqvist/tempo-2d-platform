/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp_map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
    public ArrayList<BlockData> blocksdata = new ArrayList<BlockData>() {{
            add(new BlockData("Dirt", 2, ss.getSprite(1, 0, 1, 1), 'd'));
            add(new BlockData("Grass", 1, ss.getSprite(0, 0, 1, 1), 'l'));
            add(new BlockData("Spawn", 3, ss.getSprite(2, 0, 1, 1), 's'));
            add(new BlockData("Checkpoint", 4, ss.getSprite(0, 7, 1, 1), 'c'));
            add(new BlockData("Tree", 5, ss.getSprite(0, 5, 1, 1), 't'));
        } 
    };
    MapEditor mp = new MapEditor(ss, blocksdata);
    
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
        
        Al al = new Al(this, blocksdata);
        for(BlockData bd : blocksdata) {
            toolbar.add(bd.getJButton());
            bd.getJButton().addActionListener(al);
        }
        
        
        JButton save = new JButton("Spara");
        save.addActionListener(al);
        toolbar.add(save);
        
        
        add(toolbar, BorderLayout.NORTH);
        add(mp, BorderLayout.CENTER);
        revalidate();  
    }
   
    private class Al implements ActionListener {
        
        Window w;
        ArrayList<BlockData> blocksdata;
        
        public Al(Window w, ArrayList<BlockData> blocksdata) {
            this.w = w;
            this.blocksdata = blocksdata;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            for(BlockData bd : blocksdata) {
                if(e.getActionCommand() == bd.getName())
                    w.id = bd.getID();
            }
            if(e.getActionCommand() == "Spara") {
                JFileChooser c = new JFileChooser();
                c.setDialogTitle("Specify a file to save");  
                int userSelection = c.showSaveDialog(w);
                if (userSelection  == JFileChooser.APPROVE_OPTION) {
                 // filename.setText(c.getSelectedFile().getName());
                 // dir.setText(c.getCurrentDirectory().toString());
                 File fileToSave = c.getSelectedFile();
                 System.out.println(fileToSave.getAbsolutePath());
                 new CreateMap(w.mp.blocks, blocksdata, w.mp.blockSize, fileToSave.getAbsolutePath());
                }
            }
        }
    
}
    
}
