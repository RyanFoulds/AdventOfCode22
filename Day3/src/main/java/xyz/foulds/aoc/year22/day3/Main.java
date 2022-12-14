package xyz.foulds.aoc.year22.day3;

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
        final List<Rucksack> rucksacks = Files.readAllLines(Paths.get(args[0]))
                                              .stream()
                                              .map(String::trim)
                                              .map(Rucksack::new)
                                              .collect(Collectors.toList());

        // Part 1.
        final int sum = rucksacks.stream()
                                 .mapToInt(Rucksack::getTotalDuplicatePriority)
                                 .sum();
        System.out.println(sum);

        // Part 2.
        if (rucksacks.size() % 3 != 0)
        {
            throw new RuntimeException();
        }

        int badgeSum = 0;
        for (int i = 0; i < rucksacks.size(); i += 3)
        {
            final Group group = new Group(rucksacks.get(i), rucksacks.get(i+1), rucksacks.get(i+2));
            badgeSum += group.getBadgePriority();
        }
        System.out.println(badgeSum);
    }
}