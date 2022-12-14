package xyz.foulds.aoc.year22.day2;

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
        final List<String> input = Files.readAllLines(Paths.get(args[0]))
                                        .stream()
                                        .map(String::trim)
                                        .collect(Collectors.toList());

        // part 1
        final long totalScore = input.stream()
                .map(Round::fromString)
                .mapToLong(Round::getScore)
                .sum();

        // part 2
        final long part2Score = input.stream()
                .map(Round::fromResultString)
                .mapToLong(Round::getScore)
                .sum();

        System.out.println(totalScore);
        System.out.println(part2Score);
    }
}