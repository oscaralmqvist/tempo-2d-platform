
package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
    private boolean movingLeft, movingRight, flipped;
    private int jumps = 2;

    
    public Player(int x, int y, int width, int height, BufferedImage image, float dx, float dy, float gravity) {
        super(x, y, width, height, image, dx, dy, gravity);
    }

    @Override
    public void paint(Graphics2D g) {
        Rectangle r = super.getRectangle();
        if (flipped) g.drawImage(super.getImage(), r.width + r.x, r.y, -r.width, r.height, null);
        else g.drawImage(super.getImage(), r.x, r.y, r.width, r.height, null);
    }

    
    public void tick() {
        super.getRectangle().y += super.getVelocityY();
        super.addVelocityY(super.getGravity());
        
        if(movingLeft){
           getRectangle().x += getVelocityX();
        }
        if(movingRight){
           getRectangle().x -= getVelocityX();
        }
    }
    
    public boolean getMovingLeft(){
        return movingLeft;
    }
    public boolean getMovingRight(){
        return movingRight;
    }
    public void setMovingLeft(boolean b){
        movingLeft = b;
    }
    public void setMovingRight(boolean b){
        movingRight = b;
    }
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
    public int getJumps(){
        return jumps;
    }
    public void setJumps(int i){
        jumps = i;
    }
        
}
