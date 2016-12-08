
package states;

import java.util.Stack;

public class StateManager {
    
    private Stack<State> states = new Stack<State>();
    
    public void startGame() {
        if(!states.isEmpty() && states.peek().getClass() != MenuState.class) {
            if(states.peek().getClass() == PauseState.class)
                states.pop();
        }
        else {
            states.empty();
            states.add(new GameState());
        }
    }
    
    public void loadGame() {
        states.add(new MenuState());
    }
    
    public GameState getGameState() {
        return (GameState) states.peek();
    }
    
    public void pauseGame() {
        synchronized(this) {
            if(states.peek().getClass() == GameState.class) {
                disableAllStatesKeyboard();
                states.add(new PauseState());
            }
        }
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
