
package states;

import java.util.Stack;

public class StateManager {
    
    private Stack<State> states = new Stack<State>();
    
    public void startGame() {
        states.empty();
        states.add(new GameState());
    }
    
    public void pauseGame() {
        
    }
    
    
    public void disableAllStatesKeyboard() {
        for(State state : states) {
            state.deactivateKeys();
        }
    }
    
    public void pop() {
        states.pop();
    }
    
    public Stack<State> getStates() {
        return states;
    }
    
    
}
