
package tempo_tutorial;

import javax.swing.*;

public class Window extends JFrame {
    
    private GamePanel gp;
    private GameEngine ge;
    
    public Window() {
        gp =  new GamePanel();
        ge  = new GameEngine(gp);
        setTitle(Tempo_tutorial.NAME);
        setVisible(true);
        setSize(Tempo_tutorial.WIDTH, Tempo_tutorial.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new Keyboard(gp));
        addMouseListener(new Mouse(gp));
        add(gp);
        
    }

}
