public class Board extends Game.Dice{
    Space[][] windowPattern = new Space[4][5];
    Game.Dice[][] frame = new Game.Dice[4][5];
    String name;
    int difficulty;
    enum Color {
        RED,YELLOW, GREEN, BLUE , PURPLE,BLANK
    }

    public Board(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    //    set dice space
    public void setSpace(int row, int col, int face) {
        this.windowPattern[row][col] = new Space(face);
    }

    //set blank space
    public void setSpace(int row, int col) {
        this.windowPattern[row][col] = new Space();

    }

    //set color space
    public void setSpace(int row, int col, Color color) {
        this.windowPattern[row][col] = new Space(color);
    }


    static class Space  {
        int face;
        Color color;

        public Space(int face) {
            this.face = face;
        }

        public Space(Color color) {
            this.color = color;
        }

        public Space(int face, Color color) {
            this.face = face;
            this.color = color;
        }

        public Space() {
            this.face=0;
            this.color=Color.BLANK;
        }


    }
}
