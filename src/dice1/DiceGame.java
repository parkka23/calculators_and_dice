package dice1;

import java.util.Random;
import java.util.Scanner;

public class DiceGame {


    public static void printDice(int side) {
        String output = "";
        switch (side) {
            case 1:
                output = "+-------+\n|       |\n|   #   |\n|       |\n+-------+";
                break;
            case 2:
                output = "+-------+\n| #     |\n|       |\n|     # |\n+-------+";
                break;
            case 3:
                output = "+-------+\n| #     |\n|   #   |\n|     # |\n+-------+";
                break;
            case 4:
                output = "+-------+\n| #   # |\n|       |\n| #   # |\n+-------+";
                break;
            case 5:
                output = "+-------+\n| #   # |\n|   #   |\n| #   # |\n+-------+";
                break;
            case 6:
                output = "+-------+\n| # # # |\n|       |\n| # # # |\n+-------+";
                break;
            default:
                output = "Error";
        }

        System.out.println(output);
    }

    public static int rollTheDice() {
        Random rand = new Random();
        int side = rand.nextInt(6) + 1;
        return side;
    }

    public static void getResult(int fallen, int predicted) {
        int x = fallen;
        int y = predicted;
        int result = x - Math.abs(x - y) * 2;
        System.out.println("On the dice fell " + x + " points.");
        System.out.println("Result is " + x + " - abs(" + x + " - " + y + ") * 2: " + result + " points.");
        if (result > 0) {
            System.out.println("User wins!");
        } else System.out.println("User loses!");
    }


    public static void play() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String op = "";
        do {
            int predicted = 0, fallen, dice1, dice2;

            System.out.println("---          Start Game          ---");
            System.out.print("Predict amount of points (2-12): ");
            try {
                predicted = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error. Enter a number.");
                DiceGame.play();
            }

            if (predicted < 2 || predicted > 12) DiceGame.play();

            dice1 = DiceGame.rollTheDice();
            dice2 = DiceGame.rollTheDice();
            DiceGame.printDice(dice1);
            DiceGame.printDice(dice2);

            fallen = dice1 + dice2;

            DiceGame.getResult(fallen, predicted);


            System.out.println("Enter x to stop.");
            op = sc2.nextLine();
        } while (op != "x");
    }


}
