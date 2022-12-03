package io.github.zrdzn.aoc;

import io.github.zrdzn.aoc.day.Day;
import io.github.zrdzn.aoc.day.days.Day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) {
        Day day = new Day2();
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

}
