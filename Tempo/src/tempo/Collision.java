/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.awt.Rectangle;

/**
 *
 * @author Elev
 */
public class Collision {
    //ACTOR
    Rectangle a_rect = new Rectangle();
    
    //OBJECT
    Rectangle o_rect = new Rectangle();
    
    public Collision(){
        //this.a_rect = a_rect;
        //this.o_rect = o_rect;
    }
    
    public boolean isIntersect(Rectangle a_rect, Rectangle o_rect){
        return a_rect.intersects(o_rect); 
    }
    
    public Rectangle getCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Collision side check
            //Right side
            if (CollisionRotation(a_rect, o_rect) > -140f && CollisionRotation(a_rect, o_rect) < -26f)
            {
                tempRect = a_rect;
                tempRect.x = o_rect.x + o_rect.width;
                //System.out.println("RIGHT HIT");
                return tempRect;
            }


            //Left side
            if (CollisionRotation(a_rect, o_rect) > 26f && CollisionRotation(a_rect, o_rect) < 140f)
            {
                tempRect = a_rect;
                tempRect.x = o_rect.x - a_rect.width;
                //System.out.println("LEFT HIT");
                return tempRect;
            }


            //Top
            if (CollisionRotation(a_rect, o_rect) > -26f && CollisionRotation(a_rect, o_rect) < 26f)
            {
                tempRect = a_rect;
                tempRect.y = o_rect.y - a_rect.height;
                //System.out.println("TOP HIT");
                return tempRect;
            }

            //Bottom
            if (CollisionRotation(a_rect, o_rect) < -140f || CollisionRotation(a_rect, o_rect) > 140f)
            {
                tempRect = a_rect;
                tempRect.y = o_rect.y + o_rect.height;
                //System.out.println("BOT HIT");
                return tempRect;
            }
        }
        return a_rect;
    }
    public double CollisionRotation(Rectangle position, Rectangle position2)
    {
        double degree = 0;
        degree = Math.toDegrees(Math.atan2(position2.x - position.x, position2.y - position.y));
        return degree;
    }
    public boolean getTopCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Top
            if (CollisionRotation(a_rect, o_rect) > -26f && CollisionRotation(a_rect, o_rect) < 26f)
            {
                tempRect = a_rect;
                tempRect.y = o_rect.y - a_rect.height;
                //System.out.println("TOP HIT SINGLE");
                return true;
            }
        }
        return false;
    }
    public boolean getBottomCollision(Rectangle a_rect, Rectangle o_rect){
        Rectangle tempRect = new Rectangle();
        if(isIntersect(a_rect, o_rect)){
            //Bottom
            if (CollisionRotation(a_rect, o_rect) < -140f || CollisionRotation(a_rect, o_rect) > 140f)
            {
                tempRect = a_rect;
                tempRect.y = o_rect.y + o_rect.height;
                //System.out.println("BOT HIT SINGLE");
                return true;
            }
        }
        return false;
    }
    
}
