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

    static JSONArray patterns;

    public static void main(String[] args) throws IOException {
        patterns = PatternBuilder.builder();


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


    private static void hotSeat(Game game) {
    }

    private static void localComputerGame(Game game) {

//        TODO: get player choice for pattern card, update game.player.board.pattern to match pattern choice
        while (true) {
            System.out.println("Please select a window pattern card:");
            showPatterns();
            try (Scanner scanner = new Scanner(System.in)) {
                scanner.nextInt();
            } catch (NumberFormatException e){

            }
        }
        int x = new Random().nextInt(2);
        while (!game.won) {
            if (x % 2 == 0) {
//                TODO:each loop active player is updated and passed to 'turn' method
                playerTurn(game,game.players.get(0));
                cpuTurn(game);
            }

        }
    }

    private static void showPatterns() {
        System.out.println(patterns);
    }

    private static void cpuTurn(Game game) {
        showOffer(game.offer);
    }

    private static void playerTurn(Game game,Player player) {

        showOffer(game.offer);
        int choice;
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Select a dice from the offer (1-" + game.offer.size());
                choice = scanner.nextInt();
                Player.placeDice(game.offer.remove(choice));
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
