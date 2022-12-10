package io.github.zrdzn.aoc.day.days;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.github.zrdzn.aoc.day.Day;

import static io.github.zrdzn.aoc.AdventOfCode.getNumber;
import static io.github.zrdzn.aoc.AdventOfCode.isNumber;

public class Day7 implements Day {

    @Override
    public void run(List<String> input) {
        // Part 1 - Start
        AocFile rootDirectory = new AocFile(null, "/", null, new ArrayList<>(), true, 0);
        AocFile currentDirectory = rootDirectory;
        for (int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
            String[] lineParts = input.get(lineIndex).split(" ");

            if (lineParts[0].equals("$")) {
                switch (lineParts[1]) {
                    case "cd" -> {
                        String nextDirectoryRaw = lineParts[2];

                        if (nextDirectoryRaw.equals("/")) {
                            continue;
                        }

                        // Go to the previous directory.
                        if (nextDirectoryRaw.equals("..")) {
                            if (currentDirectory.parent == null) {
                                currentDirectory.parent = rootDirectory;
                                continue;
                            }

                            currentDirectory = currentDirectory.parent;
                            continue;
                        }

                        currentDirectory = this.createDirectoryIfNotExists(currentDirectory, nextDirectoryRaw);
                    }
                    case "ls" -> {
                        while (true) {
                            int safeIndex = lineIndex + 1;
                            if (safeIndex >= input.size()) {
                                break;
                            }

                            String[] fileParts = input.get(safeIndex).split(" ");
                            if (fileParts[0].equals("$")) {
                                break;
                            }

                            if (fileParts[0].equals("dir")) {
                                this.createDirectoryIfNotExists(currentDirectory, fileParts[1]);
                            }

                            if (isNumber(fileParts[0])) {
                                this.createFileIfNotExists(currentDirectory, fileParts[1], getNumber(fileParts[0]));
                            }

                            lineIndex++;
                        }
                    }
                }
            }
        }

        System.out.println("Total size of all files: " + this.getTotalSize(rootDirectory));
        // Part 1 - End

        // Part 2 - Start
        System.out.println("Total size of smallest directory that would free space enough: " + this.getSmallestSizeThatWouldFreeSpace(rootDirectory, 30_000_000));
        // Part 2 - End
    }

    @Override
    public Path getInputPath() throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource("day7.txt").toURI());
    }

    private int getTotalSize(AocFile parentDirectory) {
        int totalSize = 0;
        // Get sizes indirectly.
        for (AocFile file : parentDirectory.getChildDirectories()) {
            if (file.getTotalSize() > 100_000) {
                continue;
            }

            totalSize += file.getTotalSize();
        }
        return totalSize;
    }

    private int getSmallestSizeThatWouldFreeSpace(AocFile parentDirectory, int minimumFreeSpace) {
        int neededSpace = minimumFreeSpace - this.getUnusedSpace(parentDirectory);

        int smallestSize = 0;
        // Get sizes indirectly.
        for (AocFile file : parentDirectory.getChildDirectories()) {
            int directoryTotalSize = file.getTotalSize();

            if (directoryTotalSize >= neededSpace) {
                if (smallestSize == 0) {
                    smallestSize = directoryTotalSize;
                    continue;
                }

                if (directoryTotalSize < smallestSize) {
                    smallestSize = directoryTotalSize;
                }
            }
        }
        return smallestSize;
    }

    private int getUnusedSpace(AocFile parentDirectory) {
        return 70_000_000 - parentDirectory.getTotalSize();
    }

    private AocFile createDirectoryIfNotExists(AocFile parentDirectory, String targetFileRaw) {
        return this.createFileIfNotExists(parentDirectory, targetFileRaw, 0, true);
    }

    private AocFile createFileIfNotExists(AocFile parentDirectory, String targetFileRaw, int size) {
        return this.createFileIfNotExists(parentDirectory, targetFileRaw, size, false);
    }

    private AocFile createFileIfNotExists(AocFile parentDirectory, String targetFileRaw, int size, boolean directory) {
        Optional<AocFile> fileMaybe = parentDirectory.children.stream()
                .filter(file -> file.name.equals(targetFileRaw))
                .findAny();

        AocFile nextFile;

        // Create file if not exists.
        if (fileMaybe.isEmpty()) {
            nextFile = new AocFile(
                    targetFileRaw,
                    parentDirectory.path + targetFileRaw + "/",
                    parentDirectory,
                    directory ? new ArrayList<>() : null,
                    directory,
                    size
            );
            parentDirectory.children.add(nextFile);
        } else {
            nextFile = fileMaybe.get();
        }

        return nextFile;
    }

    private class AocFile {

        private final String name;
        private final String path;
        private AocFile parent;
        private final List<AocFile> children;
        private final boolean directory;
        private final int size;

        public AocFile(String name, String path, AocFile parent, List<AocFile> children, boolean directory, int size) {
            this.name = name;
            this.path = path;
            this.parent = parent;
            this.children = children;
            this.directory = directory;
            this.size = size;
        }

        public int getTotalSize() {
            if (!this.directory) {
                return this.size;
            }

            int totalSize = 0;
            for (AocFile child : this.children) {
                totalSize += child.getTotalSize();
            }

            return totalSize;
        }

        public List<AocFile> getChildDirectories() {
            List<AocFile> allDirectories = new ArrayList<>();

            for (AocFile file : this.children) {
                if (!file.directory) {
                    continue;
                }

                allDirectories.add(file);
                allDirectories.addAll(file.getChildDirectories());
            }

            return allDirectories;
        }

    }

}
