package xyz.foulds.aoc.year22.day12;

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
        final String[] input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                      Charset.defaultCharset()))
            .lines()
            .map(String::trim)
            .toArray(String[]::new);

        final Graph graph = new Graph(input);
        System.out.println(graph.findShortestPathLengthFromStartToEnd());
        System.out.println(graph.findShortestPathLengthFromHighestToLowestPoint());
    }
}
