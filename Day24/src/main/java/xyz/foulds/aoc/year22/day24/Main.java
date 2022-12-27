package xyz.foulds.aoc.year22.day24;

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

        final List<String> input = Files.readAllLines(Paths.get(args[0])).stream()
                                        .map(String::trim)
                                        .collect(Collectors.toList());

        // Part 1
        final Valley valley = new Valley(input);
        System.out.println(valley.navigate());

        // Part 2 (Carrying on from state at the end of part 1.
        valley.returnTrip();
        System.out.println(valley.navigate());
    }
}