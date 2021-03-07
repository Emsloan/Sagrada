import java.io.Serializable;
import java.util.Arrays;

public class Board extends PlayerWindow implements java.io.Serializable {



    Space[][] windowPattern = new Space[4][5];
    Game.Dice[][] frame = new Game.Dice[4][5];
    String name;
    int difficulty;
    enum Color {
        RED,YELLOW, GREEN, BLUE , PURPLE, BLANK
    }

    public Space[][] getWindowPattern() {
        return windowPattern;
    }

    public void setWindowPattern(Space[][] windowPattern) {
        this.windowPattern = windowPattern;
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


    static class Space implements java.io.Serializable {
        int face;
        Color color;

        public Space(int face) {
            this.face = face;
        }

        public Space(Color color) {
            this.color = color;
        }

        public Space() {
            this.face=0;
            this.color=Color.BLANK;
        }


    }

    @Override
    public String toString() {
        return "Board{" +
                "windowPattern=" + Arrays.toString(windowPattern) +
                ", frame=" + Arrays.toString(frame) +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
