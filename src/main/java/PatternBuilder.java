import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PatternBuilder {
    public static JSONArray builder() throws IOException {

        JSONArray patternList = new JSONArray();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

//        try opening file
        try (FileReader reader = new FileReader("windowpatterns.json")) {
            //Read JSON file
            patternList = (JSONArray) jsonParser.parse(reader);

            Object obj = jsonParser.parse(reader);

            //Iterate over pattern array

            patternList.forEach(pat -> parsePatternObject((JSONObject) pat));

//            if file doesn't exist (e.g. first run) create it and populate
        } catch (FileNotFoundException e) {

            JSONObject fractalDropsJSON = new JSONObject();
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

            fractalDropsJSON.put("board", fractalDrops);
            patternList.add(fractalDropsJSON);

            try (FileWriter file = new FileWriter("windowpatterns.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(patternList.toJSONString());
                file.flush();
//                TODO: something with this after it's written, probably return?

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return patternList;
    }

    private static void parsePatternObject(JSONObject pattern) {

        //Get board object within list
        JSONObject patternObject = (JSONObject) pattern.get("board");

        //Get pattern name
        String name = (String) patternObject.get("name");
        System.out.println(name);

        //Get pattern difficulty
        String difficulty = (String) patternObject.get("difficulty");
        System.out.println(difficulty);
    }
}
