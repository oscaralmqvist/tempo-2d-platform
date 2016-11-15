
package temp_map;

import java.io.PrintWriter;
import java.util.ArrayList;

public class CreateMap {
    

    public CreateMap(ArrayList<Block> bs, ArrayList<BlockData> blocksdata, int blockSize, String path) {

        try{
            PrintWriter writer = new PrintWriter(path + ".txt", "UTF-8");
            String line = "";
            int currentline = 0;
           
            for(Block b : bs) {
                if(currentline == (b.y / blockSize)) {
                    for(BlockData bd : blocksdata) {
                        if(b.id == bd.getID())
                            line += bd.getCharID();
                    }
                    if(b.id == 0) {
                        line += '-';
                    }
                } else {
                    System.out.println(currentline);
                    writer.println(line);
                    System.out.println(bs.size());
                    for(BlockData bd : blocksdata) {
                        if(b.id == bd.getID())
                            line = "" + bd.getCharID();
                    }
                    if(b.id == 0) {
                        line = "-";
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
