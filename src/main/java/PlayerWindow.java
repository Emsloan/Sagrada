import javax.swing.*;

public class PlayerWindow {
    private JLabel Sagrada;
    private JLabel zero_zero;
    private JLabel one_zero;
    private JLabel two_zero;
    private JLabel three_zero;
    private JLabel zero_one;
    private JLabel zero_two;
    private JLabel zero_three;
    private JLabel zero_four;
    private JLabel one_one;
    private JLabel two_one;
    private JLabel three_one;
    private JLabel one_two;
    private JLabel two_two;
    private JLabel three_two;
    private JLabel one_three;
    private JLabel two_three;
    private JLabel three_three;
    private JLabel one_four;
    private JLabel two_four;
    private JLabel three_four;

    JLabel[][] pattern;

    public PlayerWindow() {
        pattern[0][0] = zero_zero;
        pattern[0][1] = zero_one;
        pattern[0][2] = zero_two;
        pattern[0][3] = zero_three;
        pattern[0][4] = zero_four;

        pattern[1][0] = one_zero;
        pattern[1][1] = one_one;
        pattern[1][2] = one_two;
        pattern[1][3] = one_three;
        pattern[1][4] = one_four;

        pattern[2][0] = two_zero;
        pattern[2][1] = two_one;
        pattern[2][2] = two_two;
        pattern[2][3] = two_three;
        pattern[2][4] = two_four;

        pattern[3][0] = three_zero;
        pattern[3][1] = three_one;
        pattern[3][2] = three_two;
        pattern[3][3] = three_three;
        pattern[3][4] = three_four;

    }

    public void setWindowPattern(Board.Space[][] windowPattern) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Board.Space space = windowPattern[i][j];
                String choice;
                if (String.valueOf(space.color).equals("BLANK")) choice = String.valueOf(space.face);
                else choice = String.valueOf(space.color);
                pattern[i][j].setIcon(new ImageIcon(getPath(choice)));
            }
        }
    }

    private String getPath(String choice) {
        return switch (choice) {
            case ("1") -> "java/images/Dice pattern 1.png";
            case ("2") -> "java/images/Dice pattern 2.png";
            case ("3") -> "java/images/Dice pattern 3.png";
            case ("4") -> "java/images/Dice pattern 4.png";
            case ("5") -> "java/images/Dice pattern 5.png";
            case ("6") -> "java/images/Dice pattern 6.png";
            case ("RED") -> "java/images/color pattern red.png";
            case ("BLUE") -> "java/images/color pattern blue.png";
            case ("GREEN") -> "java/images/color pattern green.png";
            case ("PURPLE") -> "java/images/color pattern purple.png";
            case ("YELLOW") -> "java/images/color pattern yellow.png";
            default -> null;
        };
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        zero_zero = new JLabel();
    }
}
