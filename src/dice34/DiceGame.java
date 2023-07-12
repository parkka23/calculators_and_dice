package dice34;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DiceGame {


    static int userScore;
    static int computerScore;
    static int[] userPredicted=new int[3], userFallen=new int[3],userResult=new int[3], compPredicted=new int[3], compFallen=new int[3],compResult=new int[3];




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
        int userScore, computerScore;
        int[] userPredicted=new int[3], userFallen=new int[3],userResult=new int[3], compPredicted=new int[3], compFallen=new int[3],compResult=new int[3];

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String op = "y";

        int round, userAbs=0,computerAbs=0,diff=0;
        while(op.equals("y")) {
            userScore=0;
            computerScore=0;
            System.out.println("------------------------------------------");

            System.out.println("----------------START GAME----------------");
            for (int i = 0; i < 3; i++) {
                round = i + 1;
                System.out.println("-----------------ROUND " + round + "------------------");

                Map<String, Integer> userMap = DiceGame.userPlay();
                userPredicted[i] = userMap.get("predicted");
                userFallen[i] = userMap.get("fallen");
                userResult[i] = DiceGame.getResult(userFallen[i], userPredicted[i]);

                System.out.println();

                Map<String, Integer> compMap = DiceGame.computerPlay();
                compPredicted[i] = compMap.get("predicted");
                compFallen[i] = compMap.get("fallen");
                compResult[i] = DiceGame.getResult(compFallen[i], compPredicted[i]);

                userScore += userResult[i];
                computerScore += compResult[i];

                System.out.println();

                userAbs = Math.abs(userScore);
                computerAbs = Math.abs(computerScore);
                diff = Math.abs(computerAbs - userAbs);

                System.out.println("--------------CURRENT SCORE---------------");
                System.out.println("User: " + userScore + " points");
                System.out.println("Computer: " + computerScore + " points");
                if (computerAbs > userAbs) {
                    System.out.println("Computer is ahead by " + diff + " points.");
                } else if (computerAbs < userAbs) {
                    System.out.println("User is ahead by " + diff + " points.");
                } else {
                    System.out.println("Draw!");
                }
                System.out.println("------------------------------------------");
                System.out.println();
            }

            System.out.println("-------------- FINISH GAME ---------------");
            System.out.println(" ROUND |      USER       |   COMPUTER    ");
            for (int i = 0; i < 3; i++) {
                round = i + 1;
                System.out.println("-------+-----------------+-----------------");
                System.out.println("       | Predicted: " + String.format("%4d", userPredicted[i]) + " | Predicted: " + String.format("%4d", compPredicted[i]));
                System.out.println(" - " + round + " - | Dice: " + String.format("%9d", userFallen[i]) + " | Dice: " + String.format("%9d", userFallen[i]));
                System.out.println("       | Result: " + String.format("%7d", userResult[i]) + " | Result: " + String.format("%7d", userResult[i]));
            }
            System.out.println("-------+-----------------+-----------------");
            System.out.println(" TOTAL | Points: " + String.format("%7d", userScore) + " | Points: " + String.format("%7d", computerScore));

            if (computerAbs > userAbs) {
                System.out.println("Computer wins by " + diff + " points more. (ಥ﹏ಥ)");
            } else if (computerAbs < userAbs) {
                System.out.println("User wins by " + diff + " points more. ٩(^ᴗ^)۶");
            } else {
                System.out.println("Draw!");
            }

            System.out.print("\nDo you want to play one more time (y/n)? ");
            op=sc2.nextLine();
            if(op.equals("n")) {
                System.out.println("Goodbye! (￣▽￣)ノ");
                break;
            }
            System.out.println();
        }
    }

    public static Map<String, Integer> userPlay() {
        Scanner sc = new Scanner(System.in);
        int predicted = 0, fallen, dice1, dice2;

        System.out.print("Predict amount of points (2-12): ");
        try {
            predicted = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error. Enter a number.");
            DiceGame.play();
        }
        if (predicted < 2 || predicted > 12) {
            System.out.println("Error. Enter a number from 2 to 12.");
            DiceGame.play();
        }

        System.out.println("User rolls the dices...");
        dice1 = DiceGame.rollTheDice();
        dice2 = DiceGame.rollTheDice();

        DiceGame.printDice(dice1);
        DiceGame.printDice(dice2);

        fallen = dice1 + dice2;
        Map<String, Integer> result = new HashMap<>();
        result.put("predicted", predicted);
        result.put("fallen", fallen);
        return result;
    }

    public static Map<String, Integer>  computerPlay() {
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
        Map<String, Integer> result = new HashMap<>();
        result.put("predicted", predicted);
        result.put("fallen", fallen);
        return result;

    }

}
