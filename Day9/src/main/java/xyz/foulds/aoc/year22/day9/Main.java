package xyz.foulds.aoc.year22.day9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args)
    {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        final List<Move> moves = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream),
                                                                          Charset.defaultCharset()))
                .lines()
                .map(String::trim)
                .map(Move::new)
                .collect(Collectors.toList());

        final Rope rope1 = new Rope(2);
        final Rope rope2 = new Rope(10);

        for (final Move move : moves)
        {
            rope1.moveHead(move);
            rope2.moveHead(move);
        }

        System.out.println(rope1.countUniqueTailVisits());
        System.out.println(rope2.countUniqueTailVisits());
    }
}