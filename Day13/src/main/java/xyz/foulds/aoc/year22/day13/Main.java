package xyz.foulds.aoc.year22.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                                  .collect(Collectors.joining("\n"));

        final List<Pair> pairs = Arrays.stream(input.split("\n\n"))
                                       .map(str -> str.split("\n"))
                                       .map(str -> new Pair(new Node(str[0]), new Node(str[1])))
                                       .collect(Collectors.toList());

        // Part 1.
        final int indexSum = pairs.stream()
                                  .filter(Pair::isOrdered)
                                  .mapToInt(pairs::indexOf)
                                  .map(i -> i+1) // 1-based indexing 🙄
                                  .sum();
        System.out.println(indexSum);

        // Part 2.
        final String[] dividerPackets = new String[]{"[[2]]","[[6]]"};
        final List<String> nodes = Stream.of(input.split("\n"), dividerPackets)
                                         .flatMap(Arrays::stream)
                                         .map(String::trim)
                                         .filter(str -> !str.isEmpty())
                                         .map(Node::new)
                                         .sorted()
                                         .map(Node::toString)
                                         .collect(Collectors.toList());

        final int prodcutOfIndices = Arrays.stream(dividerPackets)
                                           .mapToInt(nodes::indexOf)
                                           .map(i -> i+1) // 1-based indexing 🙄
                                           .reduce(1, (a, b) -> a*b);
        System.out.println(prodcutOfIndices);
    }
}