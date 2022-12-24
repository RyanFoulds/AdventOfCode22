package xyz.foulds.aoc.year22.day23;

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

        final Grove grove = new Grove(input);
        grove.doRounds(10);
        System.out.println(grove.countEmptySquares());

        final Grove freshGrove = new Grove(input);
        
        System.out.println(freshGrove.doRounds());
    }
}