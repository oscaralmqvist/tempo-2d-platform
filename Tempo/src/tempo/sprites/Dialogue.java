
package tempo.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Dialogue extends Sprites {
    
    private ArrayList<String> dialogue;
    private String output;
    private boolean printing;
    private boolean printingDone;
    private BufferedImage p1;
    private BufferedImage p2;
    private int ticks;
    
    public Dialogue(BufferedImage p1, BufferedImage p2, String... args) {
        super(new Rectangle(0,0,0,0), null, 0, 0, 0);
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
        setTicks(getTicks() + 1);
        if(getDialogue().isEmpty()) {
            setPrintingDone(true);
        } else {
            
            if((int) (getTicks()/8) > getDialogue().get(0).length())  {
                getDialogue().remove(0);
                setTicks(0);
            } else {
                setOutput(getDialogue().get(0).substring(0, (int) (getTicks() / 8)));
            }
        }
    }
    
    public boolean isDone() {
        return isPrintingDone();
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(200, 500, 900, 200);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.white);
        g.drawImage(getP1(), 900, 500, 200, 200, null);
        g.drawImage(getP2(), 200, 500, 200, 200, null);
        g.drawString(getOutput(), 500, 550);
    }

    /**
     * @return the dialogue
     */
    public ArrayList<String> getDialogue() {
        return dialogue;
    }

    /**
     * @param dialogue the dialogue to set
     */
    public void setDialogue(ArrayList<String> dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * @return the printing
     */
    public boolean isPrinting() {
        return printing;
    }

    /**
     * @param printing the printing to set
     */
    public void setPrinting(boolean printing) {
        this.printing = printing;
    }

    /**
     * @return the printingDone
     */
    public boolean isPrintingDone() {
        return printingDone;
    }

    /**
     * @param printingDone the printingDone to set
     */
    public void setPrintingDone(boolean printingDone) {
        this.printingDone = printingDone;
    }

    /**
     * @return the p1
     */
    public BufferedImage getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(BufferedImage p1) {
        this.p1 = p1;
    }

    /**
     * @return the p2
     */
    public BufferedImage getP2() {
        return p2;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(BufferedImage p2) {
        this.p2 = p2;
    }

    /**
     * @return the ticks
     */
    public int getTicks() {
        return ticks;
    }

    /**
     * @param ticks the ticks to set
     */
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
    
}
