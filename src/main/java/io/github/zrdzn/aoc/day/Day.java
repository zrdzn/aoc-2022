package io.github.zrdzn.aoc.day;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public interface Day {

    void run(List<String> input);

    Path getInputPath() throws URISyntaxException;

}
