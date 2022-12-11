package xyz.foulds.aoc.year22.day4;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main
{

    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final List<Pair> elfPairs = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                             Charset.defaultCharset()))
                .lines()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(string -> {
                final String[] strings = string.split(",");
                return new Pair(new Elf(strings[0]), new Elf(strings[1]));
                })
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