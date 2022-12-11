package xyz.foulds.aoc.year22.day8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;

public class Main
{
    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final String[] rows = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                Charset.defaultCharset()))
                .lines()
                .map(String::trim)
                .toArray(String[]::new);

        final Grid grid = new Grid(rows);

        // Part 1.
        System.out.println(grid.getVisibleTreeCount());

        // Part 2.
        System.out.println(grid.getAllTreeScores().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(RuntimeException::new));
    }
}