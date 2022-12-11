package xyz.foulds.aoc.year22.day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final List<String> input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                            Charset.defaultCharset()))
                .lines()
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