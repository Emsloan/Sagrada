import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PatternBuilder implements java.io.Serializable {

    public static ArrayList<Board> builder() throws IOException {
        ArrayList<Board> list = new ArrayList<>();

        Board fractalDrops = new Board("Fractal Drops", 3);
//            row 1
        fractalDrops.setSpace(0, 0);
        fractalDrops.setSpace(0, 1, 4);
        fractalDrops.setSpace(0, 2);
        fractalDrops.setSpace(0, 3, Board.Color.YELLOW);
        fractalDrops.setSpace(0, 4, 6);
//            row 2
        fractalDrops.setSpace(1, 0, Board.Color.RED);
        fractalDrops.setSpace(1, 1);
        fractalDrops.setSpace(1, 2, 2);
        fractalDrops.setSpace(1, 3);
        fractalDrops.setSpace(1, 4);
//            row 3
        fractalDrops.setSpace(2, 0);
        fractalDrops.setSpace(2, 1);
        fractalDrops.setSpace(2, 2, Board.Color.RED);
        fractalDrops.setSpace(2, 3, Board.Color.PURPLE);
        fractalDrops.setSpace(2, 4, 1);
//            row 4
        fractalDrops.setSpace(3, 0, Board.Color.BLUE);
        fractalDrops.setSpace(3, 1, Board.Color.YELLOW);
        fractalDrops.setSpace(3, 2);
        fractalDrops.setSpace(3, 3);
        fractalDrops.setSpace(3, 4);

//        try opening file
        FileOutputStream fileOut = new FileOutputStream("windowpatterns.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        list.add(fractalDrops);
        out.writeObject(list);
        out.close();
        fileOut.close();
        return list;
    }
}
