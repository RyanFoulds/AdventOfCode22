package xyz.foulds.aoc.year22.day12;

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
        final String[] input = Files.readAllLines(Paths.get(args[0]))
                                    .stream()
                                    .map(String::trim)
                                    .toArray(String[]::new);

        final Graph graph = new Graph(input);
        System.out.println(graph.findShortestPathLengthFromStartToEnd());
        System.out.println(graph.findShortestPathLengthFromHighestToLowestPoint());
    }
}
