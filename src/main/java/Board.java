public class Board {
    Space[][] windowPattern = new Space[4][5];
    Game.Dice[][] frame = new Game.Dice[4][5];
    String name;
    String difficulty;


    private static class Space extends Game.Dice {
        int face;
        Color color;
        public Space(int face){
            this.face = face;
        }
        public Space(Color color){
            this.color = color;
        }
        enum Color {
            RED, GREEN, BLUE, YELLOW, PURPLE,
        }
    }
}
