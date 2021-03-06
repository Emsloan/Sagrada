import java.util.*;

public class Game {
    boolean won;
    int gameType;
    boolean computer;
    List<Game.Dice> offer;
    ArrayList<Player> players = new ArrayList<Player>();

    List<Dice> bag = new ArrayList<>();

    public Game(int players, boolean computer) {
        won = false;
        this.computer=computer;
        gameType = players;
        for(int i = 0; i<players;i++) {
            this.players.add(new Player());
        }
        Dice.Color[] colors = new Dice.Color[]{
                Dice.Color.RED,
                Dice.Color.BLUE,
                Dice.Color.GREEN,
                Dice.Color.PURPLE,
                Dice.Color.YELLOW};

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 18; i++) {
                bag.add(new Dice(colors[j]));
            }
        }
        Collections.shuffle(bag);
    }

    public static class Dice {
        Color color;

        public void setFace(int face) {
            this.face = face;
        }

        int face;
        Random random = new Random();

        public Dice() {
        }

        public Dice(Color color) {

            this.color = color;
            face = random.nextInt(5) + 1;

        }

        enum Color {
            RED, GREEN, BLUE, YELLOW, PURPLE,
        }

    }


}