package xyz.foulds.aoc.year22.day25;

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

        final long sum = Files.readAllLines(Paths.get(args[0])).stream()
                              .map(String::trim)
                              .map(Snafu::new)
                              .mapToLong(Snafu::getValue)
                              .sum();
        final Snafu snafu = new Snafu(sum);
        System.out.println(snafu);
    }
}