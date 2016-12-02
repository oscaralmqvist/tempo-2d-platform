
package states;

import java.awt.Graphics;

public abstract class State {
    
    private boolean stateKeys;
    
    public State() {
        stateKeys = true;
    }
    
    public void activateKeys() {
        stateKeys = true; 
    }
    
    public void deactivateKeys() {
        stateKeys = false;
    }
    
    public Class theState() {
       return getClass();
    }
    
    public abstract void paint(Graphics g);
    
}
