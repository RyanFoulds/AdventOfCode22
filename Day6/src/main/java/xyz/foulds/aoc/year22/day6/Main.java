package xyz.foulds.aoc.year22.day6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
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