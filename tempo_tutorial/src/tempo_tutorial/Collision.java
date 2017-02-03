package tempo_tutorial;
import java.awt.Rectangle;
/**
 * @author Svante Lindgren
 * Svantech Studios AB
 * All rights reserved 2017
 */
public class Collision {
    
    //Kollar ifall två rektanglar kolliderar 
    public boolean isIntersect(Rectangle a_rect, Rectangle o_rect){
        return a_rect.intersects(o_rect); 
    }
    
    
    public double collisionRotation(Rectangle position, Rectangle position2)
    {
        return Math.toDegrees(Math.atan2(position2.x - position.x, position2.y - position.y));
    }
    
    public Rectangle getCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Collision side check
            //Right side
            if (collisionRotation(a_rect, o_rect) > -140f && collisionRotation(a_rect, o_rect) < -26f){
                tempRect = a_rect;
                tempRect.x = o_rect.x + o_rect.width;
                //System.out.println("RIGHT HIT");
                return tempRect;
            }
            //Left side
            if (collisionRotation(a_rect, o_rect) > 26f && collisionRotation(a_rect, o_rect) < 140f){
                tempRect = a_rect;
                tempRect.x = o_rect.x - a_rect.width;
                //System.out.println("LEFT HIT");
                return tempRect;
            }
            //Top
            if (collisionRotation(a_rect, o_rect) > -26f && collisionRotation(a_rect, o_rect) < 26f){
                tempRect = a_rect;
                tempRect.y = o_rect.y - a_rect.height;
                //System.out.println("TOP HIT");
                return tempRect;
            }
            //Bottom
            if (collisionRotation(a_rect, o_rect) < -140f || collisionRotation(a_rect, o_rect) > 140f){
                tempRect = a_rect;
                tempRect.y = o_rect.y + o_rect.height;
                //System.out.println("BOT HIT");
                return tempRect;
            }
        }
        return a_rect;
    }
    
    public boolean getTopCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Top
            if (collisionRotation(a_rect, o_rect) > -26f && collisionRotation(a_rect, o_rect) < 26f){
                tempRect = a_rect;
                tempRect.y = o_rect.y - a_rect.height;
                return true;
            }
        }
        return false;
    }
    
    public boolean getBottomCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Bottom
            if (collisionRotation(a_rect, o_rect) < -140f || collisionRotation(a_rect, o_rect) > 140f){
                tempRect = a_rect;
                tempRect.y = o_rect.y + o_rect.height;
                return true;
            }
        }
        return false;
    } 
}
