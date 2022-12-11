package xyz.foulds.aoc.year22.day1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final String input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                            Charset.defaultCharset()))
                .lines()
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