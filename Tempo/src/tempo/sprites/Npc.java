
package tempo.sprites;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tempo.Health;

/**
 *
 * @author Oscar Almqvist
 */
public class Npc extends Sprites{
    private boolean isHostile;
    private int lives;
    public double xSpeed = 0; 
    public float ySpeed = 0;
    private boolean movingRight, movingLeft;
    private ArrayList<Health> health = new ArrayList<Health>();
    
    public Npc(int x, int y, int width, int height, BufferedImage image, int hp, int lives, boolean isHostile, float dx, float gravity) {
        super(new Rectangle(x,y,width,height), image, dx, 0, gravity);
        this.lives = lives;
        this.isHostile = isHostile;
        if(isHostile == true){
            super.setVelocityX(1);
        }
        for(int i = 0; i < hp; i++){
            health.add(new Health(x + 10*i, (y-10), 8, 8, null));
        }
    }
    

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.rect.x, super.rect.y,super.rect.width, super.rect.height, null);
    }
    
    public boolean getIsHostile(){
        return isHostile;
    }
    
    public boolean getMovingRight(){
        return movingRight;
    }
    
    public boolean getMovingLeft(){
        return movingLeft;
    }
    
    public ArrayList<Health> getHealth(){
        return health;
    }
    
     public void loseHealth(int healthLost){
        
        if(!this.health.isEmpty()){
            if(health.size() - healthLost < 0){
                healthLost = health.size();
            }
            for(int i = healthLost; i > 0; i--){
                health.remove(health.size()-i);
            }
            if(health.isEmpty()){
                image = null;
            }
        }
            
        else image = null;
    }
    
    public void die(){
        super.image = null;
    } 
}
