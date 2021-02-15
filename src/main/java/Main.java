import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        try (Scanner input = new Scanner(System.in)) {
            send("Welcome to Sagrada. How many player's? (2-4)");
            String response;
            Game game;
            while (true) {
                response = input.nextLine();
                int players = 0;
                try {
                    players = Integer.parseInt(response);
                }catch (NumberFormatException e){
                    send("That isn't an acceptable answer. Please enter a number of players. (2-4)");
                }
                if(players<5&&players>1){
                    game = new Game(players);
                    break;
                }
                else{
                    send("That isn't an acceptable answer. Please enter a number of players. (2-4)");
                }
            }

            int capacity = (game.gameType * 2) + 1;
            List<Game.Dice> offer = new ArrayList<>(capacity);
            for(int i = 0; i<capacity;i++){
                offer.add(game.bag.remove(i));
            }


            showOffer(offer);

        }


    }

    private static void showOffer(List<Game.Dice> offer) {

        for (Game.Dice dice : offer) {
            send("[" + dice.color.toString() + dice.face + "] ");
        }
    }

    private static void send(String s) {
        System.out.println(s);
    }
}
