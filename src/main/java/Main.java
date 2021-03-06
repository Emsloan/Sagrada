import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static ArrayList<Board> patterns;

    public static void main(String[] args) throws IOException {
        patterns = PatternBuilder.builder();


//        Ask for number of players
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
                    boolean computer = false;
                    if (players == 1) {
                        computer = true;
                        players += 1;
                    }
                    game = new Game(players, computer);

                    break;
                } catch (NumberFormatException e) {
                    send("That isn't an acceptable answer. Please enter a number of players. (2-4)");
                }

            }
            int capacity;
//            set dice offer capacity
            capacity = (game.gameType * 2) + 1;
            game.offer = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                game.offer.add(game.bag.remove(i));
            }

            if (game.computer) {
                localCPUsetUp(game);
            } else {
                hotSeat(game);
            }


        }


    }


    private static void hotSeat(Game game) {
    }

    private static void localCPUsetUp(Game game) {

        while (true) {
            System.out.println("Please select a window pattern card:");
            showPatterns();
            try (Scanner scanner = new Scanner(System.in)) {
                int choice = scanner.nextInt();
                choice -= 1;
                game.players.get(0).board.setWindowPattern(patterns.get(choice).windowPattern);
                break;
            } catch (NumberFormatException e) {
                System.out.println("That isn't an acceptable answer, please try again.");
            }
        }
//
        boolean turn;
        if (new Random().nextInt(2) % 2 == 0) {
            turn = true;
        } else
            turn = false;

        while (!game.won) {
//                TODO:each loop active player is updated and passed to 'turn' method
            if (turn) turn(game, game.players.get(0));
            else
                turn(game, game.players.get(1));
            turn = !turn;
        }
    }

    private static void showPatterns() {
        int i = 1;
        for (Board b : patterns) {
            System.out.println(b.name + " [" + i + "]");
            i++;
        }

    }

    private static void cpuTurn(Game game) {
        showOffer(game.offer);
    }

    private static void turn(Game game, Player player) {
        showOffer(game.offer);
        int choice;
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Select a dice from the offer (1-" + game.offer.size());
                choice = scanner.nextInt();
                choice-=1;
                if (choice > game.offer.size() || choice < 0) throw new NumberFormatException();
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
