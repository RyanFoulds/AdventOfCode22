package xyz.foulds.aoc.year22.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main
{

    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final String input = String.join("", Files.readAllLines(Paths.get(args[0])));

        System.out.println(solve(input.toCharArray(), 2022));
        System.out.println(solve(input.toCharArray(), 1000000000000L));
    }

    private static long solve(final char[] input, final long rocks)
    {
        final Tetris tetris = new Tetris(input);
        tetris.play(rocks);
        return tetris.totalHeight();
    }
}