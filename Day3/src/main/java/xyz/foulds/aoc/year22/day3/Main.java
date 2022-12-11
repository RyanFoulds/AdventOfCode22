package xyz.foulds.aoc.year22.day3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main
{

    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final List<Rucksack> rucksacks = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                                  Charset.defaultCharset()))
                .lines()
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