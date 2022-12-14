package xyz.foulds.aoc.year22.day4;

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
        final List<Pair> elfPairs = Files.readAllLines(Paths.get(args[0]))
                                         .stream()
                                         .map(String::trim)
                                         .filter(s -> !s.isEmpty())
                                         .map(str -> str.split(","))
                                         .map(strs -> new Pair(new Elf(strs[0]), new Elf(strs[1])))
                                         .collect(Collectors.toList());

        // Part 1
        final long containedElves = elfPairs.stream()
                                            .filter(Pair::hasContainedElf)
                                            .count();
        System.out.println(containedElves);

        // Part 2
        final long overlappingElves = elfPairs.stream()
                                              .filter(Pair::hasOverlap)
                                              .count();
        System.out.println(overlappingElves);
    }
}