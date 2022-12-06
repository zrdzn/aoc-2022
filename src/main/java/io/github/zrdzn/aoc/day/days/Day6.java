package io.github.zrdzn.aoc.day.days;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import io.github.zrdzn.aoc.day.Day;

import static io.github.zrdzn.aoc.AdventOfCode.getCharacterRepetitions;


public class Day6 implements Day {

    @Override
    public void run(List<String> input) {
        // Part 1 - Start
        System.out.println("Amount of processed characters (1): " + getProcessedCharacters(input.get(0), 4));
        // Part 1 - End

        // Part 2 - Start
        System.out.println("Amount of processed characters (2): " + getProcessedCharacters(input.get(0), 14));
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day6.txt").toURI());
    }

    private static int getProcessedCharacters(String target, int sequenceCharacters) {
        int maximumIndex = 0;
        String targetCopy = target;
        for (int bufferIndex = 0; bufferIndex < targetCopy.length(); bufferIndex++) {
            targetCopy = target;

            maximumIndex = bufferIndex + sequenceCharacters;
            if (maximumIndex >= targetCopy.length()) {
                maximumIndex = targetCopy.length();
            }

            String sequence = targetCopy.substring(bufferIndex, maximumIndex);
            if (getCharacterRepetitions(sequence) > 0) {
                continue;
            }

            break;
        }
        return maximumIndex;
    }

}
