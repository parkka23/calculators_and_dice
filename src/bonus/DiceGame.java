package bonus;

import java.util.HashMap;
import java.util.Map;
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
        int userScore, computerScore;
        int[] userPredicted=new int[3], userFallen=new int[3],userResult=new int[3],userResultPen=new int[3], compPredicted=new int[3], compFallen=new int[3],compResult=new int[3],compResultPen=new int[3], userPenalty=new int[3], compPenalty=new int[3];

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String op = "y";

        int round, userAbs=0,computerAbs=0,diff=0;
        while(op.equals("y")) {
            userScore=0;
            computerScore=0;
            System.out.println("----------------START GAME----------------");
            for (int i = 0; i < 3; i++) {
                round = i + 1;
                System.out.println("-----------------ROUND " + round + "------------------");

                Map<String, Integer> userMap = DiceGame. userPlay(round);
                userPredicted[i] = userMap.get("predicted");
                userFallen[i] = userMap.get("fallen");
                userPenalty[i] = userMap.get("penalty");
                userResult[i] = DiceGame.getResult(userFallen[i], userPredicted[i]);
                userResultPen[i]=userResult[i]-userPenalty[i];

                System.out.println();

                Map<String, Integer> compMap = DiceGame.computerPlay(round, diff);
                compPredicted[i] = compMap.get("predicted");
                compFallen[i] = compMap.get("fallen");
                compPenalty[i] = compMap.get("penalty");
                compResult[i] = DiceGame.getResult(compFallen[i], compPredicted[i]);
                compResultPen[i]=compResult[i]-compPenalty[i];

                userScore += userResultPen[i];
                computerScore += compResultPen[i];

                userAbs = Math.abs(userScore);
                computerAbs = Math.abs(computerScore);
                diff = Math.abs(computerAbs - userAbs);

                System.out.println("--------------CURRENT SCORE---------------");
                System.out.println("User: " + userScore + " points");
                System.out.println("User penalty: " + userPenalty[i] + " points");
                System.out.println("Computer: " + computerScore + " points");
                System.out.println("Computer penalty: " + compPenalty[i] + " points");
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
                System.out.println("       | Dice: " + String.format("%9d", userFallen[i]) + " | Dice: " + String.format("%9d", compFallen[i]));
                System.out.println(" - " + round + " - | Result: " + String.format("%7d", userResult[i]) + " | Result: " + String.format("%7d", compResult[i]));
                System.out.println("       | Penalty: " + String.format("%6d", userPenalty[i]) + " | Penalty: " + String.format("%6d", compPenalty[i]));
                System.out.println("       | Result+pen: " + String.format("%3d", userResultPen[i]) + " | Result+pen: " + String.format("%3d", compResultPen[i]));
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

    public static Map<String, Integer> userPlay(int round) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int predicted = 0, fallen, dice1=0, dice2=0, penalty=0;

        System.out.print("Predict amount of points (2-12): ");
        try {
            predicted = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error. Enter a number.");
            DiceGame.userPlay(round);
        }
        if (predicted < 2 || predicted > 12) {
            System.out.println("Error. Enter a number from 2 to 12.");
            DiceGame.userPlay(round);
        }
        System.out.print("Do you want to cheat (y/n)? ");
        String op=sc2.nextLine();
        if (op.equals("y")) {
            int chance;
            Random rand = new Random();
            if (round == 1) {
                chance = rand.nextInt(1);
            } else if (round == 2) {
                chance = rand.nextInt(3);
            } else {
                chance = rand.nextInt(5);
            }
            if (chance==1) {
                dice1=predicted/2;
                dice2=predicted-dice1;
            }
            else {
                dice1 = DiceGame.rollTheDice();
                dice2 = DiceGame.rollTheDice();
                penalty=10;
            }
        }
        else if(op.equals("n")) {
            dice1 = DiceGame.rollTheDice();
            dice2 = DiceGame.rollTheDice();
        }
        else {
            DiceGame.userPlay(round);
        }

        System.out.println("User rolls the dices...");
        DiceGame.printDice(dice1);
        DiceGame.printDice(dice2);

        fallen = dice1 + dice2;
        Map<String, Integer> result = new HashMap<>();
        result.put("predicted", predicted);
        result.put("fallen", fallen);
        result.put("penalty", penalty);
        return result;
    }

    public static Map<String, Integer>  computerPlay(int round, int diff) {
        int predicted, fallen, dice1=0, dice2=0,penalty=0;
        Random rand = new Random();
        predicted = rand.nextInt(10) + 2;
        System.out.println("Computer predicted "+predicted+" points.");
        boolean cheat=false;
        int chanceCheat;
        if(round==1) {
            chanceCheat=rand.nextInt(4)+1;
            if(chanceCheat==1) cheat=true;
        }
        else {
            if(diff>5) {
                if(diff>15) {
                    chanceCheat=rand.nextInt(4)+1;
                    if(chanceCheat < 4) cheat=true;
                }
                else {
                    chanceCheat=rand.nextInt(4)+1;
                    if (chanceCheat<3) cheat =true;
                }
            }
            else {
                chanceCheat=rand.nextInt(4)+1;
                if(chanceCheat==1) cheat=true;
            }
        }

        if (cheat) {
            int chance;
            if (round == 1) {
                chance = rand.nextInt(1);
            } else if (round == 2) {
                chance = rand.nextInt(3);
            } else {
                chance = rand.nextInt(5);
            }
            if (chance==1) {
                dice1=predicted/2;
                dice2=predicted-dice1;
            }
            else {
                dice1 = DiceGame.rollTheDice();
                dice2 = DiceGame.rollTheDice();
                penalty=10;
            }
        }
        else {
            dice1 = DiceGame.rollTheDice();
            dice2 = DiceGame.rollTheDice();
        }

        System.out.println("Computer rolls the dices...");
        DiceGame.printDice(dice1);
        DiceGame.printDice(dice2);

        fallen = dice1 + dice2;
        Map<String, Integer> result = new HashMap<>();
        result.put("predicted", predicted);
        result.put("fallen", fallen);
        result.put("penalty", penalty);
        return result;
    }

}
