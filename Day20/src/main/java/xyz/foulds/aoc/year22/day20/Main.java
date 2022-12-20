package xyz.foulds.aoc.year22.day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("Please provide a file path for the puzzle input, and a decryption key.");
        }
        final List<Long> input = Files.readAllLines(Paths.get(args[0]))
                                         .stream()
                                         .map(String::trim)
                                         .map(Long::parseLong)
                                         .collect(Collectors.toList());
        final int decryptionKey = Integer.parseInt(args[1]);

        // Part 1
        final Decrypter part1Decrypter = new Decrypter(input, 1);
        System.out.println(part1Decrypter.decrypt());

        // Part 2
        final Decrypter part2Decrypter = new Decrypter(input.stream()
                                                            .map(i -> decryptionKey * i)
                                                            .collect(Collectors.toList()), 10);
        System.out.println(part2Decrypter.decrypt());
    }
}