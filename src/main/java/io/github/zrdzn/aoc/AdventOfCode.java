package io.github.zrdzn.aoc;

import io.github.zrdzn.aoc.day.Day;
import io.github.zrdzn.aoc.day.days.Day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) {
        Day day = new Day4();
        try {
            day.run(getInput(day.getInputPath()));
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    private static List<String> getInput(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        throw new IllegalArgumentException("Input for that day does not exist or something went wrong.");
    }

    public static int getNumber(String numberRaw) {
        try {
            return Integer.parseInt(numberRaw);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static boolean containsAnotherArray(int[] array1, int[] array2) {
        int array1Index = 0;
        for (int array2Number : array2) {
            for (array1Index = 0; array1Index < array1.length; array1Index++) {
                if (array2Number == array1[array1Index]) {
                    break;
                }
            }

            if (array1Index == array1.length) {
                return false;
            }
        }

        return true;
    }

    public static boolean intersectsWithAnotherArray(int[] array1, int[] array2) {
        for (int array1Number : array1) {
            for (int array2Number : array2) {
                if (array1Number == array2Number) {
                    return true;
                }
            }
        }

        return false;
    }

}
