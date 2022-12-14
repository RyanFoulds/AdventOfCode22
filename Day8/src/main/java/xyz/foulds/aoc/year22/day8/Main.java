package xyz.foulds.aoc.year22.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }
        final String[] rows = Files.readAllLines(Paths.get(args[0]))
                                   .stream()
                                   .map(String::trim)
                                   .toArray(String[]::new);

        final Grid grid = new Grid(rows);

        // Part 1.
        System.out.println(grid.getVisibleTreeCount());

        // Part 2.
        System.out.println(grid.getAllTreeScores().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(RuntimeException::new));
    }
}