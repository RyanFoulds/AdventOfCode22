package xyz.foulds.aoc.year22.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
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
        final String input = Files.readAllLines(Paths.get(args[0]))
                                  .stream()
                                  .map(String::trim)
                                  .collect(Collectors.joining("\n"));

        final List<Integer> orderedElves = Arrays.stream(input.split("\n\n"))
                                                 .map(elf -> Arrays.stream(elf.split("\n"))
                                                                   .map(String::trim)
                                                                   .mapToInt(Integer::parseInt)
                                                                   .sum())
                                                 .sorted(Comparator.reverseOrder())
                                                 .collect(Collectors.toList());

        // Part 1
        System.out.println(orderedElves.get(0));
        // Part 2
        System.out.println(orderedElves.get(0) + orderedElves.get(1) + orderedElves.get(2));
    }
}