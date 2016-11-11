
package tempo.sprites;

import java.awt.Graphics;
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
    private ArrayList<Health> health = new ArrayList<Health>();
    
    public Npc(int x, int y, int width, int height, BufferedImage image, int hp, int lives, boolean isHostile) {
        super(x, y, width, height, image);
        this.lives = lives;
        this.isHostile = isHostile;
        for(int i = 0; i < hp; i++){
            health.add(new Health(x + 10*i, (y-10), 8, 8, null));
        }
    }
    

    @Override
    public void paint(Graphics g) {
        g.drawImage(super.image, super.x, super.y,super.width, super.height, null);
    }
    
    public boolean getIsHostile(){
        return isHostile;
    }
    
    public ArrayList<Health> getHealth(){
        return health;
    }
    
    public void loseHealth(int healthLost){
        if(!this.health.isEmpty())
            for(int i = healthLost; i > 0; i--){
                health.remove(health.size()-i);
            }
        else image = null;
    }
    
    public void die(){
        super.image = null;
    } 
}
