
package tempo;

import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    private ArrayList<Clip> sounds = new ArrayList<Clip>();
    
    public Sound() {
        loadSound("neeeej");
    }
   
    
    public void loadSound(String name) {
     try {
        Clip c = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
          new File("src/resources/" + name + ".wav"));
        c.open(inputStream);
        sounds.add(c);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    
    public void playSound(int i) {
        sounds.get(i).start();
    }
    
    public void playSound(String name) {
        switch(name) {
            case "die":
                sounds.get(0).start();
            break;
        }
    }
    

    
}
