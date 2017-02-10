
package tempo_tutorial.sprite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import tempo_tutorial.Animation;

public class Player extends Sprite {
    private boolean movingLeft, movingRight, jumping, falling, flipped;
    private Animation animation;
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
           getRectangle().x -= getVelocityX();
        }
        if(movingRight){
           getRectangle().x += getVelocityX();
        }
        
        //Om hastigheten är riktad uppåt, hoppar player
        if(super.getVelocityY() < 0) {
            jumping = true;
        }
        
        //Om hastigheten är riktad mycket nedåt, faller player
        if(super.getVelocityY() > 17.5) {
            falling = true;
        }
        
    }
    
    public boolean getMovingLeft(){
        return movingLeft;
    }
    public boolean getMovingRight(){
        return movingRight;
    }
    public boolean getJumping(){
        return jumping;
    }
    public boolean getFalling(){
        return falling;
    }
    public void setMovingLeft(boolean b){
        movingLeft = b;
    }
    public void setMovingRight(boolean b){
        movingRight = b;
    }
    public void setJumping(boolean b){
        jumping = b;
    }
    public void setFalling(boolean b){
        falling = b;
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
    public void createAnimation() {
        animation = new Animation(this);
    }
    
    public Animation getAnimation() {
        return animation;
    }
        
}
