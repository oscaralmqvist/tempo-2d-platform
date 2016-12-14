
package tempo;

import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    private Clip music = loadSound("music");
   
    public Clip loadSound(String name) {
        Clip c = null;
        try {
            c = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
            new File("src/resources/sounds/" + name + ".wav"));
            c.open(inputStream);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return c;
    }
    
    public void playSound(String name) {
        Clip c = loadSound(name);
        c.setFramePosition(0);
        c.start();
    }
    
    public void playMusic() {
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }
 
}
