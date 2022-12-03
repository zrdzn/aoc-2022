package io.github.zrdzn.aoc.day.days;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.github.zrdzn.aoc.day.Day;

public class Day3 implements Day {

    private final static String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final static String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final Map<Character, Integer> priorities = new HashMap<>();

    @Override
    public void run(List<String> input) {
        // Part 1 - Start
        int letterPriority = 1;
        for (char letter : LOWERCASE_ALPHABET.toCharArray()) {
            this.priorities.put(letter, letterPriority);
            letterPriority++;
        }

        letterPriority = 27;
        for (char letter : UPPERCASE_ALPHABET.toCharArray()) {
            this.priorities.put(letter, letterPriority);
            letterPriority++;
        }

        int prioritiesSum = 0;
        for (String line : input) {
            int rucksackMiddle = line.length() / 2;
            String compartment1 = line.substring(0, rucksackMiddle);
            String compartment2 = line.substring(rucksackMiddle);
            boolean found = false;
            for (char compartment1Letter : compartment1.toCharArray()) {
                if (found) {
                    break;
                }

                for (char compartment2Letter : compartment2.toCharArray()) {
                    if (compartment1Letter == compartment2Letter) {
                        prioritiesSum += this.priorities.get(compartment1Letter);
                        found = true;
                        break;
                    }
                }
            }
        }
        System.out.println("Total sum of priorities (1): " + prioritiesSum);
        // Part 1 - End

        // Part 2 - Start
        prioritiesSum = 0;
        for (int lineIndex = 0; lineIndex < input.size(); lineIndex += 3) {
            String rucksack1 = input.get(lineIndex);
            String rucksack2 = input.get(lineIndex + 1);
            String rucksack3 = input.get(lineIndex + 2);

            boolean foundInThree = false;
            for (char rucksack1Letter : rucksack1.toCharArray()) {
                if (foundInThree) {
                    break;
                }
                for (char rucksack2Letter : rucksack2.toCharArray()) {
                    if (foundInThree) {
                        break;
                    }
                    if (rucksack1Letter == rucksack2Letter) {
                        for (char rucksack3Letter : rucksack3.toCharArray()) {
                            if (rucksack2Letter == rucksack3Letter) {
                                prioritiesSum += this.priorities.get(rucksack3Letter);
                                foundInThree = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Total sum of priorities (2): " + prioritiesSum);
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day3.txt").toURI());
    }

}
