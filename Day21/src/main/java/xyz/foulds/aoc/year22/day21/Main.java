package xyz.foulds.aoc.year22.day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main
{
    public static void main(final String[] args) throws IOException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Please provide a single file path for the puzzle input.");
        }

        final Map<String, Monkey> monkeys = Files.readAllLines(Paths.get(args[0]))
                                                 .stream()
                                                 .map(String::trim)
                                                 .map(Monkey::new)
                                                 .collect(Collectors.toMap(Monkey::getName,
                                                                           Function.identity()));
        monkeys.forEach((name, monkey) -> monkey.linkMonkeys(monkeys));

        // Part 1
        System.out.println(monkeys.get("root").getValue());

        // Part 2
        System.out.println(monkeys.get("humn").calculateNeededValue());
    }
}