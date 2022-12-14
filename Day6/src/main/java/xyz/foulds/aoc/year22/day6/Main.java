package xyz.foulds.aoc.year22.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }
        final String input = Files.readAllLines(Paths.get(args[0]))
                                  .stream()
                                  .map(String::trim)
                                  .collect(Collectors.joining());

        final char[] inputChars = input.toCharArray();

        // Part 1.
        System.out.println(findStart(inputChars, 4));

        // Part 2.
        System.out.println(findStart(inputChars, 14));
    }

    public static int findStart(final char[] input, final int size)
    {
        final EvictingQueue<Character> queue = new EvictingQueue<>(size);
        for (int i = 0; i < input.length; i++)
        {
            queue.offer(input[i]);

            if (i >= size && queue.containsOnlyUniqueElements())
            {
                return i + 1;
            }
        }
        throw new RuntimeException();
    }
}