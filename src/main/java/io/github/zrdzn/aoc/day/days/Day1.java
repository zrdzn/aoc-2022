package io.github.zrdzn.aoc.day.days;

import io.github.zrdzn.aoc.day.Day;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day1 implements Day {

    @Override
    public void run(List<String> input) {
        // Part 1 - Start
        int[] calories = new int[input.size()];

        int currentCalorie = 0;
        for (String line : input) {
            try {
                calories[currentCalorie] += Integer.parseInt(line);
            } catch (NumberFormatException ignored) {
                currentCalorie += 1;
            }
        }

        Arrays.sort(calories);

        System.out.println("Most calories: " + calories[calories.length - 1]);
        // Part 1 - End

        // Part 2 - Start
        int totalCalories = calories[calories.length - 1] + calories[calories.length - 2] + calories[calories.length - 3];
        System.out.println("Total calories of 3 entries: " + totalCalories);
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day1.txt").toURI());
    }

}
