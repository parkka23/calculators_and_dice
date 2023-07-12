package dice2;

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

    public static int getResult(int fallen, int predicted) {
        int x = fallen;
        int y = predicted;
        int result = x - Math.abs(x - y) * 2;
        System.out.println("On the dice fell " + x + " points.");
        System.out.println("Result is " + x + " - abs(" + x + " - " + y + ") * 2: " + result + " points.");
        return result;
    }


    public static void play() {
        Scanner sc = new Scanner(System.in);
        String op = "";
        do {

            System.out.println("---          Start Game          ---");

            int user=DiceGame.userPlay();
            System.out.println();
            int computer=DiceGame.computerPlay();

            int userAbs=Math.abs(user);
            int computerAbs=Math.abs(computer);
            int diff=Math.abs(computerAbs-userAbs);

            System.out.println();

            System.out.println("User: "+user+ " points");
            System.out.println("Computer: "+computer + " points");

            if (computerAbs>userAbs) {
                System.out.println("Computer wins by "+diff+" points more.");
            }
            else if(computerAbs<userAbs) {
                System.out.println("User wins by "+diff+" points more.");
                System.out.println("Congratulations!");
            }
            else{
                System.out.println("Draw!");
            }

            System.out.println("\nEnter x to stop.");
            op = sc.nextLine();
        } while (op != "x");
    }

    public static int userPlay() {
        Scanner sc = new Scanner(System.in);
        int predicted = 0, fallen, dice1, dice2;

        System.out.print("Predict amount of points (2-12): ");
        try {
            predicted = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error. Enter a number.");
            DiceGame.userPlay();
        }
        if (predicted < 2 || predicted > 12) DiceGame.play();

        System.out.println("User rolls the dices...");
        dice1 = DiceGame.rollTheDice();
        dice2 = DiceGame.rollTheDice();

        DiceGame.printDice(dice1);
        DiceGame.printDice(dice2);

        fallen = dice1 + dice2;
         return DiceGame.getResult(fallen, predicted);
    }

    public static int computerPlay() {
        Scanner sc = new Scanner(System.in);
        int predicted, fallen, dice1, dice2;
        Random rand = new Random();
        predicted = rand.nextInt(11) + 2;
        System.out.println("Computer predicted "+predicted+" points.");

        System.out.println("Computer rolls the dices...");
        dice1 = DiceGame.rollTheDice();
        dice2 = DiceGame.rollTheDice();

        DiceGame.printDice(dice1);
        DiceGame.printDice(dice2);

        fallen = dice1 + dice2;
        return DiceGame.getResult(fallen, predicted);

    }

}
