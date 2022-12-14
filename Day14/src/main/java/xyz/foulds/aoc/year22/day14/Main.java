package xyz.foulds.aoc.year22.day14;

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
        final List<Wall> walls = Files.readAllLines(Paths.get(args[0]))
                                      .stream()
                                      .map(String::trim)
                                      .map(Wall::new)
                                      .collect(Collectors.toList());

        // Part 1
        System.out.println(calculateSandCount(walls, false));
        System.out.println(calculateSandCount(walls, true));
    }

    private static int calculateSandCount(final List<Wall> walls, final boolean hasFloor)
    {
        final Cave cave = new Cave(walls, hasFloor);
        cave.pourSand();
        return cave.getSandCount();
    }
}