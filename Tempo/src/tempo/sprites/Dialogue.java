
package tempo.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Dialogue extends Sprites {
    
    public ArrayList<String> dialogue;
    public String output;
    private boolean printing;
    private boolean printingDone;
    public BufferedImage p1;
    public BufferedImage p2;
    public int ticks;
    
    public Dialogue(BufferedImage p1, BufferedImage p2, String... args) {
        super(0, 0, 0, 0, null);
        dialogue = new ArrayList<String>();
        for(String arg : args) {
            dialogue.add(arg);
        }
        this.p1 = p1;
        this.p2 = p2;
        this.printingDone = false;
        this.output = "";
        this.ticks = 0;
    }
    
    public void printDialogue() {
        ticks += 1;
        if(dialogue.isEmpty()) {
            printingDone = true;
        } else {
            
            if((int) (ticks/8) > dialogue.get(0).length())  {
                dialogue.remove(0);
                ticks = 0;
            } else {
                output = dialogue.get(0).substring(0, (int) (ticks / 8));
            }
        }
    }
    
    public boolean isDone() {
        return printingDone;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(200, 500, 900, 200);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.white);
        g.drawImage(p1, 900, 500, 200, 200, null);
        g.drawImage(p2, 200, 500, 200, 200, null);
        g.drawString(output, 400, 550);
    }
    
}
