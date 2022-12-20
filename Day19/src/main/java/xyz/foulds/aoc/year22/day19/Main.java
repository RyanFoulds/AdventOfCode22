package xyz.foulds.aoc.year22.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }
        final List<Blueprint> blueprints = Files.readAllLines(Paths.get(args[0]))
                                                .stream()
                                                .map(String::trim)
                                                .map(Blueprint::new)
                                                .collect(Collectors.toList());

        // Part 1
        System.out.println(blueprints.stream()
                                     .mapToInt(bp -> bp.getQualityLevel(24))
                                     .sum());

        // Part 2
        System.out.println(blueprints.stream()
                                     .filter(bp -> bp.getId() <= 3)
                                     .mapToInt(bp -> bp.getMaxGeodeCount(32))
                                     .reduce(1, (left, right) -> left * right));
    }
}