package io.github.zrdzn.aoc.day.days;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import io.github.zrdzn.aoc.day.Day;

public class Day2 implements Day {

    private static final int WIN_POINTS = 6;
    private static final int LOSE_POINTS = 0;
    private static final int DRAW_POINTS = 3;
    private static final int ROCK_POINTS = 1;
    private static final int PAPER_POINTS = 2;
    private static final int SCISSOR_POINTS = 3;

    @Override
    public void run(List<String> input) {
        // Part 1 - Start

        int score = 0;
        for (String line : input) {
            String[] splitLine = line.split(" ");
            String opponentDecision = splitLine[0];
            String myDecision = splitLine[1];
            score += this.calculatePointsPartOne(opponentDecision, myDecision);
        }
        System.out.println("My total score (1): " + score);
        // Part 1 - End

        // Part 2 - Start
        score = 0;
        for (String line : input) {
            String[] splitLine = line.split(" ");
            String opponentDecision = splitLine[0];
            String expectedDecision = splitLine[1];
            score += this.calculatePointsPartTwo(opponentDecision, expectedDecision);
        }
        System.out.println("My total score (2): " + score);
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day2.txt").toURI());
    }

    private int calculatePointsPartOne(String opponentDecision, String myDecision) {
        int myDecisionPoints = getDecisionPoints(myDecision);
        if (isDraw(opponentDecision, myDecision)) {
            // Draw
            return DRAW_POINTS + myDecisionPoints;
        } else if (isRock(myDecision)) {
            // Opponent wins Paper -> Rock
            if (isPaper(opponentDecision)) {
                return LOSE_POINTS + myDecisionPoints;
            }
        } else if (isPaper(myDecision)) {
            // Opponent wins Scissors -> Paper
            if (isScissors(opponentDecision)) {
                return LOSE_POINTS + myDecisionPoints;
            }
        } else if (isScissors(myDecision)) {
            // Opponent wins Rock -> Scissors
            if (isRock(opponentDecision)) {
                return LOSE_POINTS + myDecisionPoints;
            }
        }

        // I win
        return WIN_POINTS + myDecisionPoints;
    }

    /*
    X - Lose
    Y - Draw
    Z - Win
     */
    private int calculatePointsPartTwo(String opponentDecision, String expectedDecision) {
        int losePoints = LOSE_POINTS + getDecisionPoints(getResultSymbolFor(opponentDecision, GameResult.LOSE));
        int drawPoints = DRAW_POINTS + getDecisionPoints(getResultSymbolFor(opponentDecision, GameResult.DRAW));
        int winPoints = WIN_POINTS + getDecisionPoints(getResultSymbolFor(opponentDecision, GameResult.WIN));

        int finalPoints = 0;

        switch (expectedDecision) {
            case "X" -> finalPoints = losePoints;
            case "Y" -> finalPoints = drawPoints;
            case "Z" -> finalPoints = winPoints;
        }

        return finalPoints;
    }

    private static int getDecisionPoints(String symbol) {
        return isRock(symbol) ? ROCK_POINTS :
                isPaper(symbol) ? PAPER_POINTS :
                isScissors(symbol) ? SCISSOR_POINTS : 0;
    }

    private static String getResultSymbolFor(String symbol, GameResult expectedResult) {
        String resultSymbol = null;

        switch (expectedResult) {
            case WIN -> {
                if (isRock(symbol)) {
                    // Paper
                    resultSymbol = "B";
                } else if (isPaper(symbol)) {
                    // Scissors
                    resultSymbol = "C";
                } else if (isScissors(symbol)) {
                    // Rock
                    resultSymbol = "A";
                }
            }
            case LOSE -> {
                if (isRock(symbol)) {
                    // Scissors
                    resultSymbol = "C";
                } else if (isPaper(symbol)) {
                    // Rock
                    resultSymbol = "A";
                } else if (isScissors(symbol)) {
                    // Paper
                    resultSymbol = "B";
                }
            }
            case DRAW -> {
                if (isRock(symbol)) {
                    // Rock
                    resultSymbol = "A";
                } else if (isPaper(symbol)) {
                    // Paper
                    resultSymbol = "B";
                } else if (isScissors(symbol)) {
                    // Scissors
                    resultSymbol = "C";
                }
            }
        }

        return resultSymbol;
    }

    private static boolean isDraw(String symbol1, String symbol2) {
        return symbol1.equals(symbol2) ||
                (isRock(symbol1) && isRock(symbol2)) ||
                (isPaper(symbol1) && isPaper(symbol2)) ||
                (isScissors(symbol1) && isScissors(symbol2));
    }

    private static boolean isRock(String symbol) {
        return symbol.equals("A") || symbol.equals("X");
    }

    private static boolean isPaper(String symbol) {
        return symbol.equals("B") || symbol.equals("Y");
    }

    private static boolean isScissors(String symbol) {
        return symbol.equals("C") || symbol.equals("Z");
    }

    private enum GameResult {

        WIN,
        LOSE,
        DRAW

    }

}
