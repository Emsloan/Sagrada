import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    static ArrayList<Board> patterns;

    public static void main(String[] args) {
        patterns = loadPatterns();


        try (Scanner input = new Scanner(System.in)) {
            send("Welcome to Sagrada. How many players? (2-4)");
            String response;
            Game game;
            while (true) {
                response = input.nextLine();
                int players = 0;
                try {
                    players = Integer.parseInt(response);
                    if (!(players < 5 && players > 0))
                        throw new NumberFormatException();
                    game = new Game(players);

                    break;
                } catch (NumberFormatException e) {
                    send("That isn't an acceptable answer. Please enter a number of players. (2-4)");
                }

            }
            int capacity;
//            set dice offer capacity for all game types, except for single player with cpu
            if (game.gameType != 1) {
                capacity = (game.gameType * 2) + 1;
            }
//            single player with cpu capacity set
            else
                capacity = (4) + 1;
            game.offer = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                game.offer.add(game.bag.remove(i));
            }

            if (game.gameType == 1) {
                localComputerGame(game);
            } else {
                hotSeat(game);
            }


        }


    }

    private static ArrayList<Board> loadPatterns() throws IOException {

        JSONArray patternList = new JSONArray();

        File tempFile = new File("/windowpatterns.json");

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

//        try opening file
        try (FileReader reader = new FileReader("windowpatterns.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            patternList = (JSONArray) obj;
            System.out.println(patternList);

            //Iterate over employee array
            patternList.forEach(emp -> parsePatternObject((JSONObject) emp));

//            if file doesn't exist (e.g. first run) create it and populate
        } catch (FileNotFoundException e) {
            try (FileWriter file = new FileWriter("windowpatterns.json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(patternList.toJSONString());
                file.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    private static void parsePatternObject(JSONObject pattern) {

        //Get pattern object within list
        JSONObject patternObject = (JSONObject) pattern.get("pattern");

        //Get pattern name
        String name = (String) patternObject.get("name");

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }

    private static void hotSeat(Game game) {
    }

    private static void localComputerGame(Game game) {

        while (true) {
            System.out.println("Please select a window pattern card:");
            showPatterns();
            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextInt();
            }


        }
        int x = new Random().nextInt(2);
        while (!game.won) {
            if (x % 2 == 0) {
                playerTurn(game);
                cpuTurn(game);
            }

        }
    }

    private static void showPatterns() {
    }

    private static void cpuTurn(Game game) {
        showOffer(game.offer);
    }

    private static void playerTurn(Game game) {

        showOffer(game.offer);
        int choice;
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Select a dice from the offer (1-" + game.offer.size());
                choice = scanner.nextInt();
                placeDice(game.offer.remove(choice));
                break;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid choice.");

            }
        }


    }


    private static void showOffer(List<Game.Dice> offer) {

        for (Game.Dice dice : offer) {
            //            String output = String.format("%s = %d", "joe", 35);
            String output = String.format("[%s", dice.color.toString());
            System.out.print(output);
            for (int i = 0; i < 6 - dice.color.toString().length(); i++) {
                System.out.print(" ");
            }
            System.out.print(dice.face);
            System.out.print("]  ");
//            System.out.print(("[" + dice.color.toString() + dice.face + "]  "));
        }
        System.out.println();

        for (int i = 0; i < offer.size(); i++) {
            System.out.print(("  ( " + (i + 1) + " )    "));
        }
        System.out.println();
    }

    private static void send(String s) {
        System.out.println(s);
    }
}
