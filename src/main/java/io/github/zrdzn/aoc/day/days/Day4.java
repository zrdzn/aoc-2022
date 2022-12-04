package io.github.zrdzn.aoc.day.days;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import io.github.zrdzn.aoc.day.Day;

import static io.github.zrdzn.aoc.AdventOfCode.containsAnotherArray;
import static io.github.zrdzn.aoc.AdventOfCode.getNumber;
import static io.github.zrdzn.aoc.AdventOfCode.intersectsWithAnotherArray;

public class Day4 implements Day {

    @Override
    public void run(List<String> input) {
        // Part 1 - Start
        int totalOverlaps = 0;
        for (String rawSections : input) {
            String[] sections = rawSections.split(",");

            String[] section1Parts = sections[0].split("-");
            int section1Minimum = getNumber(section1Parts[0]);
            int section1Maximum = getNumber(section1Parts[1]);

            String[] section2Parts = sections[1].split("-");
            int section2Minimum = getNumber(section2Parts[0]);
            int section2Maximum = getNumber(section2Parts[1]);

            int[] section1 = IntStream.rangeClosed(section1Minimum, section1Maximum).toArray();
            int[] section2 = IntStream.rangeClosed(section2Minimum, section2Maximum).toArray();

            if (containsAnotherArray(section1, section2) || containsAnotherArray(section2, section1)) {
                totalOverlaps += 1;
            }
        }
        System.out.println("Total amount of overlaps: " + totalOverlaps);
        // Part 1 - End

        // Part 2 - Start
        int totalIntersections = 0;
        for (String rawSections : input) {
            String[] sections = rawSections.split(",");

            String[] section1Parts = sections[0].split("-");
            int section1Minimum = getNumber(section1Parts[0]);
            int section1Maximum = getNumber(section1Parts[1]);

            String[] section2Parts = sections[1].split("-");
            int section2Minimum = getNumber(section2Parts[0]);
            int section2Maximum = getNumber(section2Parts[1]);

            int[] section1 = IntStream.rangeClosed(section1Minimum, section1Maximum).toArray();
            int[] section2 = IntStream.rangeClosed(section2Minimum, section2Maximum).toArray();

            if (intersectsWithAnotherArray(section1, section2) || intersectsWithAnotherArray(section2, section1)) {
                totalIntersections += 1;
            }
        }
        System.out.println("Total amount of intersections: " + totalIntersections);
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day4.txt").toURI());
    }

}
