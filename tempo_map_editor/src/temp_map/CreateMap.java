
package temp_map;

import java.io.PrintWriter;
import java.util.ArrayList;

public class CreateMap {
    

    public CreateMap(ArrayList<Block> bs, int blockSize) {

        try{
            PrintWriter writer = new PrintWriter("level1.txt", "UTF-8");
            /*
            String line = "";
            int currentline = 0;
            for(int i = 0; i < bs.size(); i++) {
                if(bs.get(i).y != bs.get(i+1).y) {
                    System.out.println(line);
                    writer.println("asdasd");
                    System.out.println(currentline);
                    currentline++;
                    line = "";                    
                } else {
                    if(bs.get(i).id == 0) {
                        line += "-";
                    }
                    if(bs.get(i).id == 1) {
                        line += "l";
                    }  
                }
            }
            */
            
            String line = "";
            int currentline = 0;
           
            for(Block b : bs) {
                if(currentline == (b.y / blockSize)) {
                    if(b.id == 0) {
                        line += "-";
                    }
                    if(b.id == 1) {
                        line += "l";
                    }     
                } else {
                    System.out.println(currentline);
                    writer.println(line);
                    System.out.println(bs.size());
                    if(b.id == 0) {
                        line = "-";
                    }
                    if(b.id == 1) {
                        line = "l";
                    }   
                    currentline++;
                }
            }
            writer.println(line);
            writer.close();
        } catch (Exception e) {
           // do something
        }
    }
    
    
}
