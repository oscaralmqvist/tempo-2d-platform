
package temp_map;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BlockData {
    
    private int ID;
    private BufferedImage image;
    private char id_char;
    private String name;
    JButton button;
    
    public BlockData(String name, int ID, BufferedImage image, char id_char) {
        this.ID = ID;
        this.image = image;
        this.name = name;
        this.id_char = id_char;
        JButton b = new JButton(new ImageIcon(image));
        b.setText(name);
        this.button = b;
        
    }
    
    public String getName() {
        return name;
    }
    
public JButton getJButton() {
        return button;
    }
    
    public int getID() {
        return ID;
    }

    public BufferedImage getImage() {
        return image;
    }    
    
    public char getCharID() {
        return id_char;
    }
    
}
