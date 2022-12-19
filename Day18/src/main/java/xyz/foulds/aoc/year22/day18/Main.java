package xyz.foulds.aoc.year22.day18;

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
        final List<Cube> cubes = Files.readAllLines(Paths.get(args[0]))
                                      .stream()
                                      .map(String::trim)
                                      .map(Cube::new)
                                      .collect(Collectors.toList());

        final LavaDrop lavaDrop = new LavaDrop(cubes);

        // Part 1.
        System.out.println(lavaDrop.getTotalSurfaceArea());

        // Part 2.
        System.out.println(lavaDrop.getExternalSurfaceArea());
    }
}